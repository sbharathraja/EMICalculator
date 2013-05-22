package com.sbr.services.loan.core;

import java.io.Serializable;

/**
 * Contains the metadata related to EMI.
 * 
 * @author sbharathraja
 * 
 */
public class EmiData implements Serializable {

	private static final long serialVersionUID = -3249714546141464817L;

	private double loanAmount;

	private LoanType loanType;

	private DurationPeriod durationPeriod;

	private InterestRate interestRate;

	private Emi emi;

	private double totalInterestAmount;

	/**
	 * Creates the empty emi data.
	 */
	public EmiData() {

	}

	/**
	 * Creates the emi data with given details.
	 * 
	 * @param loanAmount
	 * @param loanType
	 * @param durationPeriod
	 * @param interestRate
	 * @param emi
	 */
	public EmiData(double loanAmount, LoanType loanType,
			DurationPeriod durationPeriod, InterestRate interestRate,
			double totalInterestAmount, Emi emi) {
		this.setLoanAmount(loanAmount);
		this.setLoanType(loanType);
		this.setDurationPeriod(durationPeriod);
		this.setInterestRate(interestRate);
		this.setTotalInterestAmount(totalInterestAmount);
		this.setEmi(emi);
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public DurationPeriod getDurationPeriod() {
		return durationPeriod;
	}

	public void setDurationPeriod(DurationPeriod durationPeriod) {
		this.durationPeriod = durationPeriod;
	}

	public InterestRate getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(InterestRate interestRate) {
		this.interestRate = interestRate;
	}

	public Emi getEmi() {
		return emi;
	}

	public void setEmi(Emi emi) {
		this.emi = emi;
	}

	public double getTotalInterestAmount() {
		return totalInterestAmount;
	}

	public void setTotalInterestAmount(double totalInterestAmount) {
		this.totalInterestAmount = totalInterestAmount;
	}

}
