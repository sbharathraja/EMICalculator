package com.temenos.services.loan.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LoanCalculatorTest {

	private Calculator loanCalculator;

	@Before
	public void setUp() throws Exception {
		loanCalculator = new LoanCalculator();
	}

	@Test
	public void testCalculateLoan() throws LoanCalculatorException {
		try {
			loanCalculator.calculateLoan(0, null, null);
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorException);
		}

		LoanMetaData loanMetaData=loanCalculator.calculateLoan(100000, LoanType.PERSONAL,
				DurationPeriods._3MONTHS);
		assertTrue(loanMetaData.getInterestRate().equals("15%"));
		assertTrue(loanMetaData.getTotalInterestAmount()==3750);
		assertTrue(loanMetaData.getAmountToBePaidPerMonth()==34583.333333333336);
	}	
}
