package com.allen.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class DateUtil {
	
	/**
	 * 时间字符串转时间戳(精确到秒)
	 * @return
	 */
	public static Integer getDateStringToTimeStamp(String date,String format) {
		if (StringUtils.isBlank(date))
			return null;
		
		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			Date data = sf.parse(date);
			String dateStr = String.valueOf(data.getTime()).substring(0, 10);
			return Integer.valueOf(dateStr);
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
		System.out.println(getDateStringToTimeStamp("2037-04-10","yyyy-MM-dd"));
		//System.out.println(getTimeStampToDate(1554092725000L));
	}

}
