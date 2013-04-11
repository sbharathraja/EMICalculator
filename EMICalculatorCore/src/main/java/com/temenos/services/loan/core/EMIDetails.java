package com.temenos.services.loan.core;

import java.io.Serializable;

import com.temenos.services.loan.core.interest.InterestRate;

/**
 * Act as a container of Loan Meta-data
 * 
 * @author BharathRaja
 * 
 */
public class EMIDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3290592341474514213L;

	private double loanAmount;

	private double totalInterestAmount;

	private LoanType loanType;

	private DurationPeriods durationPeriod;

	private InterestRate interestRate;

	private double emi;

	/**
	 * Creates the loan meta data with given details.
	 * 
	 * @param loanAmount
	 * @param loanType
	 * @param durationPeriod
	 */
	public EMIDetails(double loanAmount, LoanType loanType,
			DurationPeriods durationPeriod) {
		this.loanAmount = loanAmount;
		this.setLoanType(loanType);
		this.setDurationPeriod(durationPeriod);
	}
	
	public EMIDetails(){
		
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getTotalInterestAmount() {
		return totalInterestAmount;
	}

	public void setTotalInterestAmount(double totalInterestAmount) {
		this.totalInterestAmount = totalInterestAmount;
	}

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public String getDurationPeriod() {
		if (durationPeriod == null) {
			return "";
		}
		String periods = durationPeriod.toString();
		periods.replaceAll("_", "");
		return periods;
	}

	public void setDurationPeriod(DurationPeriods durationPeriod) {
		this.durationPeriod = durationPeriod;
	}

	public String getInterestRate() {
		if (interestRate == null) {
			return "";
		}
		switch (interestRate) {
		case _8POINT5:
			return "8.5%";
		case _10:
			return "10%";
		case _15:
			return "15%";
		default:
			return "";
		}
	}

	public void setInterestRate(InterestRate interestRate) {
		this.interestRate = interestRate;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double amountToBePaidPerMonth) {
		this.emi = amountToBePaidPerMonth;
	}

}
