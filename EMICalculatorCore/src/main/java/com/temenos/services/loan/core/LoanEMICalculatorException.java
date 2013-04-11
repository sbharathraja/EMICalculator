package com.temenos.services.loan.core;

/**
 * Generic type of exception.
 * 
 * @author BharathRaja
 * 
 */
public class LoanEMICalculatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoanEMICalculatorException() {
		super();
	}

	public LoanEMICalculatorException(String message) {
		super(message);
	}

	public LoanEMICalculatorException(Throwable cause) {
		super(cause);
	}

	public LoanEMICalculatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
