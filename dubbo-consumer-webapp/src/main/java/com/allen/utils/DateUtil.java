package com.allen.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class DateUtil {
	
	/**
	 * 时间字符串转时间戳(精确到秒)
	 * @return
	 */
	public static Long getDateStringToTimeStamp(String date,String format) {
		if (StringUtils.isBlank(date))
			return null;
		
		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			Date data = sf.parse(date);
			String dateStr = String.valueOf(data.getTime()).substring(0, 10);
			return Long.valueOf(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getTimeStampToDate(Long timeStamp,String format) {
		if (timeStamp == null)
			return null;
		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			Date date = new Date(timeStamp);
			return sf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getDateStringToTimeStamp("2019-04-22 19:05:00","yyyy-MM-dd HH:mm:ss"));
		//System.out.println(getTimeStampToDate(Long.parseLong("2185977600"+"000"),"yyyy-MM-dd"));
	
		//Date data = new Date();
		//System.out.println(data.getTime());
	}

}
