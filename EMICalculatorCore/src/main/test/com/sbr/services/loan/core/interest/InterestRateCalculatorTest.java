package com.sbr.services.loan.core.interest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sbr.services.loan.core.DurationPeriod;
import com.sbr.services.loan.core.InterestRate;
import com.sbr.services.loan.core.LoanCalculatorCoreException;
import com.sbr.services.loan.core.LoanType;
import com.sbr.services.loan.core.TimePeriod;

/**
 * Responsible running unit tests on {@link InterestRateCalculator}
 * 
 * @author sbharathraja
 * 
 */
public class InterestRateCalculatorTest {

	private InterestRateCalculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new InterestRateCalculator();
	}

	@Test
	public void testCalculateRate() throws LoanCalculatorCoreException {
		// null cases
		try {
			calculator.calculateRate(10, null, new DurationPeriod(1,
					TimePeriod.Year));
			fail("LoanCalculatorCoreException should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
		}
		try {
			calculator.calculateRate(10, LoanType.Education, null);
			fail("LoanCalculatorCoreException should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
		}

		// invalid loan amount
		try {
			calculator.calculateRate(0, LoanType.Education, new DurationPeriod(
					1, TimePeriod.Year));
			fail("LoanCalculatorCoreException should be thrown");
		} catch (Exception e) {
			assertTrue(e instanceof LoanCalculatorCoreException);
			assertTrue(e.getMessage().endsWith("loan amount is not valid!"));
		}

		// invalid duration
		InterestRate rate = calculator.calculateRate(1000, LoanType.Education,
				new DurationPeriod(0, TimePeriod.Month));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 0);

		rate = calculator.calculateRate(1000, LoanType.Education,
				new DurationPeriod(0, TimePeriod.Year));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 0);

		// test the predefined rate
		rate = calculator.calculateRate(1000, LoanType.Education,
				new DurationPeriod(1, TimePeriod.Year));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 3);

		rate = calculator.calculateRate(1000, LoanType.TwoWheeler,
				new DurationPeriod(1, TimePeriod.Year));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 4);

		// test the different rates based on the certain condition
		rate = calculator.calculateRate(1000, LoanType.Housing,
				new DurationPeriod(11, TimePeriod.Month));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 5);
		
		rate = calculator.calculateRate(10000, LoanType.Housing,
				new DurationPeriod(1, TimePeriod.Year));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 5);
		

		rate = calculator.calculateRate(100000, LoanType.Housing,
				new DurationPeriod(1, TimePeriod.Year));
		assertNotNull(rate);
		assertTrue(rate.getRate() == 8);
	}

}
