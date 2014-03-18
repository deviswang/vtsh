package com.cardinfolink.vtsh.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 *
	 */
	public static String formatCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String strDate = sdf.format(date);
		return strDate;
	}
	/**
	 * 
	 */
	public static String formatCurrentDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);
		return strDate;
	}
	
	/**
	 * Ìí¼ÓÌìÊı
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date addDay(Date date, int num) {
		  Calendar startDT = Calendar.getInstance();
		  startDT.setTime(date);
		  startDT.add(Calendar.DAY_OF_MONTH, num);
		  return startDT.getTime();
  }
}
