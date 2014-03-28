package com.rivetlogic.jstl;

import java.util.Arrays;

/**
 * The Class Functions.
 */
public class Functions {

	/**
	 * Arr contains.
	 *
	 * @param strArray the str array
	 * @param value the value
	 * @return true, if successful
	 */
	public static boolean arrContains(String[] strArray, String value){
		if((strArray != null) && (value != null))
			return Arrays.asList(strArray).contains(value);
		return false;
	}
}
