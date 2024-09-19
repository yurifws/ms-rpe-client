package br.com.app.client.model;

import java.time.LocalDateTime;

import br.com.app.client.enuns.StatusEnum;
import lombok.Data;

@Data
public class CardRequestModel {
	
    private String number;
    
    private String password;
    
    private StatusEnum cardStatus;
    
    private String holderName;
    
    private LocalDateTime dateCreated;
    
    private LocalDateTime dateUpdated;
    
    private Long clientId;
    
    private Long productId;
	
}
