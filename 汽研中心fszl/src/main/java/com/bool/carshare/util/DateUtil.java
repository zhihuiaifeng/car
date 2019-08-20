/**
 * 
 */
package com.bool.carshare.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author wangw
 */
public class DateUtil {
	/**
	 * 获得当前时间
	 * @return
	 */
	public static Date getCurrentDate() {
		Date currentDate = new Date();
		
		return currentDate;
	}
	
	/**
	 * 将时间转换成Date类型
	 * @param date
	 * @return
	 */
	public static Date toDate(String date) {
		if(!"".equals(date) && date!=null) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			
			try {
				Date d = sdf.parse(date);
				
				return d;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * 将时间转换成String类型
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date, String format) {
		if(date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String d = sdf.format(date);
			
			return d;
		}
		
		return null;
	}
}