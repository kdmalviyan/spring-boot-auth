package com.example.authentication.exceptions;

public class UserDiabledException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDiabledException(String msg){
		super(msg);
	}
}
