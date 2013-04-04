package com.temenos.webservices.loan.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.temenos.services.loan.core.DurationPeriods;
import com.temenos.services.loan.core.LoanCalculatorException;
import com.temenos.services.loan.core.LoanCalculatorFactory;
import com.temenos.services.loan.core.LoanMetaData;
import com.temenos.services.loan.core.LoanType;

/**
 * Act as a Service Endpoint Implementation.
 * 
 * @author sbharathraja
 * 
 */
@WebService(targetNamespace = "http://temenos.com", name = "LoanCalculator")
public class LoanCalculatorService {

	@WebMethod
	public LoanMetaData calculateLoan(double loanAmount, LoanType loanType,
			DurationPeriods durationPeriod) throws LoanCalculatorException {
		return LoanCalculatorFactory.createLoanCalculator().calculateLoan(
				loanAmount, loanType, durationPeriod);
	}

}
