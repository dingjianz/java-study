package com.itheima.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * S3 客户端配置
 */
@Configuration
@RequiredArgsConstructor
public class S3Config {

    private final S3Properties s3Properties;

    /**
     * 创建 AmazonS3 客户端
     */
    @Bean
    public AmazonS3 amazonS3() {
        // 生成客户端配置类
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        clientConfig.setMaxConnections(s3Properties.getMaxConnections());
        clientConfig.withUseExpectContinue(false);

        // 创建凭证
        AWSCredentials credentials = new BasicAWSCredentials(
                s3Properties.getAccessKey(),
                s3Properties.getSecretKey()
        );
        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);

        // 构建 S3 客户端
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
        builder.setCredentials(credentialsProvider);
        builder.setEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(
                        s3Properties.getUrl(),
                        s3Properties.getRegion()
                )
        );
        builder.setClientConfiguration(clientConfig);
        builder.withPathStyleAccessEnabled(Boolean.TRUE);

        return builder.build();
    }
}
