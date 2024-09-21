package br.com.app.client;

import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.app.client.service.IUserService;
import br.com.app.client.service.TokenService;

public abstract class ContextBaseTests {
	
	@MockBean
	private TokenService tokenService;

	@MockBean
	private IUserService iUserService;

}
