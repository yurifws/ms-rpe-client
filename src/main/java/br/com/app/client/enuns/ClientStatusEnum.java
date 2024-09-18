package br.com.app.client.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClientStatusEnum {

	ATIVO("ATIVO"),
	BLOQUEADO("BLOQUEADO"),
	CANCELADO("CANCELADO");
	
	private final String value;
}
