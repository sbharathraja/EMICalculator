package com.sbr.soap.services.loan.adapter;

import com.sbr.services.loan.core.DurationPeriod;
import com.sbr.services.loan.core.EmiData;
import com.sbr.services.loan.core.InterestRate;
import com.sbr.soap.services.loan.LoanCalculator;
import com.sbr.soap.services.loan.LoanCalculatorFault;
import com.sbr.soap.services.loan.jaxws.CalculateDuration;
import com.sbr.soap.services.loan.jaxws.CalculateDurationResponse;
import com.sbr.soap.services.loan.jaxws.CalculateEmi;
import com.sbr.soap.services.loan.jaxws.CalculateEmiResponse;
import com.sbr.soap.services.loan.jaxws.CalculateInterestRate;
import com.sbr.soap.services.loan.jaxws.CalculateInterestRateFromEmi;
import com.sbr.soap.services.loan.jaxws.CalculateInterestRateFromEmiResponse;
import com.sbr.soap.services.loan.jaxws.CalculateInterestRateResponse;
import com.sbr.soap.services.loan.jaxws.CalculatePrincipalAmount;
import com.sbr.soap.services.loan.jaxws.CalculatePrincipalAmountResponse;

/**
 * Act as a adapter for Loan Calculator Service and responsible for converting
 * the incoming request to proper response.
 * <p>
 * <b>Note: </b>
 * <p>
 * JAX-WS for SOAP servers are not supported on Google App Engine. Hence we need
 * to write this. Please refer <a
 * href="https://developers.google.com/appengine/articles/soap"
 * >https://developers.google.com/appengine/articles/soap</a>
 * 
 * @author sbharathraja
 * 
 */
public class LoanCalculatorAdapter {

	private LoanCalculator loanCalculator;

	/**
	 * Constructs the {@link LoanCalculatorAdapter}
	 */
	public LoanCalculatorAdapter() {
		this.loanCalculator = new LoanCalculator();
	}

	/**
	 * adapter operation to calculate the EMI.
	 * 
	 * @param request
	 * @return response in instance of {@link CalculateEmiResponse}
	 * @throws LoanCalculatorFault
	 */
	public CalculateEmiResponse calculateEmiOperation(CalculateEmi request)
			throws LoanCalculatorFault {
		// call the appropriate method and get the result
		EmiData result = loanCalculator.calculateEmi(
				request.getPrincipalAmount(), request.getLoanType(),
				request.getDurationPeriod());
		// construct the response with the result.
		CalculateEmiResponse response = new CalculateEmiResponse();
		response.setEmiData(result);

		return response;
	}

	/**
	 * adapter operation to calculate the duration.
	 * 
	 * @param request
	 * @return response in instance of {@link CalculateDurationResponse}
	 * @throws LoanCalculatorFault
	 */
	public CalculateDurationResponse calculateDurationOperation(
			CalculateDuration request) throws LoanCalculatorFault {
		// call the appropriate method and get the result
		DurationPeriod result = loanCalculator.calculateDuration(
				request.getPrincipalAmount(), request.getInterestRate(),
				request.getTotalInterestAmountPayable());
		// construct the response with the result.
		CalculateDurationResponse response = new CalculateDurationResponse();
		response.setDurationPeriod(result);

		return response;
	}

	/**
	 * adapter operation to calculate the interest rate.
	 * 
	 * @param request
	 * @return response in instance of {@link CalculateInterestRateResponse}
	 * @throws LoanCalculatorFault
	 */
	public CalculateInterestRateResponse calculateInterestRateOperation(
			CalculateInterestRate request) throws LoanCalculatorFault {
		// call the appropriate method and get the result
		InterestRate result = loanCalculator.calculateInterestRate(
				request.getPrincipalAmount(), request.getDurationPeriod(),
				request.getTotalInterestAmountPayable());
		// construct the response with the result.
		CalculateInterestRateResponse response = new CalculateInterestRateResponse();
		response.setInterestRate(result);

		return response;
	}

	/**
	 * adapter operation to calculate the interest rate from emi.
	 * 
	 * @param request
	 * @return response in instance of
	 *         {@link CalculateInterestRateFromEmiResponse}
	 * @throws LoanCalculatorFault
	 */
	public CalculateInterestRateFromEmiResponse calculateInterestRateFromEmiOperation(
			CalculateInterestRateFromEmi request) throws LoanCalculatorFault {
		// call the appropriate method and get the result
		InterestRate result = loanCalculator.calculateInterestRateFromEmi(
				request.getPrincipalAmount(), request.getDurationPeriod(),
				request.getEmi());
		// construct the response with the result.
		CalculateInterestRateFromEmiResponse response = new CalculateInterestRateFromEmiResponse();
		response.setInterestRate(result);

		return response;
	}

	/**
	 * adapter operation to calculate the principal amount.
	 * 
	 * @param request
	 * @return response in instance of {@link CalculatePrincipalAmountResponse}
	 * @throws LoanCalculatorFault
	 */
	public CalculatePrincipalAmountResponse calculatePrincipalAmountOperation(
			CalculatePrincipalAmount request) throws LoanCalculatorFault {
		// call the appropriate method and get the result
		double result = loanCalculator.calculatePrincipalAmount(
				request.getTotalInterestAmountPayable(),
				request.getDurationPeriod(), request.getInterestRate());
		// construct the response with the result.
		CalculatePrincipalAmountResponse response = new CalculatePrincipalAmountResponse();
		response.setPrincipalAmount(result);

		return response;
	}
}
