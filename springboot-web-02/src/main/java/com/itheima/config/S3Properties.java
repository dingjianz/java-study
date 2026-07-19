package com.itheima.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * S3 配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "aws.s3")
public class S3Properties {
    /**
     * S3 服务地址
     */
    private String url = "oss-beijing-m8.openstorage.cn";

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 桶名称
     */
    private String bucket;

    /**
     * 最大连接数
     */
    private Integer maxConnections = 200;

    /**
     * 区域
     */
    private String region = "cn-north-1";
}
