package com.sbr.emicalculatorapp.service.loan.consumer;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.sbr.emicalculatorapp.service.loan.client.DurationPeriod;
import com.sbr.emicalculatorapp.service.loan.client.Emi;
import com.sbr.emicalculatorapp.service.loan.client.EmiData;
import com.sbr.emicalculatorapp.service.loan.client.InterestRate;
import com.sbr.emicalculatorapp.service.loan.client.LoanType;
import com.sbr.emicalculatorapp.service.loan.client.TimePeriod;

/**
 * Responsible for consuming the EMI Calculator service and providing the
 * service operation.
 * 
 * @author sbharathraja
 * @param <P>
 * @param <R>
 * 
 */
public class EMICalculatorServiceConsumer extends
		AbstractProgressableAsyncTask<SoapObject, EmiData> {

	private static final String WSDL_URL = "http://temloancalc.appspot.com/service?wsdl";

	private static final String NAMESPACE = "http://loan.services.sbr.com";

	private static final String METHOD_NAME = "calculateEmi";

	private static final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

	@Override
	protected EmiData performTaskInBackground(SoapObject parameter)
			throws Exception {
		// 1. Create SOAP Envelope using the request
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.implicitTypes = true;
		// envelope.dotNet = true;
		MarshalFloat marshalFloat = new MarshalFloat();
		marshalFloat.register(envelope);

		envelope.addMapping(NAMESPACE, "DurationPeriod",
				new DurationPeriod().getClass());
		envelope.addMapping(NAMESPACE, "InterestRate",
				new InterestRate().getClass());
		envelope.addMapping(NAMESPACE, "Emi", new Emi().getClass());
		envelope.addMapping(NAMESPACE, "EmiData", new EmiData().getClass());

		envelope.setOutputSoapObject(parameter);

		// 2. Create a HTTP Transport object to send the web service request
		HttpTransportSE httpTransport = new HttpTransportSE(WSDL_URL,40000);
		httpTransport.debug = true; // allows capture of raw request/response
		// in Logcat
		try {
			// 3. Make the web service invocation
			httpTransport.call(SOAP_ACTION, envelope);
		} catch (IOException e) {
			throw e;
		} finally {
			Log.d(this.getClass().getSimpleName(), "HTTP REQUEST:\n"
					+ httpTransport.requestDump);
			Log.d(this.getClass().getSimpleName(), "HTTP RESPONSE:\n"
					+ httpTransport.responseDump);
		}

		EmiData result = null;

		if (envelope.bodyIn instanceof SoapObject) { // SoapObject = SUCCESS
			SoapObject soapObject = (SoapObject) envelope.bodyIn;
			result = parseSOAPResponse(soapObject);
		} else if (envelope.bodyIn instanceof SoapFault) { // SoapFault =
			// FAILURE
			SoapFault soapFault = (SoapFault) envelope.bodyIn;
			throw new Exception(soapFault.getMessage());
		}
		return result;
	}

	/**
	 * Helps to create the soap request using given request data.
	 * 
	 * @param loanAmount
	 * @param loanType
	 * @param durationPeriod
	 * @return soap request object.
	 */
	public static SoapObject createRequest(double loanAmount,
			LoanType loanType, DurationPeriod durationPeriod) {
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

		PropertyInfo pAmount = new PropertyInfo();
		pAmount.type = Double.class;
		pAmount.name = "principalAmount";
		pAmount.setValue(loanAmount);

		request.addProperty(pAmount);

		request.addProperty("LoanType", loanType.toString());

		request.addProperty("DurationPeriod", durationPeriod);

		return request;
	}

	/**
	 * Helps to parse the given soap response and convert it into our own
	 * response object.
	 * 
	 * @param response
	 * @return instance of {@link CustomEmiData}
	 */
	private EmiData parseSOAPResponse(SoapObject response) {
		// get the response.
		SoapObject responseObject = (SoapObject) response
				.getProperty("EmiData");
		if (responseObject != null) {
			EmiData data = new EmiData();
			data.setProperty(0, getLoanAmount(responseObject));
			data.setProperty(1, getLoanType(responseObject));
			data.setProperty(2, getDurationPeriod(responseObject));
			data.setProperty(3, getInterestRate(responseObject));
			data.setProperty(4, getEmi(responseObject));
			data.setProperty(5, getTotalInterestAmount(responseObject));

			return data;
		}
		throw new RuntimeException("Invalid soap object recieved.");
	}

	/**
	 * Helps to parse the given soap response object to get the loan amount from
	 * soap response.
	 * 
	 * @param responseObject
	 * @return 0 if in case the expected property is not avail, otherwise return
	 *         the value from soap response.
	 */
	private double getLoanAmount(SoapObject responseObject) {
		if (responseObject == null) {
			return 0;
		}
		if (!responseObject.hasProperty("loanAmount")) {
			return 0;
		}
		return Double.parseDouble(responseObject
				.getPropertySafelyAsString("loanAmount"));
	}

	/**
	 * Helps to parse the given soap response object to get the loan type from
	 * soap response.
	 * 
	 * @param responseObject
	 * @return loan type.
	 */
	private LoanType getLoanType(SoapObject responseObject) {
		if (responseObject == null) {
			return null;
		}
		if (!responseObject.hasProperty("loanType")) {
			return null;
		}
		return LoanType.fromString(responseObject
				.getPropertySafelyAsString("loanType"));
	}

	/**
	 * Helps to parse the given soap response object to get the duration period
	 * from soap response.
	 * 
	 * @param responseObject
	 * @return duration period
	 */
	private DurationPeriod getDurationPeriod(SoapObject responseObject) {
		if (responseObject == null) {
			return null;
		}
		if (!responseObject.hasProperty("durationPeriod")) {
			return null;
		}
		// complex type
		SoapObject durationComplexTypeObject = (SoapObject) responseObject
				.getProperty("durationPeriod");

		if (durationComplexTypeObject == null) {
			return null;
		}
		DurationPeriod durationPeriod = new DurationPeriod();
		// parse the complex type object to construct its properties

		if (durationComplexTypeObject.hasProperty("duration")) {
			durationPeriod.setProperty(0, Integer
					.parseInt(durationComplexTypeObject
							.getPropertySafelyAsString("duration")));
		}
		if (durationComplexTypeObject.hasProperty("timePeriod")) {
			durationPeriod.setProperty(1, TimePeriod
					.fromString(durationComplexTypeObject
							.getPropertySafelyAsString("timePeriod")));
		}

		return durationPeriod;
	}

	/**
	 * Helps to parse the given soap response object to get the interest rate.
	 * 
	 * @param responseObject
	 * @return interest rate.
	 */
	private InterestRate getInterestRate(SoapObject responseObject) {
		if (responseObject == null) {
			return null;
		}
		if (!responseObject.hasProperty("interestRate")) {
			return null;
		}
		// complex type
		SoapObject interestRateComplexTypeObject = (SoapObject) responseObject
				.getProperty("interestRate");
		if (interestRateComplexTypeObject == null) {
			return null;
		}
		InterestRate rate = new InterestRate();
		// parse the complex type object to construct its simple type
		// properties..
		if (interestRateComplexTypeObject.hasProperty("rate")) {
			rate.setProperty(0, Float.parseFloat(interestRateComplexTypeObject
					.getPropertySafelyAsString("rate")));
		}
		return rate;
	}

	/**
	 * Helps to parse the given soap response object to get the emi.
	 * 
	 * @param responseObject
	 * @return emi.
	 */
	private Emi getEmi(SoapObject responseObject) {
		if (responseObject == null) {
			return null;
		}
		if (!responseObject.hasProperty("emi")) {
			return null;
		}
		// complex type
		SoapObject emiComplexTypeObject = (SoapObject) responseObject
				.getProperty("emi");
		if (emiComplexTypeObject == null) {
			return null;
		}
		Emi emi = new Emi();
		// parse the complex type of object to construct its simple type
		// properties.
		if (emiComplexTypeObject.hasProperty("emi")) {
			emi.setProperty(0, Double.parseDouble(emiComplexTypeObject
					.getPropertySafelyAsString("emi")));
		}
		return emi;
	}

	/**
	 * Helps to parse the given response object to get the total interest
	 * amount.
	 * 
	 * @param responseObject
	 * @return total interest amount.
	 */
	private double getTotalInterestAmount(SoapObject responseObject) {
		if (responseObject == null) {
			return 0;
		}
		if (!responseObject.hasProperty("totalInterestAmount")) {
			return 0;
		}
		return Double.parseDouble(responseObject
				.getPropertySafelyAsString("totalInterestAmount"));
	}

}
