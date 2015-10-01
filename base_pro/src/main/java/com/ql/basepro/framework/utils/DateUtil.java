package com.ql.basepro.framework.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final String FORMAT_LONG_MS = "yyyyMMdd HHmmss";
	
	public static final String FORMAT_DATE_DD = "yyyy-MM-dd";
	
	public static final String FORMAT_DATE_MM = "yyyy-MM";
	
	public static final String FORMAT_DATE_YY = "yyyy";
	
	public static final String FORMAT_DATE_MDS = "yyyy-MM-dd HH:mm:ss";
	
	public static final String FORMAT_DATE_DD_EN = "yyyy年MM月dd HH时mm分";
	
	
	public static Date stringToDateMHS(Date dateTime) {
		String value = dateToYMDStr(dateTime)+" 00:00:00";
		SimpleDateFormat dateformat1=new SimpleDateFormat(FORMAT_DATE_MDS); 
		try {
			dateTime = dateformat1.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	public static Date stringToDateMDS2(String dateTimeStr) {
		Date dateTime = null;
		SimpleDateFormat dateformat1=new SimpleDateFormat(FORMAT_DATE_MDS); 
		try {
			dateTime = dateformat1.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static Date stringToDateMDS(Date dateTime) {
		String value = dateToYMDStr(dateTime)+" 23:59:59";
		SimpleDateFormat dateformat1=new SimpleDateFormat(FORMAT_DATE_MDS); 
		try {
			dateTime = dateformat1.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static String currentDateToString() {
		String dateTime="";
		SimpleDateFormat dateformat1=new SimpleDateFormat(FORMAT_DATE_MDS); 
		dateTime=dateformat1.format(new Date());
		System.out.println(dateTime);
		return dateTime;
	}
	
		
	
	public static String dateToString(Date date, String format) {
		DateFormat df1 = new SimpleDateFormat(format);
		String time = df1.format(date);
		return time;
	}

	public static String dateToYMDStr(Date date) {
		DateFormat df1 = new SimpleDateFormat(FORMAT_DATE_DD);
		String time = df1.format(date);
		return time;
	}
	
	public static String dateToYMDENStr(Date date) {
		DateFormat df1 = new SimpleDateFormat(FORMAT_DATE_DD_EN);
		String time = df1.format(date);
		return time;
	}
	public static String dateToYMDHMSStr(Date date) {
		DateFormat df1 = new SimpleDateFormat(FORMAT_LONG_MS);
		String time = df1.format(date);
		return time;
	}
	
	public static Date stringToDate(String date, String format) {
		DateFormat df1 = new SimpleDateFormat(format);
		Date dateTime = new Date();
		try {
			dateTime = df1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static Date shortStrToDate(String date) {
		DateFormat df1 = new SimpleDateFormat(FORMAT_DATE_DD);
		Date dateTime = new Date();
		try {
			dateTime = df1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static Date longStrToDate(String date) {
		DateFormat df1 = new SimpleDateFormat(FORMAT_LONG_MS);
		Date dateTime = new Date();
		try {
			dateTime = df1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

	//获取当前时间是第几季度
	public static  int getQuarter (Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month  = calendar.get(Calendar.MONTH);
		switch (month) {
			case 0:
			case 1:
			case 2:
			return 1;
			case 3:
			case 4:
			case 5:
			return 2;
			case 6:
			case 7:
			case 8:
			return 3;
			default:
			return 4;
		}
	}
	
	//获得当前时间是第几周
	public static int getWeekNum(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	//日期
	public static String getBeforeDay(String date,int num){
		Calendar calendar = Calendar.getInstance();
		try {
			Date dates = new SimpleDateFormat(FORMAT_DATE_DD).parse(date);
			calendar.setTime(dates);
			calendar.add(Calendar.DATE, -num);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat(FORMAT_DATE_DD).format(calendar.getTime());
	}
	
	public static String getBeforeDay(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -num);
		return new SimpleDateFormat(FORMAT_DATE_DD).format(calendar.getTime());
	}
	
	public static Date getAfterDay(Date date,int num){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(date);
			calendar.add(Calendar.DATE, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendar.getTime();
	}
	
	public static String getRunTime(Date effectTime,Date pastTime){
		String runTime = "";
		Date currentDate = new Date();
		long  time=currentDate.getTime()-effectTime.getTime();
		if(pastTime.compareTo(currentDate) < 0){
			time=pastTime.getTime() - effectTime.getTime();
		}
		time=time/1000;
		if(time>0){
			int day= new Long(time/86400).intValue();
			int hour= new Long((time-day*86400)/3600).intValue();
			int munite= new Long((time-day*86400-hour*3600)/60).intValue();
			int seconds= new Long(time-day*86400-hour*3600-munite*60).intValue();
			runTime=day+"天"+hour+"时"+munite+"分"+seconds+"秒";
		}else{
			runTime="未运行";
		}
		return runTime;
	} 
	
	/**
	 * 比较当前时间
	 * @param tokenTime
	 * @param invalidateTime
	 * @return
	 */
	public static boolean compareDate(Date tokenTime,int invalidateTime){
		
		boolean flag = false;
		//获取当前时间
		Date nowTime = new Date();
		//日期比较\
		long plusTime = nowTime.getTime() - tokenTime.getTime();
		if( plusTime/1000/60 <= invalidateTime ){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 根据当前时间获取凌晨点
	 */
	public static Date getZero(){
		Date zeroDate = null;
		String zeroTime = dateToYMDStr(new Date());
		zeroDate = shortStrToDate(zeroTime);
		return zeroDate;
	}
	
	public static void main(String[] args) {
//		Date date = stringToDate("2014-01-01", "yyyy-MM-dd");
//		System.out.println(dateToYMDENStr(new Date()));
//		
//		if(false){
//			System.out.println("111");
//		}
//		currentDateToString();
		System.out.println(new Date().getTime());
		System.out.println(new Date(14390038547L).toLocaleString());
//		System.out.println(getZero().toLocaleString());
		
	}
	
    
}
