package br.com.app.client.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.app.client.enuns.ClientStatusEnum;
import lombok.Data;

@Data
public class ClientRequestModel {
	
	private String name;
	
	private String document;
	
	private ClientStatusEnum status;
	
	@JsonFormat(pattern = "yyyyMMdd")
	private LocalDate dateOfBirth;
}
