package br.com.app.client.producer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;

@SpringBootTest
class CardProducerTest {
	
	@Mock
	QueueMessagingTemplate queueMessagingTemplate;
	
	@InjectMocks
	CardProducer cardProducer;
	
	String queueUri = "http://localhost:4566/000000000000/queue-card";
	
    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(cardProducer, "queueUri", queueUri);
    }
	
	@Test
	void sendMessage() {
		
		String message = """
				{
					"number": "1231234",
					"password": "password",
					"status": "ATIVO",
					"holderName": "holderName",
					"clientId": 1234,
					"productId": 12345
				}
				""";

		
		cardProducer.sendMessage(message);
		
		verify(queueMessagingTemplate).send(eq(queueUri), any());

	}
}
