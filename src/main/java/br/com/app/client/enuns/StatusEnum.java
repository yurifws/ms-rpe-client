package br.com.app.client.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusEnum {

	ATIVO("ATIVO"),
	BLOQUEADO("BLOQUEADO"),
	CANCELADO("CANCELADO");
	
	private final String value;
}
