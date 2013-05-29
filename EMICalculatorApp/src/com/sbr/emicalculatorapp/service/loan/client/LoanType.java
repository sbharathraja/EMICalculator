package com.sbr.emicalculatorapp.service.loan.client;


/**
 * Adapter which should be mapped to the type of LoanType in real service when
 * invoking any related call.
 * 
 * @author sbharathraja
 * 
 */
public enum LoanType{

	Housing(0), Personal(1), Education(2), Property(3), TwoWheeler(4);

	private int code;

	LoanType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static LoanType fromString(String str) {
		if (str.equals("Housing"))
			return Housing;
		if (str.equals("Personal"))
			return Personal;
		if (str.equals("Education"))
			return Education;
		if (str.equals("Property"))
			return Property;
		if (str.equals("TwoWheeler"))
			return TwoWheeler;
		return null;
	}

}
