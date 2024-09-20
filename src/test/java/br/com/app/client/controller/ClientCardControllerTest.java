package br.com.app.client.controller;

import static br.com.app.client.constants.RestConstants.PATH_CLIENTS_CARDS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.app.client.model.CardRequestModel;
import br.com.app.client.model.FullClientResponseModel;
import br.com.app.client.service.ICardService;
import br.com.app.client.testdata.CardRequestModelTestData;
import br.com.app.client.testdata.FullClientResponseModelTestData;

@WebMvcTest(ClientCardController.class)
class ClientCardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ICardService cardService;

	@Test
	void insert() throws Exception {
		Long id = 1234L;

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
		
		mockMvc.perform(MockMvcRequestBuilders.post(PATH_CLIENTS_CARDS, id)
				.content(body)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isAccepted());

		verify(cardService).sendMessage(request);
	}
	
	@Test
	void findByClientId() throws Exception {
		Long id = 1234L;

		FullClientResponseModel expected = FullClientResponseModelTestData.getFullClientResponseModel();
		when(cardService.getCardByClientId(id)).thenReturn(expected);
		
		mockMvc.perform(MockMvcRequestBuilders.get(PATH_CLIENTS_CARDS, id)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(expected.getId()))
		.andExpect(jsonPath("$.name").value(expected.getName()))
		.andExpect(jsonPath("$.document").value(expected.getDocument()))
		.andExpect(jsonPath("$.dateOfBirth").value(expected.getDateOfBirth().toString()))
		.andExpect(jsonPath("$.status").value(expected.getStatus().name()))
		.andExpect(jsonPath("$.dateCreated").value(expected.getDateCreated().toString()))
		.andExpect(jsonPath("$.dateUpdated").value(expected.getDateUpdated().toString()))
		.andExpect(jsonPath("$.cards[0].id").value(expected.getCards().get(0).getId()))
		.andExpect(jsonPath("$.cards[0].number").value(expected.getCards().get(0).getNumber()))
		.andExpect(jsonPath("$.cards[0].password").value(expected.getCards().get(0).getPassword()))
		.andExpect(jsonPath("$.cards[0].status").value(expected.getCards().get(0).getStatus().name()))
		.andExpect(jsonPath("$.cards[0].holderName").value(expected.getCards().get(0).getHolderName()))
		.andExpect(jsonPath("$.cards[0].dateCreated").value(expected.getCards().get(0).getDateCreated().toString()))
		.andExpect(jsonPath("$.cards[0].dateUpdated").value(expected.getCards().get(0).getDateUpdated().toString()))
		.andExpect(jsonPath("$.cards[0].product.id").value(expected.getCards().get(0).getProduct().getId()))
		.andExpect(jsonPath("$.cards[0].product.description").value(expected.getCards().get(0).getProduct().getDescription()))
		.andExpect(jsonPath("$.cards[0].product.status").value(expected.getCards().get(0).getProduct().getStatus().name()))
		.andExpect(jsonPath("$.cards[0].product.dateCreated").value(expected.getCards().get(0).getProduct().getDateCreated().toString()))
		.andExpect(jsonPath("$.cards[0].product.dateUpdated").value(expected.getCards().get(0).getProduct().getDateUpdated().toString()));

		verify(cardService).getCardByClientId(id);

	}

}
