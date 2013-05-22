package com.sbr.services.loan.core.interest;

import com.sbr.services.loan.core.DurationPeriod;
import com.sbr.services.loan.core.InterestRate;
import com.sbr.services.loan.core.LoanCalculatorCoreException;
import com.sbr.services.loan.core.LoanType;

/**
 * Helper class to helps to calculate the interest rate based on our business
 * functionality.
 * 
 * @author sbharathraja
 * 
 */
public class InterestRateCalculator {

	private static final String COMMON_ERROR = "Error occurs in rate calculation - ";

	/**
	 * Helps to calculate the rated for the given details.
	 * 
	 * @param loanAmount
	 * @param loanType
	 * @param durationPeriod
	 * @return calculated rate.
	 * @throws LoanCalculatorCoreException
	 */
	public InterestRate calculateRate(double loanAmount, LoanType loanType,
			DurationPeriod durationPeriod) throws LoanCalculatorCoreException {
		if (loanAmount <= 0) {
			throw new LoanCalculatorCoreException(COMMON_ERROR
					+ "loan amount is not valid!");
		}
		if (loanType == null || durationPeriod == null) {
			throw new LoanCalculatorCoreException(COMMON_ERROR
					+ "loan type or duration period cannot be null.");
		}
		float rate = calculate(loanAmount, loanType, durationPeriod);
		return new InterestRate(rate);
	}

	private float calculate(double loanAmount, LoanType loanType,
			DurationPeriod durationPeriod) throws LoanCalculatorCoreException {
		switch (loanType) {
		case Education:
			if (durationPeriod.getDuration() <= 0) {
				return 0;
			}
			// minimum interest rate for education loan.
			return 3;
		case Housing:
			return calculate(loanAmount, durationPeriod);
		case Personal:
			return calculate(loanAmount, durationPeriod);
		case Property:
			return calculate(loanAmount, durationPeriod);
		case TwoWheeler:
			if (durationPeriod.getDuration() <= 0) {
				return 0;
			}
			// standard interest rate for two-wheeler loan.
			return 4;
		}
		throw new LoanCalculatorCoreException(COMMON_ERROR
				+ "Invalid loan type.");
	}

	private float calculate(double loanAmount, DurationPeriod durationPeriod)
			throws LoanCalculatorCoreException {
		int period = durationPeriod.getDuration();
		switch (durationPeriod.getTimePeriod()) {
		case Month:
			if (period <= 0) {
				return 0;
			} else if (period < 12) {
				return 5;
			} else if (period > 12 && period < 24) {
				return 6;
			} else {
				return (float) 8.5;
			}
		case Year:
			if (period <= 0) {
				return 0;
			} else if (period == 1 && loanAmount <= 10000) {
				return 5;
			} else if (period <= 2 && loanAmount <= 50000) {
				return 6;
			} else if (loanAmount >= 100000) {
				return 8;
			} else {
				return (float) 5.5;
			}
		}
		throw new LoanCalculatorCoreException(COMMON_ERROR
				+ "Invalid time period.");
	}
}
