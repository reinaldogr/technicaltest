package com.hotelbeds.supplierintegrations.hackertest.utils;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Util {
	
	public static Long timeCalculation(Timestamp time1, Timestamp time2) {
		Long difference = time2.getTime() - time1.getTime();
		return TimeUnit.MILLISECONDS.toMinutes(difference);
	}
}
