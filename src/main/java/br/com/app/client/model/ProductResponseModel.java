package br.com.app.client.model;

import java.time.LocalDateTime;

import br.com.app.client.enuns.StatusEnum;
import lombok.Data;

@Data
public class ProductResponseModel {
	
	private Long id;
	
	private String description;
	
	private StatusEnum status;
	
	private LocalDateTime dateCreated;
	
	private LocalDateTime dateUpdated;

}
