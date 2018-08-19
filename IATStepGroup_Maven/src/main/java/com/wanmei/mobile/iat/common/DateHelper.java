package com.wanmei.mobile.iat.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	

	public static String getTimeStampSec() throws Exception
	{
	    	String timeSeconds=String.valueOf(Calendar.getInstance().getTimeInMillis()/1000);
			return timeSeconds;	
	}
	

	public static String getTimeInMillis() throws Exception
	{
		String timeMillis=String.valueOf(Calendar.getInstance().getTimeInMillis());
		return timeMillis;	
	}
	


	public static String convertDetailFromServer(String mill) 
	{
		Date date = new Date(Long.parseLong(mill.trim()));
		String strs = "";	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strs = sdf.format(date);
		return strs;
	}
	

	public static long getMillTime(String date) throws Exception {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		  
		  long millionSeconds = sdf.parse(date).getTime();//����
		  return millionSeconds;
	}
	

	public static String getDate(String mill) 
	{
		Date date = new Date(Long.parseLong(mill.trim()));
		String strs = "";	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		strs = sdf.format(date);
		return strs;
	}
	

	public static String getYesterDay(String mill) 
	{	
		Date date = new Date(Long.parseLong(mill.trim()));	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		String strs = "";	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		strs = sdf.format(date);	
		return strs;
	}
	
	

	public static String getTheDayBeforeYesterDay(String mill) 
	{	
		Date date = new Date(Long.parseLong(mill.trim()));	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		calendar.add(Calendar.MINUTE, -1);
		date = calendar.getTime();
		String strs = "";	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strs = sdf.format(date);	
		return strs;
	}
	

	public static String getDateNextYear(String mill) 
	{	
		Date date = new Date(Long.parseLong(mill.trim()));	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		date = calendar.getTime();
		String strs = "";	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		strs = sdf.format(date);	
		return strs;
	}

}
