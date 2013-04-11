package com.temenos.services.loan.core.interest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.temenos.services.loan.core.LoanEMICalculatorException;

public class InterestRateCalculatorTest {

	private InterestRateCalculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new InterestRateCalculator();
	}

	@Test
	public void testCalculateRate() throws LoanEMICalculatorException {
		try {
			calculator.calculateRate(0);
		} catch (Exception e) {
			assertTrue(e instanceof LoanEMICalculatorException);
		}

		assertTrue(calculator.calculateRate(1000).equals(InterestRate._8POINT5));
		assertTrue(calculator.calculateRate(99999).equals(InterestRate._8POINT5));
		assertTrue(calculator.calculateRate(100000).equals(InterestRate._15));
		assertTrue(calculator.calculateRate(500000).equals(InterestRate._15));
		assertTrue(calculator.calculateRate(500001).equals(InterestRate._10));
	}
}
