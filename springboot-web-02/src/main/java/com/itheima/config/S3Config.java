package com.itheima.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.checksums.RequestChecksumCalculation;
import software.amazon.awssdk.core.checksums.ResponseChecksumValidation;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

/**
 * S3 客户端配置（AWS SDK for Java 2.x）
 */
@Configuration
@RequiredArgsConstructor
public class S3Config {

    private final S3Properties s3Properties;

    /**
     * 静态凭证提供者
     */
    private StaticCredentialsProvider credentialsProvider() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                s3Properties.getAccessKey(),
                s3Properties.getSecretKey()
        );
        return StaticCredentialsProvider.create(credentials);
    }

    /**
     * 完整 endpoint（补全协议头），2.x 的 endpointOverride 要求带 scheme 的 URI
     */
    private URI endpoint() {
        String url = s3Properties.getUrl();
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        return URI.create(url);
    }

    /**
     * 路径风格访问配置（等价于 1.x 的 withPathStyleAccessEnabled(true)）
     */
    private S3Configuration serviceConfiguration() {
        return S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build();
    }

    /**
     * 创建 S3Client（同步客户端，负责上传/下载/删除等操作）
     */
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(credentialsProvider())
                .endpointOverride(endpoint())
                .region(Region.of(s3Properties.getRegion()))
                .serviceConfiguration(serviceConfiguration())
                // 第三方 S3 兼容存储（非 AWS）不识别 SDK 2.30+ 默认注入的 CRC 校验头，
                // 会返回 400；改为仅在必要时计算/校验，避免多余的 checksum header
                .requestChecksumCalculation(RequestChecksumCalculation.WHEN_REQUIRED)
                .responseChecksumValidation(ResponseChecksumValidation.WHEN_REQUIRED)
                .httpClientBuilder(ApacheHttpClient.builder()
                        .maxConnections(s3Properties.getMaxConnections())
                        .expectContinueEnabled(false))
                .build();
    }

    /**
     * 创建 S3Presigner（专门用于生成预签名 URL）
     */
    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .credentialsProvider(credentialsProvider())
                .endpointOverride(endpoint())
                .region(Region.of(s3Properties.getRegion()))
                .serviceConfiguration(serviceConfiguration())
                .build();
    }
}
