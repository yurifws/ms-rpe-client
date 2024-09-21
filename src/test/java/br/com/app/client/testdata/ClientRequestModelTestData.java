package br.com.app.client.testdata;

import java.time.LocalDate;

import br.com.app.client.enuns.StatusEnum;
import br.com.app.client.model.ClientRequestModel;

public class ClientRequestModelTestData {
	
	public static ClientRequestModel getClientRequestModel() {
		ClientRequestModel model = new ClientRequestModel();
		model.setStatus(StatusEnum.ATIVO);
		model.setName("Name 1");
		model.setDocument("001023145610");
		model.setDateOfBirth(LocalDate.of(1994, 10, 21));
		return model;
	}

}
