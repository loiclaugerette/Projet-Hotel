/*
 * NoProduitInStockException
 * Version: 1.0.0
 * Date: 07-12-2016
 * Author: Etienne Lorteau
 */

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
