package br.com.app.client.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
		String json;
		try {
			json = objectWriter.writeValueAsString(cardRequestModel);
			cardProducer.sendMessage(json);
		} catch (Exception e) {
		}
		
	}

}
