package br.com.app.client.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 7605046493676526175L;

	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(String message, Throwable cause){
		super(message, cause);
	}

}
