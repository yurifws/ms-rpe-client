package br.com.app.client.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.app.client.entity.ClientEntity;
import br.com.app.client.model.CardResponseModel;
import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.model.FullClientResponseModel;
import br.com.app.client.testdata.CardResponseModelTestData;
import br.com.app.client.testdata.ClientEntityTestData;
import br.com.app.client.testdata.ClientRequestModelTestData;
import br.com.app.client.testdata.ClientResponseModelTestData;

@SpringBootTest
class ClientMapperTest {
	
	@Test
	void toEntity() {
		ClientRequestModel expected = ClientRequestModelTestData.getClientRequestModel();
		ClientEntity actual = ClientMapper.INSTANCE.toEntity(expected);
		
		assertNotNull(actual);
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDocument(), actual.getDocument());
		assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
		assertEquals(expected.getStatus(), actual.getStatus());
	}
	
	@Test
	void toResponseModel() {
		ClientEntity expected = ClientEntityTestData.getClientEntity();
		ClientResponseModel actual = ClientMapper.INSTANCE.toResponseModel(expected);
		
		assertNotNull(actual);
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDocument(), actual.getDocument());
		assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
		assertEquals(expected.getStatus(), actual.getStatus());
		assertEquals(expected.getDateCreated(), actual.getDateCreated());
		assertEquals(expected.getDateUpdated(), actual.getDateUpdated());
	}

	@Test
	void toListResponseModel() {
		
		ClientEntity expected = ClientEntityTestData.getClientEntity();
		List<ClientResponseModel> response = ClientMapper.INSTANCE.toListResponseModel(List.of(expected));
		
		assertFalse(response.isEmpty());
		
		ClientResponseModel actual = response.get(0);

		assertNotNull(actual);
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDocument(), actual.getDocument());
		assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
		assertEquals(expected.getStatus(), actual.getStatus());
		assertEquals(expected.getDateCreated(), actual.getDateCreated());
		assertEquals(expected.getDateUpdated(), actual.getDateUpdated());
	}
	
	@Test
	void toFullResponseModel() {
		
		ClientResponseModel clientResponseModel = ClientResponseModelTestData.getClientResponseModel();
		CardResponseModel cardResponseModel = CardResponseModelTestData.getCardResponseModel();
		FullClientResponseModel actual = ClientMapper.INSTANCE.toResponseModel(clientResponseModel, cardResponseModel);
		
		assertNotNull(actual);
		assertEquals(clientResponseModel.getId(), actual.getId());
		assertEquals(clientResponseModel.getName(), actual.getName());
		assertEquals(clientResponseModel.getDocument(), actual.getDocument());
		assertEquals(clientResponseModel.getDateOfBirth(), actual.getDateOfBirth());
		assertEquals(clientResponseModel.getStatus(), actual.getStatus());
		assertEquals(clientResponseModel.getDateCreated(), actual.getDateCreated());
		assertEquals(clientResponseModel.getDateUpdated(), actual.getDateUpdated());
		assertEquals(cardResponseModel.getId(), actual.getCard().getId());
		assertEquals(cardResponseModel.getNumber(), actual.getCard().getNumber());
		assertEquals(cardResponseModel.getPassword(), actual.getCard().getPassword());
		assertEquals(cardResponseModel.getStatus(), actual.getCard().getStatus());
		assertEquals(cardResponseModel.getHolderName(), actual.getCard().getHolderName());
		assertEquals(cardResponseModel.getDateCreated(), actual.getCard().getDateCreated());
		assertEquals(cardResponseModel.getDateUpdated(), actual.getCard().getDateUpdated());
		assertEquals(cardResponseModel.getProduct().getId(), actual.getCard().getProduct().getId());
		assertEquals(cardResponseModel.getProduct().getDescription(), actual.getCard().getProduct().getDescription());
		assertEquals(cardResponseModel.getProduct().getStatus(), actual.getCard().getProduct().getStatus());
		assertEquals(cardResponseModel.getProduct().getDateCreated(), actual.getCard().getProduct().getDateCreated());
		assertEquals(cardResponseModel.getProduct().getDateUpdated(), actual.getCard().getProduct().getDateUpdated());
	}
}
