package com.sbr.soap.services.loan;

import javax.xml.ws.WebFault;

/**
 * Defines the fault/exception in this service.
 * 
 * @author sbharathraja
 * 
 */
@WebFault(targetNamespace = "http://loan.services.sbr.com/fault")
public class LoanCalculatorFault extends Exception {

	/**
	 * generated serial version uid.
	 */
	private static final long serialVersionUID = -6166767610848617618L;

	private LoanCalculatorFaultBean faultBean;

	/**
	 * Creates the empty fault.
	 */
	public LoanCalculatorFault() {
		super();
	}

	/**
	 * Creates the fault with given message and given fault bean.
	 * 
	 * @param message
	 * @param faultBean
	 */
	public LoanCalculatorFault(String message, LoanCalculatorFaultBean faultBean) {
		super(message);
		this.faultBean = faultBean;
	}

	/**
	 * Creates the fault with given details.
	 * 
	 * @param message
	 * @param cause
	 * @param faultBean
	 */
	public LoanCalculatorFault(String message, Throwable cause,
			LoanCalculatorFaultBean faultBean) {
		super(message, cause);
		this.faultBean = faultBean;
	}

	/**
	 * Helps to get the bean associated with this fault.
	 * 
	 * @return fault bean.
	 */
	public LoanCalculatorFaultBean getFaultInfo() {
		return faultBean;
	}

}
