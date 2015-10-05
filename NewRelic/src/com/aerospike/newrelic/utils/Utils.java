package com.aerospike.newrelic.utils;

import static com.aerospike.newrelic.utils.Constants.*;
/**
 * Utils class having utility methods.
 * 
 * @author Aniruddha Atre
 *
 */
public class Utils {

	/**
	 * Method to check if metric is a valid number or not.
	 * 
	 * @param value
	 *            A metric to be published
	 * @return Boolean True if valid number else false
	 */
	public static boolean validNumber(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * Method to check if stats from aerospike have value true/false
	 * 
	 * @param value
	 * @return 1/0 for true/false or return value as it is
	 */
	public static String handleAerospikeString(String value) {
		if (value.equalsIgnoreCase(TRUE))
			return ONE;
		else if (value.equalsIgnoreCase(FALSE))
			return ZERO;
		return value;
	}
}
