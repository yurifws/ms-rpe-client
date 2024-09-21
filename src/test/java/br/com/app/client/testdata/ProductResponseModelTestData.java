package br.com.app.client.testdata;

import java.time.LocalDateTime;

import br.com.app.client.enuns.StatusEnum;
import br.com.app.client.model.ProductResponseModel;

public class ProductResponseModelTestData {
	
	public static ProductResponseModel getProductResponseModel() {
		ProductResponseModel model = new ProductResponseModel();
		model.setId(12345L);
		model.setStatus(StatusEnum.ATIVO);
		model.setDescription("Produto 1");
		model.setDateCreated(LocalDateTime.of(2023, 11, 25, 11, 12, 13));
		model.setDateUpdated(LocalDateTime.of(2024, 12, 24, 01, 02, 03));
		return model;
	}

}
