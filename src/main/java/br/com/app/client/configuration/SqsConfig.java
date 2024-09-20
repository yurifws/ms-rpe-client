package br.com.app.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;

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

//    @Bean
//    SqsAsyncClient sqsAsyncClient() {
//        return SqsAsyncClient.builder()
//                .region(Region.of(region))
//                .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
//                .endpointOverride(URI.create(sqsEndpoint))
//                .build();
//    }
//    
//    @Bean
//    SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient) {
//        return SqsTemplate.builder()
//                .sqsAsyncClient(sqsAsyncClient)
//                .build();
//    }
    

    @Bean
    QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }
    
    @Bean
    @Primary
    AmazonSQSAsync amazonSQSAsync() {
    	BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
    	
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(sqsEndpoint, region))
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .build();
    }
}
