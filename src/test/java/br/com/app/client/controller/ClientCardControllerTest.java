package br.com.app.client.controller;

import static br.com.app.client.constants.RestConstants.PATH_CARDS;
import static br.com.app.client.constants.RestConstants.PATH_CLIENTS;
import static br.com.app.client.constants.RestConstants.PATH_VARIABLE_ID;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.app.client.model.CardRequestModel;
import br.com.app.client.service.ICardService;
import br.com.app.client.testdata.CardRequestModelTestData;

@WebMvcTest(ClientCardController.class)
class ClientCardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ICardService cardService;

	@Test
	void insert() throws Exception {
		Long clientId = 1234L;

		CardRequestModel request = CardRequestModelTestData.getCardRequestModel();
		doNothing().when(cardService).sendMessage(request);
		
		String body = """
				{
					"number": "1231234",
					"password": "password",
					"status": "ATIVO",
					"holderName": "holderName",
					"clientId": 1234,
					"productId": 12345
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post(PATH_CLIENTS+PATH_VARIABLE_ID+PATH_CARDS, clientId)
				.content(body)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isCreated());

		verify(cardService).sendMessage(request);
	}

}
