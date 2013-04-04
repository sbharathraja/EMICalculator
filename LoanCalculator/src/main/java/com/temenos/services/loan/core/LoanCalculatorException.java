package com.temenos.services.loan.core;

/**
 * Generic type of exception.
 * 
 * @author BharathRaja
 * 
 */
public class LoanCalculatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoanCalculatorException() {
		super();
	}

	public LoanCalculatorException(String message) {
		super(message);
	}

	public LoanCalculatorException(Throwable cause) {
		super(cause);
	}

	public LoanCalculatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
