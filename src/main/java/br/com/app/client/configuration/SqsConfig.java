package br.com.app.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
@EnableSqs
public class SqsConfig {

	@Value("${cloud.aws.sqs.endpoint}")
	private String serviceEndpoint;

	@Value("${cloud.aws.region.static}")
	private String signingRegion;

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1")
                )
                .build();
    }
}
