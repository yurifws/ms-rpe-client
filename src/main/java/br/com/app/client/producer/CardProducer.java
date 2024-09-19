package br.com.app.client.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
public class CardProducer {

	@Value("${spring.cloud.aws.sqs.queue.uri}")
	private String queueUri;
	
	private final QueueMessagingTemplate queueMessagingTemplate;
	
	public void sendMessage(String message) {
		queueMessagingTemplate.send(queueUri, MessageBuilder.withPayload(message).build());
	}

}
