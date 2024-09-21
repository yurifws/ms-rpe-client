package br.com.app.client.model;

import java.time.LocalDateTime;

import br.com.app.client.enuns.StatusEnum;
import lombok.Data;

@Data
public class CardResponseModel {
	
    private Long id;
    
    private String number;

    private String password;

    private StatusEnum status;

    private String holderName;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    private ProductResponseModel product;

}
