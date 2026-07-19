package com.itheima.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.itheima.pojo.Result;
import com.itheima.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器
 */
@Slf4j
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3Service s3Service;

    /**
     * 上传文件到 S3
     *
     * @param file 上传的文件
     * @return 文件访问信息
     */
    @PostMapping
    public Result upload(@RequestParam("file") MultipartFile file) {
        log.info("开始上传文件: {}, 大小: {} bytes", file.getOriginalFilename(), file.getSize());

        try {
            // 上传文件到 S3（默认前缀为 "uploads"）
            String key = s3Service.uploadFile(file, "uploads");

            // 生成访问 URL
            String publicUrl = s3Service.getPublicUrl(key);
            String presignedUrl = s3Service.generatePresignedUrl(key);

            // 返回结果
            Map<String, Object> data = new HashMap<>();
            data.put("key", key);
            data.put("fileName", file.getOriginalFilename());
            data.put("fileSize", file.getSize());
            data.put("contentType", file.getContentType());
            data.put("publicUrl", publicUrl);
            data.put("presignedUrl", presignedUrl);

            log.info("文件上传成功: key={}", key);
            return Result.success(data);

        } catch (IOException e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 上传图片（带前缀）
     *
     * @param file   上传的图片
     * @param prefix 路径前缀（可选，默认 "images"）
     * @return 图片访问信息
     */
    @PostMapping("/image")
    public Result uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "prefix", defaultValue = "images") String prefix) {

        log.info("开始上传图片: {}, 前缀: {}", file.getOriginalFilename(), prefix);

        // 验证文件类型
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return Result.error("只能上传图片文件");
        }

        try {
            String key = s3Service.uploadFile(file, prefix);
            String publicUrl = s3Service.getPublicUrl(key);
            String presignedUrl = s3Service.generatePresignedUrl(key);

            Map<String, Object> data = new HashMap<>();
            data.put("key", key);
            data.put("fileName", file.getOriginalFilename());
            data.put("publicUrl", publicUrl);
            data.put("presignedUrl", presignedUrl);

            log.info("图片上传成功: key={}", key);
            return Result.success(data);

        } catch (IOException e) {
            log.error("图片上传失败", e);
            return Result.error("图片上传失败: " + e.getMessage());
        }
    }

    /**
     * 下载文件
     *
     * @param key S3 文件 key
     * @return 文件流
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("key") String key) {
        log.info("开始下载文件: key={}", key);

        try {
            S3Object s3Object = s3Service.downloadFile(key);
            InputStream inputStream = s3Object.getObjectContent();

            // 读取文件内容
            byte[] content = inputStream.readAllBytes();
            inputStream.close();

            // 获取文件名
            String fileName = key.substring(key.lastIndexOf("/") + 1);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);

            log.info("文件下载成功: key={}", key);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(content);

        } catch (Exception e) {
            log.error("文件下载失败: key={}", key, e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 删除文件
     *
     * @param key S3 文件 key
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("key") String key) {
        log.info("开始删除文件: key={}", key);

        try {
            if (!s3Service.fileExists(key)) {
                return Result.error("文件不存在");
            }

            s3Service.deleteFile(key);
            log.info("文件删除成功: key={}", key);
            return Result.success();

        } catch (Exception e) {
            log.error("文件删除失败: key={}", key, e);
            return Result.error("文件删除失败: " + e.getMessage());
        }
    }

    /**
     * 获取文件访问 URL
     *
     * @param key        S3 文件 key
     * @param expireTime 过期时间（分钟，可选，默认 10080 分钟 = 7 天）
     * @return URL 信息
     */
    @GetMapping("/url")
    public Result getUrl(
            @RequestParam("key") String key,
            @RequestParam(value = "expireTime", defaultValue = "10080") Integer expireTime) {

        log.info("获取文件 URL: key={}, expireTime={}分钟", key, expireTime);

        try {
            if (!s3Service.fileExists(key)) {
                return Result.error("文件不存在");
            }

            String publicUrl = s3Service.getPublicUrl(key);
            String presignedUrl = s3Service.generatePresignedUrl(key, expireTime * 60 * 1000L);

            Map<String, String> data = new HashMap<>();
            data.put("key", key);
            data.put("publicUrl", publicUrl);
            data.put("presignedUrl", presignedUrl);
            data.put("expireMinutes", expireTime.toString());

            return Result.success(data);

        } catch (Exception e) {
            log.error("获取文件 URL 失败: key={}", key, e);
            return Result.error("获取文件 URL 失败: " + e.getMessage());
        }
    }
}
