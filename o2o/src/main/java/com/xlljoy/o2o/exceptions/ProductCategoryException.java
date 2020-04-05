package com.xlljoy.o2o.exceptions;

public class ProductCategoryException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4811379109664986110L;
	 
	public ProductCategoryException (String errMsg) {
		super(errMsg);
	}
}
