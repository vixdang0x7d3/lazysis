package com.lazygroup.lazysis.exception;

public class InvalidCredentialException extends Exception {
	public InvalidCredentialException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public InvalidCredentialException(String errorMessage) {
		super(errorMessage);
	}
}
