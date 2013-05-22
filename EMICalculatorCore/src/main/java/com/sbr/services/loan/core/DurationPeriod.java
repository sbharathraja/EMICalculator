package com.sbr.services.loan.core;

/**
 * Holds the meta data about duration of the loan.
 * 
 * @author sbharathraja
 * 
 */
public class DurationPeriod {

	private int duration;

	private TimePeriod timePeriod;

	/**
	 * Creates the empty duration period.
	 */
	public DurationPeriod() {
		this.setDuration(0);
		this.setTimePeriod(TimePeriod.Month);
	}

	/**
	 * Creates the duration period with given details.
	 * 
	 * @param duration
	 *            - should not be float value.
	 * @param timePeriod
	 *            - {@link TimePeriod#Month} / {@link TimePeriod#Year}
	 */
	public DurationPeriod(int duration, TimePeriod timePeriod) {
		this.setDuration(duration);
		this.setTimePeriod(timePeriod);
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	/**
	 * Helps to get the duration period in user comfortable string format.
	 * 
	 * @return e.g.: 10 Month, 1 Year.
	 */
	public String getDurationPeriodAsString() {
		return duration + " " + timePeriod.toString();
	}

}
