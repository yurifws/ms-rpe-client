package br.com.app.client.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.app.client.client.CardClient;
import br.com.app.client.exception.BusinessException;
import br.com.app.client.mapper.ClientMapper;
import br.com.app.client.model.CardRequestModel;
import br.com.app.client.model.CardResponseModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.model.FullClientResponseModel;
import br.com.app.client.producer.CardProducer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {
	
	private final CardProducer cardProducer;
	private final CardClient cardClient;
	private final IClientService clientService;
	

	@Override
	public void sendMessage(CardRequestModel cardRequestModel) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = objectWriter.writeValueAsString(cardRequestModel);
			clientService.searchById(cardRequestModel.getClientId());
			cardProducer.sendMessage(json);
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);	
		}
		
	} 
	
	@Override
	public FullClientResponseModel getCardByClientId(Long id) {
		try {
			ClientResponseModel clientResponseModel = clientService.searchById(id);
			List<CardResponseModel> cardResponseModels = cardClient.getCardByClientId(clientResponseModel.getId());
			
			return ClientMapper.INSTANCE.toResponseModel(clientResponseModel, cardResponseModels);
		}catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);
		}
	}

}
