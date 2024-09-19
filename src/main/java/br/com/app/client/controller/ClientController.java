package br.com.app.client.controller;

import static br.com.app.client.constants.RestConstants.PATH_CLIENTS;
import static br.com.app.client.constants.RestConstants.PATH_VARIABLE_ID;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.client.model.ClientRequestModel;
import br.com.app.client.model.ClientResponseModel;
import br.com.app.client.service.IClientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = PATH_CLIENTS, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientController {
	
	private final IClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<ClientResponseModel>> findAll() {
		return ResponseEntity.ok(clientService.findAll());
	}
	
	@GetMapping(PATH_VARIABLE_ID)
	public ResponseEntity<ClientResponseModel> searchById(@PathVariable Long id) {
		return ResponseEntity.ok(clientService.searchById(id));
	}

	@PostMapping
	public ResponseEntity<ClientResponseModel> insert(
			@RequestBody ClientRequestModel clientRequestModel) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(clientService.insert(clientRequestModel));
	}

	@PutMapping(PATH_VARIABLE_ID)
	public ResponseEntity<ClientResponseModel> update(@PathVariable Long id, 
			@RequestBody ClientRequestModel clientRequestModel) {
		return ResponseEntity.ok(clientService.update(id, clientRequestModel));
	}

	@DeleteMapping(PATH_VARIABLE_ID)
	public ResponseEntity<Void> removeById(@PathVariable Long id) {
		clientService.removeById(id);
		return ResponseEntity.noContent().build();
	}

}
