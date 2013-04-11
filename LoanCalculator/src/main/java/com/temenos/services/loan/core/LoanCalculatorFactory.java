package com.temenos.services.loan.core;

/**
 * Act as a factory class to create instance to help out loan calculator
 * application.
 * 
 * @author sbharathraja
 * 
 */
public class LoanCalculatorFactory {
	
	
	public static EMICalculator createLoanCalculator(){
		return new LoanCalculator();
	}

}
