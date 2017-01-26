package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class NonValidClassTypeException extends Exception {

	public NonValidClassTypeException() {
	}

	public NonValidClassTypeException(String message) {
		super(message);
	}

	public NonValidClassTypeException(Throwable cause) {
		super(cause);
	}

	public NonValidClassTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonValidClassTypeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
