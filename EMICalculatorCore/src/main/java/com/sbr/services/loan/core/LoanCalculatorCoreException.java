package com.sbr.services.loan.core;

/**
 * Defines the exception been occured loan calculator core.
 * 
 * @author sbharathraja
 * 
 */
public class LoanCalculatorCoreException extends Exception {

	/**
	 * serial version uid.
	 */
	private static final long serialVersionUID = 2086362411138367510L;

	/**
	 * Creates the empty core exception.
	 */
	public LoanCalculatorCoreException() {
		super();
	}

	/**
	 * Creates the core exception with given message.
	 * 
	 * @param message
	 */
	public LoanCalculatorCoreException(String message) {
		super(message);
	}

	/**
	 * Creates the core exception with given cause.
	 * 
	 * @param cause
	 */
	public LoanCalculatorCoreException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates the core exception with given cause and message.
	 * 
	 * @param message
	 * @param cause
	 */
	public LoanCalculatorCoreException(String message, Throwable cause) {
		super(message, cause);
	}

}
