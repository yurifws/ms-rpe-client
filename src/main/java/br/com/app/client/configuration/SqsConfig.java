package br.com.app.client.configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
@EnableSqs
public class SqsConfig {

	@Value("${cloud.aws.sqs.endpoint}")
	private String serviceEndpoint;

	@Value("${cloud.aws.region.static}")
	private String signingRegion;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
        		.endpointOverride(URI.create(serviceEndpoint))
        		.region(Region.US_EAST_1)
        		.build();
    }
}
