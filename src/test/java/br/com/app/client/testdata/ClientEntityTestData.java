package br.com.app.client.testdata;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.app.client.entity.ClientEntity;
import br.com.app.client.enuns.StatusEnum;

public class ClientEntityTestData {
	
	public static ClientEntity getClientEntity() {
		ClientEntity model = new ClientEntity();
		model.setId(1234L);
		model.setStatus(StatusEnum.ATIVO);
		model.setName("Name 1");
		model.setDocument("001023145610");
		model.setDateOfBirth(LocalDate.of(1994, 10, 21));
		model.setDateCreated(LocalDateTime.of(2023, 11, 25, 11, 12, 13));
		model.setDateUpdated(LocalDateTime.of(2024, 12, 24, 01, 02, 03));
		return model;
	}

}
