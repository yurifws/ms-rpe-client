package br.com.app.client.configuration;

import static br.com.app.client.constants.RestConstants.PATH_LOGIN;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Profile("!test")
@SecurityScheme(name = WebSecurityConfig.SCHEMA_AUTH, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class WebSecurityConfig {
	
	public static final String SCHEMA_AUTH = "bearerAuth";
	
	private final FilterToken filterToken;

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests((authorize) -> authorize
				.antMatchers(PATH_LOGIN, "/swagger-ui/**", "/v3/api-docs/**", "/actuator").permitAll()
				.anyRequest().authenticated()
				)
		.csrf().disable()
		.cors().disable()
		.httpBasic().disable();
		return http.build();
	}

}
