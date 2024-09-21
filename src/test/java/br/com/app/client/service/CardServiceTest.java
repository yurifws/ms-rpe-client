package br.com.app.client.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.app.client.client.CardClient;
import br.com.app.client.exception.BusinessException;
import br.com.app.client.model.CardRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.producer.CardProducer;
import br.com.app.client.testdata.CardRequestModelTestData;
import br.com.app.client.testdata.CardResponseModelTestData;
import br.com.app.client.testdata.ClientResponseModelTestData;

@SpringBootTest
class CardServiceTest {

	@Mock
	CardProducer cardProducer;

	@Mock
	IClientService clientService;

	@Mock
	CardClient cardClient;

	@InjectMocks
	CardService cardService;

	@Test
	void sendMessage() throws JsonProcessingException {
		ClientResponseModel clientResponseModel = ClientResponseModelTestData.getClientResponseModel();

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		CardRequestModel cardRequestModel = CardRequestModelTestData.getCardRequestModel();
		String json = objectWriter.writeValueAsString(cardRequestModel);

		when(clientService.searchById(cardRequestModel.getClientId())).thenReturn(clientResponseModel);
		doNothing().when(cardProducer).sendMessage(json);

		cardService.sendMessage(cardRequestModel);

		verify(clientService).searchById(cardRequestModel.getClientId());
		verify(cardProducer).sendMessage(json);

	}

	@Test
	void sendMessage_throwBusinessException() throws JsonProcessingException {
		ClientResponseModel clientResponseModel = ClientResponseModelTestData.getClientResponseModel();

		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		CardRequestModel cardRequestModel = CardRequestModelTestData.getCardRequestModel();
		String json = objectWriter.writeValueAsString(cardRequestModel);
		when(clientService.searchById(cardRequestModel.getClientId())).thenReturn(clientResponseModel);

		doThrow(new RuntimeException("teste")).when(cardProducer).sendMessage(json);

		assertThrows(BusinessException.class, () -> cardService.sendMessage(cardRequestModel));
		verify(clientService).searchById(cardRequestModel.getClientId());

	}

	@Test
	void getCardByClientId() {
		Long id = 1234L;
		ClientResponseModel clientResponseModel = ClientResponseModelTestData.getClientResponseModel();
		CardRequestModel cardRequestModel = CardRequestModelTestData.getCardRequestModel();
		when(clientService.searchById(id)).thenReturn(clientResponseModel);
		when(cardClient.getCardByClientId(clientResponseModel.getId())).thenReturn(List.of(CardResponseModelTestData.getCardResponseModel()));
		
		cardService.getCardByClientId(id);

		verify(clientService).searchById(id);
		verify(cardClient).getCardByClientId(cardRequestModel.getClientId());
	}

}
