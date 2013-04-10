package com.temenos.webservices.loan.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
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
@WebService(targetNamespace = "http://temenos.com")
public class LoanCalculator {

	@WebMethod
	@WebResult(name = "LoanDetails")
	public LoanMetaData calculateLoan(
			@WebParam(name = "loanAmount") double loanAmount,
			@WebParam(name = "loanType") LoanType loanType,
			@WebParam(name = "durationPeriod") DurationPeriods durationPeriod)
			throws LoanCalculatorException {
		return LoanCalculatorFactory.createLoanCalculator().calculateLoan(
				loanAmount, loanType, durationPeriod);
	}

}
