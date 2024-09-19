package br.com.app.client.controller;

import static br.com.app.client.constants.RestConstants.PATH_CARDS;
import static br.com.app.client.constants.RestConstants.PATH_CLIENTS;
import static br.com.app.client.constants.RestConstants.PATH_VARIABLE_ID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.client.model.CardRequestModel;
import br.com.app.client.service.ICardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = PATH_CLIENTS+PATH_VARIABLE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientCardController {
	
	private final ICardService cardService;

	@PostMapping(path = PATH_CARDS)
	public ResponseEntity<Void> sendMessage(
			@RequestBody CardRequestModel cardRequestModel) {
		cardService.sendMessage(cardRequestModel);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
