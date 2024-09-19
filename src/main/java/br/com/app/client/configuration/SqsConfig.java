package br.com.app.client.configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SqsConfig {

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String sqsEndpoint;

    @Value("${spring.cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Bean
    SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                .endpointOverride(URI.create(sqsEndpoint))
                .build();
    }
    
    @Bean
    SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient) {
        return SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .build();
    }
}
