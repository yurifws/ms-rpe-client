package br.com.app.client.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.app.client.enuns.StatusEnum;
import lombok.Data;

@Data
public class ClientResponseModel {
	
	private Long id;
	
	private String name;
	
	private String document;
	
	private StatusEnum status;
	
	private LocalDate dateOfBirth;
	
	private LocalDateTime dateCreated;
	
	private LocalDateTime dateUpdated;

}
