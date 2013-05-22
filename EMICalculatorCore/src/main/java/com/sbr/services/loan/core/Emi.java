package com.sbr.services.loan.core;

/**
 * Holds the emi details.
 * 
 * @author sbharathraja
 * 
 */
public class Emi {

	private double emi;

	/**
	 * Creates the empty emi.
	 */
	public Emi() {

	}

	/**
	 * Creates the emi with given value.
	 * 
	 * @param emi
	 */
	public Emi(double emi) {
		this.setEmi(emi);
	}

	public double getEmi() {
		return emi;
	}

	/**
	 * Helps to get the emi value to rounded off to integer.
	 * 
	 * @return 12345 if the real emi is 12345.xxxxxxx
	 */
	public int getRoundedOffEmi() {
		return (int) Math.round(emi);
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

}
