package com.temenos.services.loan.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoanCalculatorTest {

	private EMICalculator loanCalculator;

	@Before
	public void setUp() throws Exception {
		loanCalculator = new LoanEMICalculator();
	}

	@Test
	public void testCalculateLoan() throws LoanEMICalculatorException {
		try {
			loanCalculator.calculateEMI(0, null, null);
		} catch (Exception e) {
			assertTrue(e instanceof LoanEMICalculatorException);
		}

		EMIDetails loanMetaData=loanCalculator.calculateEMI(100000, LoanType.PERSONAL,
				DurationPeriods._3MONTHS);
		assertTrue(loanMetaData.getInterestRate().equals("15%"));
		assertTrue(loanMetaData.getTotalInterestAmount()==3750);
		assertTrue(loanMetaData.getEmi()==34583.333333333336);
	}	
}
