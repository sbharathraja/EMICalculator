package com.sbr.emicalculatorapp.service.loan.client;


/**
 * Adapter which should be mapped to the type of TimePeriod in real
 * service when invoking any related call.
 * 
 * @author sbharathraja
 * 
 */
public enum TimePeriod{

	Month(0), Year(1);

	private int code;

	TimePeriod(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static TimePeriod fromString(String str) {
		if (str.equals("Month"))
			return Month;
		if (str.equals("Year"))
			return Year;
		return null;
	}

}
