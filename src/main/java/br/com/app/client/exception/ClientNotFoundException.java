package br.com.app.client.exception;

public class ClientNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 7070588023428343146L;
	
	private static final String MSG_CLIENT_NOT_FOUND = "Não existe um cadastro de cliente com código %d";

	public ClientNotFoundException(String mensagem) {
		super(mensagem);
	}

	public ClientNotFoundException(Long clientId) {
		this(String.format(MSG_CLIENT_NOT_FOUND, clientId));
	}
}
