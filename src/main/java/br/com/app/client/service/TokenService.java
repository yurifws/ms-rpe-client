package br.com.app.client.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

import br.com.app.client.entity.UserEntity;
import br.com.app.client.exception.UnauthorizedInvalidSessionException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {

	private static final String CLIENTS = "Clients";
	
	@Value("${algorithm.secret}")
	private String secret;

	public String generateToken(UserEntity userEntity) throws UsernameNotFoundException {
		return JWT.create()
				.withIssuer(CLIENTS)
				.withSubject(userEntity.getLogin())
				.withClaim("id", userEntity.getId())
				.withExpiresAt(LocalDateTime.now()
						.plusSeconds(60)
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256(secret));

	}


	public String getSubject(String token) {
		try {
			return JWT.require(Algorithm.HMAC256(secret))
					.withIssuer(CLIENTS)
					.build()
					.verify(token)
					.getSubject();
			
		}catch (TokenExpiredException e) {
			throw new UnauthorizedInvalidSessionException("Unauthorized - invalid session");
		}
	}

}
