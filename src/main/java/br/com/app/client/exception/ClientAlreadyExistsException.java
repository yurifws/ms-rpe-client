package br.com.app.client.exception;

public class ClientAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = -3854879613095439808L;
	
	private static final String MSG_CLIENT_ALREADY_EXISTS = "Já existe um cadastro para o cliente de documento %s";

	public ClientAlreadyExistsException(String document) {
		super(String.format(MSG_CLIENT_ALREADY_EXISTS, document));
	}

}
