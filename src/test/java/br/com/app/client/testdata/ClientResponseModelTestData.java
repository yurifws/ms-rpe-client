package br.com.app.client.testdata;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.app.client.enuns.ClientStatusEnum;
import br.com.app.client.model.ClientResponseModel;

public class ClientResponseModelTestData {
	
	public static ClientResponseModel getClientResponseModel() {
		ClientResponseModel model = new ClientResponseModel();
		model.setId(1234L);
		model.setStatus(ClientStatusEnum.ATIVO);
		model.setName("Name 1");
		model.setDocument("001023145610");
		model.setDateOfBirth(LocalDate.of(1994, 10, 21));
		model.setDateCreated(LocalDateTime.of(2023, 11, 25, 11, 12, 13));
		model.setDateUpdated(LocalDateTime.of(2024, 12, 24, 01, 02, 03));
		return model;
	}

}
