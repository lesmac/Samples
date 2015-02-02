package com.Analyzer;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeFormat {
public static String dateToString(GregorianCalendar adate){
	String dateString=adate.toString();
	String Hour, Minute, Day, Month, Second, Millisecond, Year;
	int hour=adate.HOUR_OF_DAY;
	int minute=adate.MINUTE;
	int day=adate.DAY_OF_MONTH;
	int month=adate.MONTH;
	int second=adate.SECOND;
	int millisecond=adate.MILLISECOND;
	int year=adate.YEAR;
	Hour=String.valueOf(hour);
	if(hour<10){
		Hour="0"+Hour;
	} 
	Minute=String.valueOf(minute);
	if(minute<10){
		Minute="0"+Minute;
	}
	Day=String.valueOf(day);
	if(day<10){
		Day="0"+Day;
	}
	Month=String.valueOf(month);
	if(month<10){
		Month="0"+Month;
	}
	Second=String.valueOf(second);
	if(second<10){
		Second="0"+Second;
	}
	Millisecond=String.valueOf(millisecond);
	if(millisecond<10){
		Millisecond="0"+Millisecond;
	}
	Year=String.valueOf(year);
	dateString="YYYY-MM-DD 23:59:5900";
	dateString=(Year+"-"+Month+"-"+Day+" "+Hour+":"+Minute+":"+Second+Millisecond);
	return dateString;
}
   GregorianCalendar stringToDate(String aDateString){
//	GregorianCalendar adate=new GregorianCalendar();
	char[] chAr=aDateString.toCharArray();
	int syear=Integer.valueOf(chAr[0]+chAr[1]+chAr[2]+chAr[3]);
	int smonth=Integer.valueOf(chAr[5]+chAr[6]);
	int sday=Integer.valueOf(chAr[8]+chAr[9]);
	int shour=Integer.valueOf(chAr[11]+chAr[12]);
	int sminute=Integer.valueOf(chAr[14]+chAr[15]);
	int ssecond=Integer.valueOf(chAr[17]+chAr[18]);
	GregorianCalendar adate=new GregorianCalendar(syear, smonth, sday, shour, sminute, ssecond);
	return adate;
	   
   }
}
