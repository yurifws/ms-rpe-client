package br.com.app.client.controller;

import static br.com.app.client.constants.RestConstants.PATH_CARDS;
import static br.com.app.client.constants.RestConstants.PATH_CLIENTS;
import static br.com.app.client.constants.RestConstants.PATH_VARIABLE_ID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.client.model.CardRequestModel;
import br.com.app.client.model.FullClientResponseModel;
import br.com.app.client.service.ICardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = PATH_CLIENTS+PATH_VARIABLE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientCardController {
	
	private final ICardService cardService;

	@PostMapping(path = PATH_CARDS)
	public ResponseEntity<Void> sendMessage(
			@PathVariable Long id,
			@RequestBody CardRequestModel cardRequestModel) {
		cardService.sendMessage(cardRequestModel);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@GetMapping(path = PATH_CARDS)
	public ResponseEntity<FullClientResponseModel> findByClientId(
			@PathVariable Long id) {
		return ResponseEntity.ok(cardService.getCardByClientId(id));
	}

}
