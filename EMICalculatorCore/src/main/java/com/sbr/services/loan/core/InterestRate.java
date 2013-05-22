package com.sbr.services.loan.core;

/**
 * Defines the rate for the loan.
 * 
 * @author sbharathraja
 * 
 */
public class InterestRate {

	private float rate;

	/**
	 * Creates the empty rate.
	 */
	public InterestRate() {

	}

	/**
	 * Creates the rate with given value.
	 * 
	 * @param rate
	 */
	public InterestRate(float rate) {
		this.setRate(rate);
	}

	public float getRate() {
		return rate;
	}

	/**
	 * Helps to get the interest rate in string format.
	 * 
	 * @return e.g.: 10%
	 */
	public String getRateAsString() {
		return rate + "%";
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}
