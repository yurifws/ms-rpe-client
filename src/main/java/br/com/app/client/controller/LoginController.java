package br.com.app.client.controller;

import static br.com.app.client.constants.RestConstants.PATH_LOGIN;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.client.entity.UserEntity;
import br.com.app.client.model.LoginRequestModel;
import br.com.app.client.service.TokenService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = PATH_LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
	
	private final AuthenticationManager authenticationManager;
	
	private final TokenService tokenService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public String login(@RequestBody LoginRequestModel loginRequestModel) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestModel.getLogin(), loginRequestModel.getPassword());
		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		UserEntity userEntity = (UserEntity) authenticate.getPrincipal();
		return tokenService.generateToken(userEntity);
	}
	

}
