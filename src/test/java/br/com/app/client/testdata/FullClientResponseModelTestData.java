package br.com.app.client.testdata;

import static br.com.app.client.testdata.CardResponseModelTestData.getCardResponseModel;

import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.model.FullClientResponseModel;

public class FullClientResponseModelTestData {
	
	public static FullClientResponseModel getFullClientResponseModel() {
		FullClientResponseModel model = new FullClientResponseModel();
		ClientResponseModel clientResponseModel= ClientResponseModelTestData.getClientResponseModel();
		model.setId(clientResponseModel.getId());
		model.setName(clientResponseModel.getName());
		model.setDocument(clientResponseModel.getDocument());
		model.setStatus(clientResponseModel.getStatus());
		model.setDateOfBirth(clientResponseModel.getDateOfBirth());
		model.setDateCreated(clientResponseModel.getDateCreated());
		model.setDateUpdated(clientResponseModel.getDateUpdated());
		model.setCard(getCardResponseModel());
		
		return model;
	}
	

}
