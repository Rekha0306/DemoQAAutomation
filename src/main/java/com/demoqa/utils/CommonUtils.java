package com.demoqa.utils;


/**
 *This class is used to have commonly 
 *used methods and timeouts constants 
 *
 */
public class CommonUtils {
	
	public static final int IMPLICIT_WAIT_TIME=20;
	public static final int PAGE_LOAD_TIME=30;
	public static final int EXPLICIT_WAIT_BASIC_TIME=30;
	
	public static String getAmountWithoutDollar(String amount) {
		return amount.substring(1);
	}

}
