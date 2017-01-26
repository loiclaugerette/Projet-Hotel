/*
 * NonValidClassTypeException
 * Version: 1.0.0
 * Date: 06-12-2016
 * Author: Etienne Lorteau
 */

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
