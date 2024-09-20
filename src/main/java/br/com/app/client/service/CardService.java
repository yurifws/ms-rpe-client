package br.com.app.client.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.app.client.exception.BusinessException;
import br.com.app.client.model.CardRequestModel;
import br.com.app.client.producer.CardProducer;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {
	
	private final CardProducer cardProducer;

	@Override
	public void sendMessage(CardRequestModel cardRequestModel) {
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = objectWriter.writeValueAsString(cardRequestModel);
			cardProducer.sendMessage(json);
		} catch (Exception ex) {
			throw new BusinessException(ex.getMessage(), ex);	
		}
		
	}

}
