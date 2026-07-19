package com.itheima.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.itheima.config.S3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * S3 文件服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    private static final int DEFAULT_STREAM_BUFFER_SIZE = 20 * (1 << 20);  // 20MB
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

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                s3Properties.getBucket(),
                key,
                file.getInputStream(),
                metadata
        ).withCannedAcl(CannedAccessControlList.PublicReadWrite);

        putObjectRequest.getRequestClientOptions().setReadLimit(DEFAULT_STREAM_BUFFER_SIZE);

        amazonS3.putObject(putObjectRequest);

        log.info("文件上传成功: bucket={}, key={}", s3Properties.getBucket(), key);
        return key;
    }

    /**
     * 上传输入流
     *
     * @param inputStream 输入流
     * @param key         S3 key
     * @param contentType 内容类型
     * @return PutObjectResult
     */
    public PutObjectResult uploadStream(InputStream inputStream, String key, String contentType) {
        ObjectMetadata metadata = new ObjectMetadata();
        if (contentType != null && !contentType.isEmpty()) {
            metadata.setContentType(contentType);
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                s3Properties.getBucket(),
                key,
                inputStream,
                metadata
        ).withCannedAcl(CannedAccessControlList.PublicReadWrite);

        putObjectRequest.getRequestClientOptions().setReadLimit(DEFAULT_STREAM_BUFFER_SIZE);

        return amazonS3.putObject(putObjectRequest);
    }

    /**
     * 下载文件
     *
     * @param key S3 key
     * @return S3Object
     */
    public S3Object downloadFile(String key) {
        return amazonS3.getObject(s3Properties.getBucket(), key);
    }

    /**
     * 删除文件
     *
     * @param key S3 key
     */
    public void deleteFile(String key) {
        amazonS3.deleteObject(s3Properties.getBucket(), key);
        log.info("文件删除成功: bucket={}, key={}", s3Properties.getBucket(), key);
    }

    /**
     * 判断文件是否存在
     *
     * @param key S3 key
     * @return 是否存在
     */
    public boolean fileExists(String key) {
        return amazonS3.doesObjectExist(s3Properties.getBucket(), key);
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
        Date expiration = new Date(System.currentTimeMillis() + expireTime);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
                s3Properties.getBucket(),
                key
        )
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);

        URL url = amazonS3.generatePresignedUrl(request);
        return url.toString();
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
     * 创建文件夹
     *
     * @param folderPath 文件夹路径
     */
    public void createFolder(String folderPath) throws AmazonClientException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);

        String suffix = "/";
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                s3Properties.getBucket(),
                folderPath + suffix,
                emptyContent,
                metadata
        );
        amazonS3.putObject(putObjectRequest);

        log.info("文件夹创建成功: bucket={}, folder={}", s3Properties.getBucket(), folderPath);
    }

    /**
     * 列出指定前缀的所有对象
     *
     * @param prefix 前缀
     * @return ObjectListing
     */
    public ObjectListing listObjects(String prefix) {
        return amazonS3.listObjects(s3Properties.getBucket(), prefix);
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
