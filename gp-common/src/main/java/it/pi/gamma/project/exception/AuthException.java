package it.pi.gamma.project.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3590753885730470249L;

	public AuthException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	private String code;
	
	private String message;
	
}
