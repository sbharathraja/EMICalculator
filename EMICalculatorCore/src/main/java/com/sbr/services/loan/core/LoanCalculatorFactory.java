package com.sbr.services.loan.core;

/**
 * Factory class which provides the factory methods to operate on loan
 * calculator.
 * 
 * @author sbharathraja
 * 
 */
public class LoanCalculatorFactory {

	/**
	 * Factory method to helps to create the loan calculator.
	 * 
	 * @return instance of {@link LoanCalculator}
	 */
	public LoanCalculator createLoanCalculator() {
		return new LoanCalculatorImpl();
	}

}
