package br.com.app.client.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.app.client.enuns.ClientStatusEnum;
import lombok.Data;

@Data
public class ClientResponseModel {
	
	private Long id;
	
	private String name;
	
	private String document;
	
	private ClientStatusEnum status;
	
	private LocalDate dateOfBirth;
	
	private LocalDateTime dateCreated;
	
	private LocalDateTime dateUpdated;

}
