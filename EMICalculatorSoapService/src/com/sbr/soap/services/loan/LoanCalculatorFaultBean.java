package com.sbr.soap.services.loan;

/**
 * Container which holds the fault information of the web-service.
 * 
 * @author sbharathraja
 * 
 */
public class LoanCalculatorFaultBean {

	private String faultCode;

	private String faultString;

	public LoanCalculatorFaultBean() {
		this.setFaultCode("");
		this.setFaultString("");
	}

	public LoanCalculatorFaultBean(String faultCode, String faultString) {
		this.setFaultCode(faultCode);
		this.setFaultString(faultString);
	}

	public LoanCalculatorFaultBean(String faultString) {
		this.setFaultString(faultString);
	}

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

	public void setFaultString(String faultString) {
		this.faultString = faultString;
	}

}
