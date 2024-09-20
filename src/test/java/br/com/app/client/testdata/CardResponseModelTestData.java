package br.com.app.client.testdata;

import static br.com.app.client.testdata.ProductResponseModelTestData.getProductResponseModel;

import java.time.LocalDateTime;

import br.com.app.client.enuns.StatusEnum;
import br.com.app.client.model.CardResponseModel;

public class CardResponseModelTestData {
	
	public static CardResponseModel getCardResponseModel() {
		CardResponseModel model = new CardResponseModel();
		model.setId(123L);
		model.setNumber("1231234");
		model.setPassword("password");
		model.setStatus(StatusEnum.ATIVO);
		model.setHolderName("holderName");
		model.setDateCreated(LocalDateTime.of(2024, 11, 21, 1, 2, 3));
		model.setDateUpdated(LocalDateTime.of(2023, 10, 20, 0, 5, 10));
		model.setProduct(getProductResponseModel());
		return model;
	}
}
