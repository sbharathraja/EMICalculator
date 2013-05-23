package com.sbr.soap.services.loan;

import javax.jws.WebMethod;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.sbr.services.loan.core.DurationPeriod;
import com.sbr.services.loan.core.Emi;
import com.sbr.services.loan.core.EmiData;
import com.sbr.services.loan.core.InterestRate;
import com.sbr.services.loan.core.LoanCalculatorCoreException;
import com.sbr.services.loan.core.LoanCalculatorFactory;
import com.sbr.services.loan.core.LoanType;

/**
 * Act as a Service Endpoint Implementation.
 * 
 * @author sbharathraja
 * 
 */
@WebService(targetNamespace = "http://loan.services.sbr.com")
public class LoanCalculator {

	private com.sbr.services.loan.core.LoanCalculator calculator;

	public LoanCalculator() {
		this.calculator = new LoanCalculatorFactory().createLoanCalculator();
	}

	@WebMethod
	@WebResult(name = "EmiData")
	public EmiData calculateEmi(
			@WebParam(name = "principalAmount") double principalAmount,
			@WebParam(name = "LoanType") LoanType loanType,
			@WebParam(name = "DurationPeriod") DurationPeriod durationPeriod)
			throws LoanCalculatorFault {
		try {
			return calculator.calculateEmi(principalAmount, loanType,
					durationPeriod);
		} catch (LoanCalculatorCoreException e) {
			throw new LoanCalculatorFault(e.getMessage(), e,
					new LoanCalculatorFaultBean(e.getMessage()));
		}
	}

	@WebMethod
	@WebResult(name = "DurationPeriod")
	public DurationPeriod calculateDuration(
			@WebParam(name = "principalAmount") double principalAmount,
			@WebParam(name = "InterestRate") InterestRate interestRate,
			@WebParam(name = "totalInterestAmountPayable") double totalInterestAmount)
			throws LoanCalculatorFault {
		try {
			return calculator.calculateDuration(principalAmount, interestRate,
					totalInterestAmount);
		} catch (LoanCalculatorCoreException e) {
			throw new LoanCalculatorFault(e.getMessage(), e,
					new LoanCalculatorFaultBean(e.getMessage()));
		}
	}

	@WebMethod
	@WebResult(name = "InterestRate")
	public InterestRate calculateInterestRate(
			@WebParam(name = "principalAmount") double loanAmount,
			@WebParam(name = "DurationPeriod") DurationPeriod durationPeriod,
			@WebParam(name = "totalInterestAmountPayable") double totalInterestAmount)
			throws LoanCalculatorFault {
		try {
			return calculator.calculateInterestRate(loanAmount, durationPeriod,
					totalInterestAmount);
		} catch (LoanCalculatorCoreException e) {
			throw new LoanCalculatorFault(e.getMessage(), e,
					new LoanCalculatorFaultBean(e.getMessage()));
		}
	}

	@WebMethod
	@WebResult(name = "InterestRate")
	public InterestRate calculateInterestRateFromEmi(
			@WebParam(name = "principalAmount") double loanAmount,
			@WebParam(name = "DurationPeriod") DurationPeriod durationPeriod,
			@WebParam(name = "Emi") Emi emi) throws LoanCalculatorFault {
		try {
			return calculator.calculateInterestRate(loanAmount, durationPeriod,
					emi);
		} catch (LoanCalculatorCoreException e) {
			throw new LoanCalculatorFault(e.getMessage(), e,
					new LoanCalculatorFaultBean(e.getMessage()));
		}
	}

	@WebMethod
	@WebResult(name = "principalAmount")
	public double calculatePrincipalAmount(
			@WebParam(name = "totalInterestAmountPayable") double totalInterestAmount,
			@WebParam(name = "DurationPeriod") DurationPeriod durationPeriod,
			@WebParam(name = "InterestRate") InterestRate interestRate)
			throws LoanCalculatorFault {
		try {
			return calculator.calculatePrincipalAmount(totalInterestAmount,
					durationPeriod, interestRate);
		} catch (LoanCalculatorCoreException e) {
			throw new LoanCalculatorFault(e.getMessage(), e,
					new LoanCalculatorFaultBean(e.getMessage()));
		}
	}
}
