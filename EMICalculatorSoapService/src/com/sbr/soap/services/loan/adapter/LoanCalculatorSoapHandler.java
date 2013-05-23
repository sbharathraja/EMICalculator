package com.sbr.soap.services.loan.adapter;

import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.sbr.soap.services.loan.LoanCalculatorFault;
import com.sbr.soap.services.loan.jaxws.CalculateDuration;
import com.sbr.soap.services.loan.jaxws.CalculateEmi;
import com.sbr.soap.services.loan.jaxws.CalculateInterestRate;
import com.sbr.soap.services.loan.jaxws.CalculateInterestRateFromEmi;
import com.sbr.soap.services.loan.jaxws.CalculatePrincipalAmount;

/**
 * Responsible for handling the SOAP message.
 * 
 * <p>
 * <b>Note:</b>
 * <p>
 * This is required because the JAX-WS SOAP servers are not supported on google
 * app engine. Please refer <a
 * href="https://developers.google.com/appengine/articles/soap"
 * >https://developers.google.com/appengine/articles/soap</a>
 * 
 * @author sbharathraja
 * 
 */
public class LoanCalculatorSoapHandler {

	private static final String NAMESPACE_URI = "http://loan.services.sbr.com";

	private static final QName CALCULATE_EMI_QNAME = new QName(NAMESPACE_URI,
			"calculateEmi");

	private static final QName CALCULATE_DURATION_QNAME = new QName(
			NAMESPACE_URI, "calculateDuration");

	private static final QName CALCULATE_INTEREST_RATE_QNAME = new QName(
			NAMESPACE_URI, "calculateInterestRate");

	private static final QName CACLCULATE_INTEREST_RATE_FROM_EMI_QNAME = new QName(
			NAMESPACE_URI, "calculateInterestRateFromEmi");

	private static final QName CALCULATE_PRINCIPAL_AMOUNT_QNAME = new QName(
			NAMESPACE_URI, "calculatePrincipalAmount");

	private MessageFactory messageFactory;

	private LoanCalculatorAdapter loanCalculatorAdapter;

	/**
	 * Constructs the {@link LoanCalculatorSoapHandler}
	 * 
	 * @throws SOAPException
	 */
	public LoanCalculatorSoapHandler() throws SOAPException {
		messageFactory = MessageFactory.newInstance();
		loanCalculatorAdapter = new LoanCalculatorAdapter();
	}

	/**
	 * handling the soap request and return back the soap response as soap
	 * message.
	 * 
	 * @param soapRequest
	 * @return soap response.
	 * @throws SOAPException
	 */
	public SOAPMessage handleSOAPRequest(SOAPMessage soapRequest)
			throws SOAPException {
		// constructing the response holder
		SOAPMessage soapResponse = messageFactory.createMessage();
		SOAPBody soapResponseBody = soapResponse.getSOAPBody();
		try {
			// get the soap body which actually contains the request data for
			// each end point..
			SOAPBody soapRequestBody = soapRequest.getSOAPBody();

			// container to hold response object
			Object responsePojoObject = null;

			// soap body may contains the multiple end points data, so we are
			// iterating it to find out our end point to get it's data...
			for (Iterator<?> i = soapRequestBody.getChildElements(); i
					.hasNext();) {
				Object element = i.next();
				if (!(element instanceof SOAPElement)) {
					// child element is not expected type, hence skip it...
					continue;
				}
				QName qName = ((SOAPElement) element).getElementQName();
				if (qName.equals(CALCULATE_EMI_QNAME)) {
					responsePojoObject = handleCalculateEmiRequest((SOAPElement) element);
				} else if (qName.equals(CALCULATE_DURATION_QNAME)) {
					responsePojoObject = handleCalculateDurationRequest((SOAPElement) element);
				} else if (qName.equals(CALCULATE_INTEREST_RATE_QNAME)) {
					responsePojoObject = handleCalculateInterestRateRequest((SOAPElement) element);
				} else if (qName
						.equals(CACLCULATE_INTEREST_RATE_FROM_EMI_QNAME)) {
					responsePojoObject = handleCalculateInterestRateFromEmiRequest((SOAPElement) element);
				} else if (qName.equals(CALCULATE_PRINCIPAL_AMOUNT_QNAME)) {
					responsePojoObject = handleCalculatePrincipalAmountRequest((SOAPElement) element);
				} else {
					// we are not interested in other operations, hence
					// skipping...
					continue;
				}

			}

			if (responsePojoObject != null) {
				JAXB.marshal(responsePojoObject, new SAAJResult(soapResponse));
			} else {
				// something went wrong while response object population...
				SOAPFault fault = soapResponseBody.addFault();
				fault.setFaultString("Unrecognized SOAP Request!");
			}

		} catch (LoanCalculatorFault exception) {
			// exception occurs while doing the business logic of loan
			// calculation.
			SOAPFault fault = soapResponseBody.addFault();
			fault.setFaultString(exception.getFaultInfo().getFaultString());
		}
		return soapResponse;
	}

	/**
	 * Helps to handle the given soap element which actually contains the data
	 * for calculate emi request, and build the response to return back it.
	 * 
	 * @param soapElement
	 * @return response pojo object.
	 * @throws LoanCalculatorFault
	 * 
	 */
	private Object handleCalculateEmiRequest(SOAPElement soapElement)
			throws LoanCalculatorFault {
		// unmarshalling the soap request to construct the soap request in
		// corresponding pojo instance.
		CalculateEmi calculateLoanRequest = JAXB.unmarshal(new DOMSource(
				soapElement), CalculateEmi.class);
		// doing the job with adapter..
		return loanCalculatorAdapter
				.calculateEmiOperation(calculateLoanRequest);
	}

	/**
	 * 
	 * Helps to handle the given soap element which actually contains the data
	 * for calculate duration request, and build the response to return back it.
	 * 
	 * @param soapElement
	 * @return response pojo object
	 * @throws LoanCalculatorFault
	 */
	private Object handleCalculateDurationRequest(SOAPElement soapElement)
			throws LoanCalculatorFault {
		// unmarshalling the soap request to construct the soap request in
		// corresponding pojo instance.
		CalculateDuration calculateDurationRequest = JAXB.unmarshal(
				new DOMSource(soapElement), CalculateDuration.class);
		// doing the process with adapter
		return loanCalculatorAdapter
				.calculateDurationOperation(calculateDurationRequest);
	}

	/**
	 * 
	 * Helps to handle the given soap element which actually contains the data
	 * for calculate interest rate request, and build the response to return
	 * back it.
	 * 
	 * @param soapElement
	 * @return response pojo object
	 * @throws LoanCalculatorFault
	 */
	private Object handleCalculateInterestRateRequest(SOAPElement soapElement)
			throws LoanCalculatorFault {
		// unmarshalling the soap request to construct the soap request in
		// corresponding pojo instance.
		CalculateInterestRate calculateInterestRateRequest = JAXB.unmarshal(
				new DOMSource(soapElement), CalculateInterestRate.class);
		// doing the process with adpater
		return loanCalculatorAdapter
				.calculateInterestRateOperation(calculateInterestRateRequest);
	}

	/**
	 * 
	 * Helps to handle the given soap element which actually contains the data
	 * for calculate interest rate from emi request, and build the response to
	 * return back it.
	 * 
	 * @param soapElement
	 * @return response pojo object
	 * @throws LoanCalculatorFault
	 */
	private Object handleCalculateInterestRateFromEmiRequest(
			SOAPElement soapElement) throws LoanCalculatorFault {
		// unmarshalling the soap request to construct the soap request in
		// corresponding pojo instance.
		CalculateInterestRateFromEmi calculateInterestRateFromEmiRequest = JAXB
				.unmarshal(new DOMSource(soapElement),
						CalculateInterestRateFromEmi.class);
		// doing the process with adapter
		return loanCalculatorAdapter
				.calculateInterestRateFromEmiOperation(calculateInterestRateFromEmiRequest);
	}

	/**
	 * 
	 * Helps to handle the given soap element which actually contains the data
	 * for calculate principal amount request, and build the response to return
	 * back it.
	 * 
	 * @param soapElement
	 * @return response pojo object
	 * @throws LoanCalculatorFault
	 */
	private Object handleCalculatePrincipalAmountRequest(SOAPElement soapElement)
			throws LoanCalculatorFault {
		// unmarshalling the soap request to construct the soap request in
		// corresponding pojo instance.
		CalculatePrincipalAmount calculatePrincipalAmountRequest = JAXB
				.unmarshal(new DOMSource(soapElement),
						CalculatePrincipalAmount.class);
		// doing the process with adapter
		return loanCalculatorAdapter
				.calculatePrincipalAmountOperation(calculatePrincipalAmountRequest);
	}

}
