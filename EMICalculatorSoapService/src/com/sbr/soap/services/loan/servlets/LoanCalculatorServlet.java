package com.sbr.soap.services.loan.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.sbr.soap.services.loan.adapter.LoanCalculatorSoapHandler;

/**
 * Servlet which serves the http operation for this loan calculation service.
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
@SuppressWarnings("serial")
public class LoanCalculatorServlet extends HttpServlet {

	private static MessageFactory messageFactory;

	private static LoanCalculatorSoapHandler soapHandler;

	// doing necessary initialization in static block as the servlet
	// initialization will be taken care of framework and we cannot initialize
	// it anywhere...
	static {
		try {
			messageFactory = MessageFactory.newInstance();
			soapHandler = new LoanCalculatorSoapHandler();
		} catch (SOAPException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			MimeHeaders headers = getHeaders(req);
			// Construct a SOAPMessage from the XML in the request body
			InputStream is = req.getInputStream();
			SOAPMessage soapRequest = messageFactory.createMessage(headers, is);

			// Handle soapReqest
			SOAPMessage soapResponse = soapHandler
					.handleSOAPRequest(soapRequest);

			// Write to HttpServeltResponse
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType("text/xml;charset=\"utf-8\"");
			OutputStream os = resp.getOutputStream();
			soapResponse.writeTo(os);
			os.flush();
		} catch (SOAPException e) {
			throw new IOException("Exception while creating SOAP message.", e);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.sendRedirect("http://temloancalc.appspot.com/service");
	}

	/**
	 * Helps to get all headers from given http request.
	 * 
	 * @param req
	 * @return
	 */
	private MimeHeaders getHeaders(HttpServletRequest req) {
		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue, ",");
			while (values.hasMoreTokens()) {
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		return headers;
	}

}
