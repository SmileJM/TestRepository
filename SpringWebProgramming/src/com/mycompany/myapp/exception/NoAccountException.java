package com.mycompany.myapp.exception;

public class NoAccountException extends RuntimeException{
	public NoAccountException() {
		
	}
	public NoAccountException(String message) {
		super(message);
	}
	
}
