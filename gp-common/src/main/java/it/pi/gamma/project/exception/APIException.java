package it.pi.gamma.project.exception;

import lombok.Getter;

@Getter
public class APIException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7886527408377866022L;

	public APIException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	private String code;
	
	private String message;
	
}
