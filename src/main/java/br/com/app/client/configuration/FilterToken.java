package br.com.app.client.configuration;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.app.client.entity.UserEntity;
import br.com.app.client.exception.UnauthorizedException;
import br.com.app.client.service.IUserService;
import br.com.app.client.service.TokenService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FilterToken extends OncePerRequestFilter {
	
	private static final String[] URLS_SHOULD_NOT_FILTER_LIST = {"/swagger-ui", "/v3/api-docs", "/actuator"};

	private final TokenService tokenService;
	private final IUserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		String authorizationHeader = request.getHeader("Authorization");

		if(ObjectUtils.isNotEmpty(authorizationHeader)) {
			String token = authorizationHeader.replace("Bearer ", "");
			String subject = tokenService.getSubject(token);

			UserEntity userEntity = userService.findByLogin(subject);

			if(ObjectUtils.isEmpty(userEntity)) {
				throw new UnauthorizedException("Unauthorized");
			}

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
					new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			
		}

		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();
		if(Arrays.stream(URLS_SHOULD_NOT_FILTER_LIST).anyMatch(path::contains)) {
			return true;
		}
		else {
			return false;
		}
	}

}
