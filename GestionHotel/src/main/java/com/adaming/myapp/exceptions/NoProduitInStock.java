package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class NoProduitInStock extends Exception {

	public NoProduitInStock() {
	}

	public NoProduitInStock(String message) {
		super(message);
	}

	public NoProduitInStock(Throwable cause) {
		super(cause);
	}

	public NoProduitInStock(String message, Throwable cause) {
		super(message, cause);
	}

	public NoProduitInStock(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
