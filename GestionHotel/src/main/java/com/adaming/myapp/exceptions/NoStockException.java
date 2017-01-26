/*
 * NoStockException
 * Version: 1.0.0
 * Date: 07-12-2016
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class NoStockException extends Exception {

	public NoStockException() {
	}

	public NoStockException(String message) {
		super(message);
	}

	public NoStockException(Throwable cause) {
		super(cause);
	}

	public NoStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoStockException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
