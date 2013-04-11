package com.temenos.services.loan.core;

import com.temenos.services.loan.core.interest.InterestRate;
import com.temenos.services.loan.core.interest.InterestRateCalculator;

/**
 * Act as a main business logic application calculator
 * 
 * @author BharathRaja
 * 
 */
class LoanCalculator implements EMICalculator {

	private InterestRateCalculator interestRateCalculator;

	@Override
	public EMIDetails calculateEMI(double loanAmount, LoanType loanType,
			DurationPeriods duration) throws LoanEMICalculatorException {
		if (duration == null) {
			throw new LoanEMICalculatorException("Invalid duration!");
		}
		interestRateCalculator = new InterestRateCalculator();
		InterestRate interestRate = interestRateCalculator
				.calculateRate(loanAmount);
		double totalInterestAmount = calculateTotalInterestAmount(loanAmount,
				interestRate, duration);
		double amountToBePaidPerMonth = calculateAmountToBePaidPerMonth(
				loanAmount, totalInterestAmount, duration);
		EMIDetails loanData = new EMIDetails(loanAmount, loanType,
				duration);
		loanData.setEmi(amountToBePaidPerMonth);
		loanData.setInterestRate(interestRate);
		loanData.setTotalInterestAmount(totalInterestAmount);

		return loanData;
	}

	private double calculateAmountToBePaidPerMonth(double loanAmount,
			double totalInterestAmount, DurationPeriods duration) {
		double period = 0.0;
		switch (duration) {
		case _45DAYS:
			period = Double.valueOf(45) / Double.valueOf(60);
			break;
		case _3MONTHS:
			period = 3;
			break;
		case _6MONTHS:
			period = 6;
			break;
		case _9MONTHS:
			period = 9;
			break;
		case _12MONTHS:
			period = 12;
			break;
		case _1YEAR_3MONTHS:
			period = 15;
			break;
		case _1YEAR_6MONTHS:
			period = 18;
			break;
		case _1YEAR_9MONTHS:
			period = 21;
			break;
		case _1YEAR_12MONTHS:
			period = 24;
			break;
		case _3YEARS:
			period = 36;
			break;
		}

		return (loanAmount + totalInterestAmount) / period;
	}

	/**
	 * Helps to calculate the total interest amount to be paid.
	 * 
	 * @param creditAmount
	 * @param interestRate
	 * @param duration
	 * @return total interest amount to be paid.
	 */
	private double calculateTotalInterestAmount(double creditAmount,
			InterestRate interestRate, DurationPeriods duration) {
		double rate = 0.0;
		switch (interestRate) {
		case _8POINT5:
			rate = 8.50;
			break;
		case _10:
			rate = 10.00;
			break;
		case _15:
			rate = 15.00;
			break;
		}
		double period = 0.0;
		switch (duration) {
		case _45DAYS:
			period = Double.valueOf(45) / Double.valueOf(365);
			break;
		case _3MONTHS:
			period = Double.valueOf(3) / Double.valueOf(12);
			break;
		case _6MONTHS:
			period = Double.valueOf(6) / Double.valueOf(12);
			break;
		case _9MONTHS:
			period = Double.valueOf(9) / Double.valueOf(12);
			break;
		case _12MONTHS:
			period = Double.valueOf(12) / Double.valueOf(12);
			break;
		case _1YEAR_3MONTHS:
			period = (Double.valueOf(1) + Double.valueOf((3 / 12)));
			break;
		case _1YEAR_6MONTHS:
			period = (Double.valueOf(1) + Double.valueOf((6 / 12)));
			break;
		case _1YEAR_9MONTHS:
			period = (Double.valueOf(1) + Double.valueOf((9 / 12)));
			break;
		case _1YEAR_12MONTHS:
			period = (Double.valueOf(1) + Double.valueOf((12 / 12)));
			break;
		case _3YEARS:
			period = Double.valueOf(3);
			break;
		}
		// calculate simple interest...
		return Double.valueOf(Double
				.valueOf(((creditAmount * rate * period)) / 100));
	}

}
