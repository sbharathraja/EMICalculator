package com.sbr.services.loan.core;

import java.util.logging.Logger;

import com.sbr.services.loan.core.interest.InterestRateCalculator;

/**
 * Implementation of {@link LoanCalculator}
 * 
 * @author sbharathraja
 * 
 */
class LoanCalculatorImpl implements LoanCalculator {

	private InterestRateCalculator rateCalculator;

	private static final Logger LOG = Logger.getLogger(LoanCalculatorImpl.class
			.getName());

	LoanCalculatorImpl() {
		this.rateCalculator = new InterestRateCalculator();
	}

	@Override
	public EmiData calculateEmi(double loanAmount, LoanType loanType,
			DurationPeriod durationPeriod) throws LoanCalculatorCoreException {
		if (durationPeriod == null || loanType == null) {
			throw new LoanCalculatorCoreException(
					"Duration period or loan type cannot be null.");
		}
		LOG.finest("EMI calculation process is initiated.");
		LOG.finer("loan amount is [" + loanAmount + "]" + " loan type is ["
				+ loanType + "]" + " duration period is ["
				+ durationPeriod.getDurationPeriodAsString() + "]");

		InterestRate rate = rateCalculator.calculateRate(loanAmount, loanType,
				durationPeriod);
		LOG.finer("Calculated interest rate is : " + rate.getRateAsString());
		double numberOfYears = getNumberOfYears(durationPeriod);
		LOG.finer("number of years [" + numberOfYears + "]");
		// calculating the simple interest
		double totalInterestAmount = ((loanAmount * numberOfYears * rate
				.getRate()) / 100);
		LOG.finer("total interest amount is[" + totalInterestAmount + "]");
		// calculating amount to be paid per month
		double emi = (loanAmount + totalInterestAmount)
				/ Double.valueOf(numberOfYears * 12);
		LOG.finer("emi amount is [" + emi + "]");
		LOG.finest("EMI has been calculated successfully.");
		return new EmiData(loanAmount, loanType, durationPeriod, rate,
				totalInterestAmount, new Emi(emi));
	}

	@Override
	public InterestRate calculateInterestRate(double loanAmount,
			DurationPeriod durationPeriod, Emi emi)
			throws LoanCalculatorCoreException {
		if (durationPeriod == null || emi == null) {
			throw new LoanCalculatorCoreException(
					"Duration period or Emi cannot be null.");
		}
		LOG.finest("Interest rate calculation process is initiated.");
		LOG.finer("loan amount is [" + loanAmount + "]"
				+ " duration period is ["
				+ durationPeriod.getDurationPeriodAsString() + "]"
				+ " emi is [" + emi.getEmi() + "]");
		double numberOfYears = getNumberOfYears(durationPeriod);
		LOG.finer("number of years [" + numberOfYears + "]");
		// calculate total interest amount
		double totalInterestAmount = getTotalInterestAmount(loanAmount, emi,
				numberOfYears);
		LOG.finer("total interest amount payable is [" + totalInterestAmount
				+ "]");
		// calculate interest
		double rate = (Double.valueOf(100 * totalInterestAmount))
				/ Double.valueOf(loanAmount * numberOfYears);
		LOG.finer("interest rate is [" + rate + "]");
		LOG.finest("Interest Rate has been calculated successfully.");
		return new InterestRate((float) rate);
	}

	@Override
	public InterestRate calculateInterestRate(double loanAmount,
			DurationPeriod durationPeriod, double totalInterestAmount)
			throws LoanCalculatorCoreException {
		if (durationPeriod == null) {
			throw new LoanCalculatorCoreException(
					"Duration period cannot be null.");
		}
		LOG.finest("Interest rate calculation process is initiated.");
		LOG.finer("loan amount is [" + loanAmount + "]"
				+ " duration period is ["
				+ durationPeriod.getDurationPeriodAsString() + "]"
				+ " total interest amount is [" + totalInterestAmount + "]");
		double numberOfYears = getNumberOfYears(durationPeriod);
		LOG.finer("number of years [" + numberOfYears + "]");
		// calculate interest
		double rate = (Double.valueOf(100 * totalInterestAmount))
				/ Double.valueOf(loanAmount * numberOfYears);
		LOG.finer("interest rate is [" + rate + "]");
		LOG.finest("Interest Rate has been calculated successfully.");
		return new InterestRate((float) rate);
	}

	/**
	 * @Override public DurationPeriod calculateDuration(double loanAmount,
	 *           InterestRate interestRate, Emi emi) throws
	 *           LoanCalculatorCoreException {
	 *           LOG.finest("Duration period calculation process is initiated."
	 *           ); LOG.finer("loan amount is [" + loanAmount + "]" +
	 *           " interest rate is [" + interestRate + "]" + " emi is [" +
	 *           emi.getEmi() + "]"); // calculate total interest amount double
	 *           totalInterestAmount = getTotalInterestAmount(loanAmount, emi);
	 *           LOG.finer("total interest amount payable is [" +
	 *           totalInterestAmount + "]"); DurationPeriod durationPeriod =
	 *           calculatePeriod(loanAmount, totalInterestAmount, interestRate);
	 *           LOG.finer("duration period is [" +
	 *           durationPeriod.getDurationPeriodAsString() + "]");
	 *           LOG.finest("Duration Period has been succesfully calculated.");
	 *           return durationPeriod; }
	 */

	@Override
	public DurationPeriod calculateDuration(double loanAmount,
			InterestRate interestRate, double totalInterestAmount)
			throws LoanCalculatorCoreException {
		if (interestRate == null) {
			throw new LoanCalculatorCoreException(
					"Interest rate cannot be null.");
		}
		LOG.finest("Duration period calculation process is initiated.");
		LOG.finer("loan amount is [" + loanAmount + "]" + " interest rate is ["
				+ interestRate + "]" + " total interest amount is ["
				+ totalInterestAmount + "]");
		DurationPeriod durationPeriod = calculatePeriod(loanAmount,
				totalInterestAmount, interestRate);
		LOG.finer("duration period is ["
				+ durationPeriod.getDurationPeriodAsString() + "]");
		LOG.finest("Duration Period has been succesfully calculated.");
		return durationPeriod;
	}

	/**
	 * @Override public double calculatePrincipalAmount(Emi emi, DurationPeriod
	 *           durationPeriod, InterestRate interestRate) throws
	 *           LoanCalculatorCoreException {
	 *           LOG.finest("Principal amount calculation process is initiated."
	 *           ); LOG.fine("emi is [" + emi.getEmi() + "]" +
	 *           " interest rate is [" + interestRate + "]" +
	 *           " duration period is [" +
	 *           durationPeriod.getDurationPeriodAsString() + "]"); double
	 *           numberOfYears = getNumberOfYears(durationPeriod);
	 *           LOG.finer("number of years [" + numberOfYears + "]"); // double
	 *           totalInterestAmount = getTotalInterestAmount(loanAmount, emi);
	 *           LOG
	 *           .finest("Principal amount has been calculated successfully.");
	 *           double emiAmount = emi.getEmi(); double rate =
	 *           interestRate.getRate();
	 * 
	 *           double principalPlusInterest = Double.valueOf(emiAmount
	 *           (numberOfYears * 12)); double work = Double.valueOf(rate *
	 *           numberOfYears) / 100;
	 * 
	 *           }
	 */

	@Override
	public double calculatePrincipalAmount(double totalInterestAmount,
			DurationPeriod durationPeriod, InterestRate interestRate)
			throws LoanCalculatorCoreException {
		if (interestRate == null || interestRate == null) {
			throw new LoanCalculatorCoreException(
					"Interest rate or duration period cannot be null.");
		}
		LOG.finest("Principal amount calculation process is initiated.");
		LOG.finer("total interest amount is [" + totalInterestAmount + "]"
				+ " interest rate is [" + interestRate + "]"
				+ " duration period is ["
				+ durationPeriod.getDurationPeriodAsString() + "]");
		double numberOfYears = getNumberOfYears(durationPeriod);
		LOG.finer("number of years [" + numberOfYears + "]");
		double principalAmount = getPrincipalAmount(totalInterestAmount,
				numberOfYears, interestRate);
		LOG.finer("principal amount is [" + principalAmount + "]");
		LOG.finest("Principal amount has been calculated successfully.");
		return principalAmount;
	}

	/**
	 * Helps to get the number of years from given duration period.
	 * 
	 * @param durationPeriod
	 * @return number of years.
	 */
	private double getNumberOfYears(DurationPeriod durationPeriod) {
		double numberOfYears = 0.0;
		switch (durationPeriod.getTimePeriod()) {
		case Month:
			numberOfYears = Double.valueOf(durationPeriod.getDuration())
					/ Double.valueOf(12);
			break;
		case Year:
			numberOfYears = durationPeriod.getDuration();
			break;
		}
		return numberOfYears;
	}

	/**
	 * Helps to calculate the total interest amount payable for the given loan
	 * amount and the emi.
	 * 
	 * @param loanAmount
	 * @param emi
	 * @param numberOfYears
	 * @return total interest amount payable.
	 */
	private double getTotalInterestAmount(double loanAmount, Emi emi,
			double numberOfYears) {
		// calculate total interest amount
		return Double.valueOf((emi.getEmi() * Double
				.valueOf(12 * numberOfYears))) - loanAmount;
	}

	/**
	 * Helps to calculate the principal amount from the given loan details.
	 * 
	 * @param totalInterestAmount
	 * @param numberOfYears
	 * @param interestRate
	 * @return principal amount.
	 */
	private double getPrincipalAmount(double totalInterestAmount,
			double numberOfYears, InterestRate interestRate) {
		return Double.valueOf(100 * totalInterestAmount)
				/ Double.valueOf(numberOfYears * interestRate.getRate());
	}

	/**
	 * Helps to calculate the duration period of the loan has been borrowed,
	 * using the given details.
	 * 
	 * @param loanAmount
	 * @param totalInterestAmount
	 * @param interestRate
	 * @return number of years.
	 */
	private DurationPeriod calculatePeriod(double loanAmount,
			double totalInterestAmount, InterestRate interestRate) {
		double period = Double.valueOf(totalInterestAmount * 100)
				/ Double.valueOf(loanAmount * interestRate.getRate());
		if (Double.isInfinite(period)) {
			// always returning in months.
			return new DurationPeriod(0, TimePeriod.Month);
		}
		// trying to convert the year into months.
		int months = (int) (period * 12);
		// always returning in months.
		return new DurationPeriod(months, TimePeriod.Month);
	}

}
