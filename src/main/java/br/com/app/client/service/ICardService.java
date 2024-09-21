package br.com.app.client.service;

import br.com.app.client.model.CardRequestModel;
import br.com.app.client.model.FullClientResponseModel;

public interface ICardService {
	
	void sendMessage(CardRequestModel cardRequestModel);
	FullClientResponseModel getCardByClientId(Long id);

}
