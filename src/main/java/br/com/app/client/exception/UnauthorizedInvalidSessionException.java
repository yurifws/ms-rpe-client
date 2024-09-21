package br.com.app.client.exception;

public class UnauthorizedInvalidSessionException extends RuntimeException {

	private static final long serialVersionUID = 5739849757728223637L;

	public UnauthorizedInvalidSessionException(String message){
		super(message);
	}
}
