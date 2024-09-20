package br.com.app.client.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.app.client.exception.BusinessException;
import br.com.app.client.model.CardRequestModel;
import br.com.app.client.producer.CardProducer;
import br.com.app.client.testdata.CardRequestModelTestData;

@SpringBootTest
class CardServiceTest {

	@Mock
	CardProducer cardProducer;

	@InjectMocks
	CardService cardService;

	@Test
	void sendMessage() throws JsonProcessingException {

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		CardRequestModel cardRequestModel = CardRequestModelTestData.getCardRequestModel();
		String json = objectWriter.writeValueAsString(cardRequestModel);

		doNothing().when(cardProducer).sendMessage(json);

		cardService.sendMessage(cardRequestModel);

		verify(cardProducer).sendMessage(json);

	}

	@Test
	void sendMessage_throwBusinessException() throws JsonProcessingException {

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		CardRequestModel cardRequestModel = CardRequestModelTestData.getCardRequestModel();
		String json = objectWriter.writeValueAsString(cardRequestModel);

		doThrow(new RuntimeException("teste")).when(cardProducer).sendMessage(json);

		assertThrows(BusinessException.class, () -> cardService.sendMessage(cardRequestModel));

	}

}
