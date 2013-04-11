package com.temenos.services.loan.core;

/**
 * Act as a contractor for Loan EMI (Equated Monthly Installment) Calculator
 * Service.
 * 
 * @author BharathRaja
 * 
 */
public interface EMICalculator {

	/**
	 * Responsible for calculate the EMI amount for given loan amount for the
	 * given duration periods.
	 * 
	 * @param loanAmount
	 * @param loanType
	 * @param duration
	 * @return the amount need to be
	 * @throws LoanEMICalculatorException
	 */
	public EMIDetails calculateEMI(double loanAmount, LoanType loanType,
			DurationPeriods duration) throws LoanEMICalculatorException;

}
