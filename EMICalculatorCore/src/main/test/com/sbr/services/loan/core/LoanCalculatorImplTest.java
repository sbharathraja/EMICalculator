package com.sbr.services.loan.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Responsible for running unit tests on {@link LoanCalculatorImpl}
 * 
 * @author sbharathraja
 * 
 */
public class LoanCalculatorImplTest {

	private LoanCalculatorImpl calculator;

	@Before
	public void setUp() {
		calculator = new LoanCalculatorImpl();
	}

	@Test
	public void testCalculateEmi() throws LoanCalculatorCoreException {
		// null cases
		try {
			calculator.calculateEmi(0, null, null);
			fail("LoanCalculatorCoreException should be thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
		}
		// invalid principal amount.
		try {
			calculator.calculateEmi(0, LoanType.Housing, new DurationPeriod(1,
					TimePeriod.Year));
			fail("LoanCalculatorCoreException should be thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
			assertTrue(e
					.getMessage()
					.equals("Error occurs in rate calculation - loan amount is not valid!"));
		}

		// invalid duration period
		EmiData result = calculator.calculateEmi(100, LoanType.Housing,
				new DurationPeriod(0, TimePeriod.Year));
		assertNotNull(result);
		assertTrue(result.getEmi().getEmi() == 0.0
				|| Double.isInfinite(result.getEmi().getEmi()));
		assertTrue(result.getTotalInterestAmount() == 0.0);

		result = calculator.calculateEmi(1000, LoanType.Housing,
				new DurationPeriod(1, TimePeriod.Year));
		assertNotNull(result);
		assertTrue(result.getInterestRate().getRate() == 5);
		assertTrue(result.getEmi().getEmi() == 87.5);
		assertTrue(result.getTotalInterestAmount() == 50);

		result = calculator.calculateEmi(100000, LoanType.Housing,
				new DurationPeriod(1, TimePeriod.Year));
		assertNotNull(result);
		assertTrue(result.getInterestRate().getRate() == 8);
		assertTrue(result.getEmi().getEmi() == 9000);
		assertTrue(result.getTotalInterestAmount() == 8000);

		result = calculator.calculateEmi(60000, LoanType.Housing,
				new DurationPeriod(1, TimePeriod.Year));
		assertNotNull(result);
		assertTrue(result.getInterestRate().getRate() == 5.5);
		assertTrue(result.getEmi().getEmi() == 5275);
		assertTrue(result.getTotalInterestAmount() == 3300);
	}

	@Test
	public void testCalculateInterestRateDoubleDurationPeriodEmi()
			throws LoanCalculatorCoreException {
		// null cases
		try {
			calculator.calculateInterestRate(10.0, null, null);
			fail("LoanCalculatorCoreException should be thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
		}

		// invalid principal amount
		InterestRate rate = calculator.calculateInterestRate(0,
				new DurationPeriod(1, TimePeriod.Year), new Emi(100));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 0 || Double.isInfinite(rate.getRate()));

		// invalid duration
		rate = calculator.calculateInterestRate(1000, new DurationPeriod(0,
				TimePeriod.Year), new Emi(100));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 0 || Double.isInfinite(rate.getRate()));

		// invalid emi
		rate = calculator.calculateInterestRate(1000, new DurationPeriod(1,
				TimePeriod.Year), new Emi(00));
		assertNotNull(rate);
		assertTrue(rate.getRate() <= 0);

		rate = calculator.calculateInterestRate(1000, new DurationPeriod(1,
				TimePeriod.Year), new Emi(100));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 20);

		rate = calculator.calculateInterestRate(3000, new DurationPeriod(1,
				TimePeriod.Year), new Emi(333.33));
		assertNotNull(rate);
		assertTrue((int) rate.getRate() == 33);
	}

	@Test
	public void testCalculateInterestRateDoubleDurationPeriodDouble()
			throws LoanCalculatorCoreException {
		// null cases
		try {
			calculator.calculateInterestRate(10.0, null, 10);
			fail("LoanCalculatorException should be thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
		}

		// invalid principal amount
		InterestRate rate = calculator.calculateInterestRate(0,
				new DurationPeriod(1, TimePeriod.Year), 100);
		assertNotNull(rate);
		assertTrue(rate.getRate() == 0 || Double.isInfinite(rate.getRate()));

		// invalid duration period
		rate = calculator.calculateInterestRate(100, new DurationPeriod(0,
				TimePeriod.Year), 100);
		assertNotNull(rate);
		assertTrue(rate.getRate() == 0 || Double.isInfinite(rate.getRate()));

		// invalid total interest amount
		rate = calculator.calculateInterestRate(100, new DurationPeriod(1,
				TimePeriod.Year), 00);
		assertNotNull(rate);
		assertTrue(rate.getRate() <= 0);

		rate = calculator.calculateInterestRate(1000, new DurationPeriod(1,
				TimePeriod.Year), 100);
		assertNotNull(rate);
		assertTrue(rate.getRate() == 10);

		rate = calculator.calculateInterestRate(60000, new DurationPeriod(1,
				TimePeriod.Year), 3300);
		assertNotNull(rate);
		assertTrue(rate.getRate() == 5.5);
	}

	@Test
	public void testCalculateDuration() throws LoanCalculatorCoreException {
		// null cases
		try {
			calculator.calculateDuration(10, null, 10);
			fail("LoanCalculatorCoreException should be thrown.");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
		}

		// invalid principal amount
		DurationPeriod period = calculator.calculateDuration(0,
				new InterestRate(5), 10);
		assertNotNull(period);
		assertTrue(period.getDuration() == 0);
		assertTrue(period.getTimePeriod().equals(TimePeriod.Month));

		// invalid interest rate
		period = calculator.calculateDuration(10,
				new InterestRate((float) 0.0), 10);
		assertNotNull(period);
		assertTrue(period.getDuration() == 0);
		assertTrue(period.getTimePeriod().equals(TimePeriod.Month));

		// invalid total interest amount
		period = calculator.calculateDuration(10, new InterestRate(5), 00);
		assertNotNull(period);
		assertTrue(period.getDuration() == 0);
		assertTrue(period.getTimePeriod().equals(TimePeriod.Month));

		period = calculator.calculateDuration(1000, new InterestRate(20), 200);
		assertNotNull(period);
		// period will be always in months
		assertTrue(period.getDuration() == 12);
		assertTrue(period.getTimePeriod().equals(TimePeriod.Month));

		period = calculator.calculateDuration(1000, new InterestRate(5), 75);
		assertNotNull(period);
		// period will be always in months
		assertTrue(period.getDuration() == 18);
		assertTrue(period.getTimePeriod().equals(TimePeriod.Month));

		period = calculator
				.calculateDuration(1000, new InterestRate(5), 113.33);
		assertNotNull(period);
		// period will be always in months
		assertTrue(period.getDuration() == 27);
		assertTrue(period.getTimePeriod().equals(TimePeriod.Month));

	}

	@Test
	public void testCalculatePrincipalAmount()
			throws LoanCalculatorCoreException {
		// null cases
		try {
			calculator.calculatePrincipalAmount(10, null, null);
			fail("LoanCalculatorCoreException should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
		}

		// invalid interest rate
		double principalAmount = calculator.calculatePrincipalAmount(10,
				new DurationPeriod(1, TimePeriod.Year), new InterestRate(0));
		assertTrue(principalAmount == 0 || Double.isInfinite(principalAmount));

		// invalid duration period
		principalAmount = calculator.calculatePrincipalAmount(10,
				new DurationPeriod(0, TimePeriod.Year), new InterestRate(5));
		assertTrue(principalAmount == 0 || Double.isInfinite(principalAmount));
		
		//invalid total interest amount
		principalAmount = calculator.calculatePrincipalAmount(00.0,
				new DurationPeriod(1, TimePeriod.Year), new InterestRate(5));
		assertTrue(principalAmount == 0 || Double.isInfinite(principalAmount));
		
		principalAmount = calculator.calculatePrincipalAmount(100,
				new DurationPeriod(1, TimePeriod.Year), new InterestRate(10));
		assertTrue(principalAmount == 1000);
		
		principalAmount = calculator.calculatePrincipalAmount(3300,
				new DurationPeriod(1, TimePeriod.Year), new InterestRate((float) 5.5));
		assertTrue(principalAmount == 60000);
	}

}
