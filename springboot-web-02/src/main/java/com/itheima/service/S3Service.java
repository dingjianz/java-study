package com.itheima.service;

import com.itheima.config.S3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * S3 文件服务（AWS SDK for Java 2.x）
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final S3Properties s3Properties;

    private static final long SEVEN_DAYS_MILLS = 7 * 24 * 60 * 60 * 1000L; // 7天

    /**
     * 上传文件（公共读写权限）
     *
     * @param file   文件
     * @param prefix 路径前缀
     * @return 文件的 S3 key
     */
    public String uploadFile(MultipartFile file, String prefix) throws IOException {
        String fileName = file.getOriginalFilename();
        String key = generateS3Key(prefix, fileName);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .contentType(file.getContentType())
                .contentLength(file.getSize())
                .acl(ObjectCannedACL.PUBLIC_READ_WRITE)
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        log.info("文件上传成功: bucket={}, key={}", s3Properties.getBucket(), key);
        return key;
    }

    /**
     * 上传输入流
     *
     * @param inputStream 输入流
     * @param key         S3 key
     * @param contentType 内容类型
     * @return PutObjectResponse
     */
    public PutObjectResponse uploadStream(InputStream inputStream, String key, String contentType) throws IOException {
        // 2.x 上传流需要提前知道内容长度，故先读入字节数组
        byte[] bytes = inputStream.readAllBytes();

        PutObjectRequest.Builder builder = PutObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .contentLength((long) bytes.length)
                .acl(ObjectCannedACL.PUBLIC_READ_WRITE);
        if (contentType != null && !contentType.isEmpty()) {
            builder.contentType(contentType);
        }

        return s3Client.putObject(builder.build(), RequestBody.fromBytes(bytes));
    }

    /**
     * 下载文件
     *
     * @param key S3 key
     * @return 文件响应流（本身即 InputStream）
     */
    public ResponseInputStream<GetObjectResponse> downloadFile(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .build();
        return s3Client.getObject(getObjectRequest);
    }

    /**
     * 删除文件
     *
     * @param key S3 key
     */
    public void deleteFile(String key) {
        s3Client.deleteObject(builder -> builder
                .bucket(s3Properties.getBucket())
                .key(key));
        log.info("文件删除成功: bucket={}, key={}", s3Properties.getBucket(), key);
    }

    /**
     * 判断文件是否不存在
     *
     * @param key S3 key
     * @return 不存在返回 true
     */
    public boolean fileNotExists(String key) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(s3Properties.getBucket())
                    .key(key)
                    .build();
            s3Client.headObject(headObjectRequest);
            return false;
        } catch (NoSuchKeyException e) {
            return true;
        }
    }

    /**
     * 生成预签名 URL（7天有效期）
     *
     * @param key S3 key
     * @return 预签名 URL
     */
    public String generatePresignedUrl(String key) {
        return generatePresignedUrl(key, SEVEN_DAYS_MILLS);
    }

    /**
     * 生成预签名 URL
     *
     * @param key        S3 key
     * @param expireTime 过期时间（毫秒）
     * @return 预签名 URL
     */
    public String generatePresignedUrl(String key, long expireTime) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMillis(expireTime))
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
        return presignedRequest.url().toString();
    }

    /**
     * 获取公共访问 URL
     *
     * @param key S3 key
     * @return 公共 URL
     */
    public String getPublicUrl(String key) {
        return String.format("https://%s/%s/%s",
                s3Properties.getUrl(),
                s3Properties.getBucket(),
                key
        );
    }

    /**
     * 创建文件夹（上传一个以 "/" 结尾的空对象）
     *
     * @param folderPath 文件夹路径
     */
    public void createFolder(String folderPath) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(folderPath + "/")
                .contentLength(0L)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.empty());

        log.info("文件夹创建成功: bucket={}, folder={}", s3Properties.getBucket(), folderPath);
    }

    /**
     * 列出指定前缀的所有对象
     *
     * @param prefix 前缀
     * @return ListObjectsV2Response
     */
    public ListObjectsV2Response listObjects(String prefix) {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(s3Properties.getBucket())
                .prefix(prefix)
                .build();
        return s3Client.listObjectsV2(request);
    }

    /**
     * 生成 S3 key（带日期路径和 UUID）
     *
     * @param prefix   前缀
     * @param fileName 文件名
     * @return S3 key
     */
    private String generateS3Key(String prefix, String fileName) {
        // 获取文件后缀
        String suffix = "";
        if (fileName != null && fileName.contains(".")) {
            suffix = fileName.substring(fileName.lastIndexOf("."));
        }

        // 生成日期路径: yyyy/MM/dd
        String datePath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        // 生成 UUID
        String uuid = UUID.randomUUID().toString().replace("-", "");

        // 组合路径: prefix/yyyy/MM/dd/uuid.suffix
        return String.format("%s/%s/%s%s", prefix, datePath, uuid, suffix);
    }
}
