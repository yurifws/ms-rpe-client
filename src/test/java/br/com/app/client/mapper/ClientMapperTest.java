package br.com.app.client.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.app.client.entity.ClientEntity;
import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.testdata.ClientEntityTestData;
import br.com.app.client.testdata.ClientRequestModelTestData;

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
}
