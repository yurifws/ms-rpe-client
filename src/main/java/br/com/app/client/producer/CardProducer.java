package br.com.app.client.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardProducer {

	@Value("${cloud.aws.sqs.queue.name}")
	private String queueName;
	
	private final QueueMessagingTemplate queueMessagingTemplate;
	
	public void sendMessage(String message) {
		queueMessagingTemplate.send(queueName, MessageBuilder.withPayload(message).build());
	}

}
