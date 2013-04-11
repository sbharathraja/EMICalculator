package com.temenos.webservices.loan.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.temenos.services.loan.core.DurationPeriods;
import com.temenos.services.loan.core.EMIDetails;
import com.temenos.services.loan.core.LoanEMICalculatorException;
import com.temenos.services.loan.core.LoanEMICalculatorFactory;
import com.temenos.services.loan.core.LoanType;

/**
 * Act as a Service Endpoint Implementation.
 * 
 * @author sbharathraja
 * 
 */
@WebService(targetNamespace = "http://temenos.com")
public class EMICalculator {

	@WebMethod
	@WebResult(name = "EMIDetails")
	public EMIDetails calculateEmi(
			@WebParam(name = "loanAmount") double loanAmount,
			@WebParam(name = "loanType") LoanType loanType,
			@WebParam(name = "durationPeriod") DurationPeriods durationPeriod)
			throws LoanEMICalculatorException {
		return LoanEMICalculatorFactory.createLoanEMICalculator().calculateEMI(
				loanAmount, loanType, durationPeriod);
	}

}
