package com.myAmway.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(new Date());
		return d;
	}
	
}
