package br.com.app.client.exception;

public abstract class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = 982419271311669969L;

	public EntityNotFoundException(String message){
		super(message);
	}
}
