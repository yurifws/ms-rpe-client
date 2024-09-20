package br.com.app.client.service;

import br.com.app.client.entity.UserEntity;

public interface IUserService {
	
	UserEntity findByLogin(String login);
}
 