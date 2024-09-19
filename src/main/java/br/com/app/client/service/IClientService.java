package br.com.app.client.service;

import java.util.List;

import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;

public interface IClientService {

	List<ClientResponseModel> findAll();
	ClientResponseModel searchById(Long id);  
	ClientResponseModel insert(ClientRequestModel clientRequestModel);
	ClientResponseModel update(Long id, ClientRequestModel clientRequestModel);
	void removeById(Long id);
}
 