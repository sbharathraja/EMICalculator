package com.temenos.services.loan.core.interest;

import com.temenos.services.loan.core.LoanEMICalculatorException;

/**
 * Act as a business logic to calculate the interest rate.
 * 
 * @author BharathRaja
 * 
 */
public class InterestRateCalculator {

	/**
	 * Method which is responsible for calculate the interest rate for the given
	 * loan amount.
	 * 
	 * @param loanAmount
	 * @return
	 * @throws LoanEMICalculatorException
	 */
	public InterestRate calculateRate(double loanAmount)
			throws LoanEMICalculatorException {
		if (loanAmount <= 0) {
			throw new LoanEMICalculatorException(
					"Invalid input to calculate the interest rate...");
		}
		if (loanAmount >= 100000 && loanAmount <= 500000) {
			return InterestRate._15;
		} else if (loanAmount > 500000) {
			return InterestRate._10;
		} else {
			return InterestRate._8POINT5;
		}
	}

}
