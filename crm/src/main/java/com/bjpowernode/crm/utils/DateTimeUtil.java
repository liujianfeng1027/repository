package com.bjpowernode.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getSysTime(){

		Date date = new Date();
		String dateStr = SDF.format(date);
		
		return dateStr;
		
	}

}
