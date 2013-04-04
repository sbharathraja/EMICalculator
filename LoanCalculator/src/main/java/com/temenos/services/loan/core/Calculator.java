package com.temenos.services.loan.core;

/**
 * Act as a contractor for Loan Calculator Service.
 * 
 * @author BharathRaja
 * 
 */
public interface Calculator {

	/**
	 * Responsible for calculate the loan amount for given credit amount for the
	 * given duration periods.
	 * 
	 * @param creditAmount
	 * @param loanType
	 * @param duration
	 * @return the amount need to be
	 * @throws LoanCalculatorException
	 */
	public LoanMetaData calculateLoan(double creditAmount, LoanType loanType,
			DurationPeriods duration) throws LoanCalculatorException;

}
