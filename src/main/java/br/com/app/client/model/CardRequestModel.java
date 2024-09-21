package br.com.app.client.model;

import br.com.app.client.enuns.StatusEnum;
import lombok.Data;

@Data
public class CardRequestModel {
	
    private String number;
    
    private String password;
    
    private StatusEnum status;
    
    private String holderName;
    
    private Long clientId;
    
    private Long productId;
	
}
