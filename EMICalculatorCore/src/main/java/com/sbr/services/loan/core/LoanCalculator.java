package com.sbr.services.loan.core;

/**
 * Provides business functionalities to calculate the emi, interest rate and
 * loan related metadata.
 * 
 * @author sbharathraja
 * 
 */
public interface LoanCalculator {

	/**
	 * Helps to calculate the EMI for the given detailed loan amount.
	 * 
	 * @param loanAmount
	 * @param loanType
	 * @param durationPeriod
	 * @return EMI details in {@link EmiData} instance.
	 * @throws LoanCalculatorCoreException
	 *             - if there is any thing went wrong in emi calculation.
	 */
	public EmiData calculateEmi(double loanAmount, LoanType loanType,
			DurationPeriod durationPeriod) throws LoanCalculatorCoreException;

	/**
	 * Helps to calculate the interest rate for the given emi amount using the
	 * given loan amount and given duration period.
	 * 
	 * @param loanAmount
	 * @param durationPeriod
	 * @param emi
	 * @return rate in {@link InterestRate} instance.
	 * @throws LoanCalculatorCoreException
	 *             - if there is anything went wrong in rate calculation.
	 */
	public InterestRate calculateInterestRate(double loanAmount,
			DurationPeriod durationPeriod, Emi emi)
			throws LoanCalculatorCoreException;

	/**
	 * Helps to calculate the interest rate for the given total interest amount
	 * using the given loan amount and given duration period.
	 * 
	 * @param loanAmount
	 * @param durationPeriod
	 * @param totalInterestAmount
	 * @return rate in {@link InterestRate} instance.
	 * @throws LoanCalculatorCoreException
	 *             - if there is anything went wrong in rate calculation.
	 */
	public InterestRate calculateInterestRate(double loanAmount,
			DurationPeriod durationPeriod, double totalInterestAmount)
			throws LoanCalculatorCoreException;

	/**
	 * Helps to calculate the duration period of the given loan details.
	 * 
	 * @param loanAmount
	 * @param interestRate
	 * @param emi
	 * @return duration period in instance of {@link DurationPeriod}
	 * @throws LoanCalculatorCoreException
	 *             - if there is anything went wrong in duration calculation.
	 */
	// public DurationPeriod calculateDuration(double loanAmount,
	// InterestRate interestRate, Emi emi)
	// throws LoanCalculatorCoreException;

	/**
	 * Helps to calculate the duration period of the given loan details.
	 * 
	 * @param loanAmount
	 * @param interestRate
	 * @param totalInterestAmount
	 * @return duration period in instance of {@link DurationPeriod}
	 * @throws LoanCalculatorCoreException
	 *             - if there is anything went wrong in duration calculation.
	 */
	public DurationPeriod calculateDuration(double loanAmount,
			InterestRate interestRate, double totalInterestAmount)
			throws LoanCalculatorCoreException;

	/**
	 * Helps to calculate the principal amount of the given loan details.
	 * 
	 * @param emi
	 * @param durationPeriod
	 * @param interestRate
	 * @return principal amount.
	 * @throws LoanCalculatorCoreException
	 *             - if there is anything went wrong in principal amount
	 *             calculation.
	 */
	// public double calculatePrincipalAmount(Emi emi,
	// DurationPeriod durationPeriod, InterestRate interestRate)
	//	throws LoanCalculatorCoreException;

	/**
	 * Helps to calculate the principal amount of the given loan details.
	 * 
	 * @param totalInterestAmount
	 * @param durationPeriod
	 * @param interestRate
	 * @return principal amount.
	 * @throws LoanCalculatorCoreException
	 *             - if there is anything went wrong in principal amount
	 *             calculation.
	 */
	public double calculatePrincipalAmount(double totalInterestAmount,
			DurationPeriod durationPeriod, InterestRate interestRate)
			throws LoanCalculatorCoreException;

}
