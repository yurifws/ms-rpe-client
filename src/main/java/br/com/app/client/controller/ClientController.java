package br.com.app.client.controller;

import static br.com.app.client.constants.RestConstants.PATH_VARIABLE_ID;
import static br.com.app.client.constants.RestConstants.PATH_CLIENTS;

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
import br.com.app.client.service.ClientService;
import lombok.RequiredArgsConstructor;

public class ClientController {

}
