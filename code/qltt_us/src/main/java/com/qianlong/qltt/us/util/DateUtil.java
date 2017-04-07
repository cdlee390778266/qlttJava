package com.qianlong.qltt.us.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 两个时间是否为同一天
	 */
	public static boolean isSameDate(Date date1, Date date2) {        
		Calendar cal1 = Calendar.getInstance();        
		cal1.setTime(date1);        
		
		Calendar cal2 = Calendar.getInstance();        
		cal2.setTime(date2);        
		
		return  cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) 
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);           
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的字符串解析成日期对象
	 */
	public static Date strToDate(String str){
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
		} catch (Exception e) {
			return null;
		}
	}
}
