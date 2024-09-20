package br.com.app.client.service;

import org.springframework.stereotype.Service;

import br.com.app.client.entity.UserEntity;
import br.com.app.client.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {
	
	private final UserRepository userRepository;

	@Override
	public UserEntity findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

}
