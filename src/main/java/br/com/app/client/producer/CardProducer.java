package br.com.app.client.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardProducer {

	@Value("${cloud.aws.sqs.queue.name}")
	private String queueName;
	
	private final SqsTemplate sqsTemplate;
	
	public void sendMessage(String message) {
		sqsTemplate.send(queueName, MessageBuilder.withPayload(message).build());
	}

}
