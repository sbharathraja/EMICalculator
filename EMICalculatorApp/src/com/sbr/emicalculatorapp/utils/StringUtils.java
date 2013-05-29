package com.sbr.emicalculatorapp.utils;

/**
 * Provides utility operations to manipulate on a string.
 * 
 * @author sbharathraja
 * 
 */
public class StringUtils {

	private StringUtils() {
		// block instance creation
	}

	/**
	 * Helps to decide whether the given string value is either not null or
	 * empty.
	 * 
	 * @param value
	 * @return true if given string is not <code>null</code> or empty, false
	 *         otherwise.
	 */
	public static boolean isNotEmpty(String value) {
		if (value == null) {
			return false;
		}
		return value.trim().length() != 0;
	}

	/**
	 * Decide whether the given string is <code>null</code> or empty.
	 * 
	 * @param value
	 * @return true if given string is <code>null</code> or empty, false
	 *         otherwise.
	 */
	public static boolean isNullOrEmpty(String value) {
		return !isNotEmpty(value);
	}

}
