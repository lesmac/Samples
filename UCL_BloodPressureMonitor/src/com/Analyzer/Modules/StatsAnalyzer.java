package com.Analyzer.Modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


import android.util.Log;

import com.DataStructure.numericBP;

public class StatsAnalyzer {
	
	 public final static String KEY_MAX_SYS = "Max_sys";
	 public final static String KEY_MIN_SYS = "Min_sys";
	 public final static String KEY_MAX_DIA = "Max_dia";
	 public final static String KEY_MIN_DIA = "Min_dia";
	 public final static String KEY_MAX_MAP = "Max_map";
	 public final static String KEY_MIN_MAP = "Min_map";
	 public final static String KEY_MAX_PULSE = "Max_pulse";
	 public final static String KEY_MIN_PULSE = "Min_pulse";
	 public final static String KEY_AVE = "average";
	 public final static String KEY_SD = "SD";
	 
	 
	 
	
	 static public class SysCompare implements Comparator<numericBP>{
		 public int compare(numericBP a, numericBP b){
			 return (int)(a.Systolic-b.Systolic);
		 }
	 }
	 
	 static public class DiaCompare implements Comparator<numericBP>{
		 public int compare(numericBP a, numericBP b){
			 return (int)(a.Diastolic-b.Diastolic);
		 }
	 }
	 static public class MapCompare implements Comparator<numericBP>{
		 public int compare(numericBP a, numericBP b){
			 return (int)(a.MAP-b.MAP);
		 }
	 }
	 
	 static public class PulseCompare implements Comparator<numericBP>{
		 public int compare(numericBP a, numericBP b){
			 return (int)(a.pulse-b.pulse);
		 }
	 }
	 
	
     public static HashMap<String,numericBP> mathAnalysisBP(final numericBP[] bp){
		
    	 HashMap<String,numericBP> BPresult = new HashMap<String,numericBP>();
    	 ArrayList<numericBP> bplist = new ArrayList<numericBP>();
    	 for(int i=0;i<bp.length;i++){
    		 bplist.add(bp[i]);
    	 }
         
    	 Collections.sort(bplist, new SysCompare());
    	 
    	 BPresult.put(KEY_MIN_SYS, bplist.get(0));
    	 BPresult.put(KEY_MAX_SYS, bplist.get(bplist.size()-1));
    	 
    	 Collections.sort(bplist, new DiaCompare());
    	 	
    	 BPresult.put(KEY_MIN_DIA, bplist.get(0));
    	 BPresult.put(KEY_MAX_DIA, bplist.get(bplist.size()-1));
    	 
    	 Collections.sort(bplist, new MapCompare());
    	 
    	 BPresult.put(KEY_MIN_MAP, bplist.get(0));
    	 BPresult.put(KEY_MAX_MAP, bplist.get(bplist.size()-1));
    	 
    	 Collections.sort(bplist, new PulseCompare());
    	 
    	 BPresult.put(KEY_MIN_PULSE, bplist.get(0));
    	 BPresult.put(KEY_MAX_PULSE, bplist.get(bplist.size()-1));
    	 
    	 BPresult.put(KEY_AVE, getAve(bp));
    	 BPresult.put(KEY_SD, getSD(bp));
    	 
    	 	 
    	 return BPresult;
    	 
     }
     
     public static Date convertTimestamp(String timestamp){
 		 Date d = null;	
 		try {
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
 			d = sdf.parse(timestamp); 			
 		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 		return d;
     }
     
     public static String calcDuration(final String[] times){  	 
    	 Date first = convertTimestamp(times[0]);
    	 Date last = convertTimestamp(times[times.length-1]);
    	 
    	 long durationvalue = last.getTime() - first.getTime();
    	 
    	 String duration = DurationFormatUtils.formatDurationWords(durationvalue, true, true);
    	 
    	 return duration;
     }
     public static String calcDayDuration(final String[] times){
    	 Date first = convertTimestamp(times[0]);
    	 Date last = convertTimestamp(times[times.length-1]);
    	 
    	 long durationvalue = last.getTime() - first.getTime();
    	 
    	 long days = durationvalue/24*60*60*1000;
    	 if( days>=1){
    		 durationvalue = durationvalue - days*9*60*60*1000;
    	 }
    	 
    	 String duration = DurationFormatUtils.formatDurationWords(durationvalue, true, true);
    	 
    	 return duration;
     }
     
     public static String calcNightDuration(final String[] times){
    	 Date first = convertTimestamp(times[0]);
    	 Date last = convertTimestamp(times[times.length-1]);
    	 
    	 long durationvalue = last.getTime() - first.getTime();
    	 
    	 long days = durationvalue/24*60*60*1000;
    	 if( days>=1){
    		 durationvalue = durationvalue - days*15*60*60*1000;
    	 }
    	 
    	 String duration = DurationFormatUtils.formatDurationWords(durationvalue, true, true);
    	 
    	 return duration;
     }
     
     public static String CheckDataValidation(final numericBP bp){
    	 String result = "OK";
    	 
    	 if((bp.Systolic >= 80) && (bp.Systolic <= 220) 
    	    &&(bp.Diastolic >= 60) && (bp.Diastolic <= 120)){
    		 return result;
    	 }
    	 else{
    		 result = "Data invalid:";
    		 if(bp.Systolic>220){
    			 result = result + " Sys too high";
    		 }
    		 if(bp.Systolic<80){
    			 result = result + " Sys too low";
    		 }
    		 if(bp.Diastolic>120){
    			 result = result + " Dia too high";
    		 }
    		 if(bp.Diastolic<60){
    			 result = result + "Dia too low";
    		 }
    		 return result;
    	 }  	 
     }
     
     public static double[] calcBPpercentage(final numericBP[] bp, double[] limit){
    	 double[] percentage = {0,0};
    	 int[] count ={0,0};
    	 if(limit.length!=2){
    		 return percentage;
    	 }
    	 else{
    		 for(int i = 0; i<bp.length;i++){
    			 if(bp[i].Systolic > limit[0]){
    				 count[0]++;
    			 }
    			 if(bp[i].Diastolic > limit[1]){
    				 count[1]++;
    			 }
    		 }
    		 
    		 percentage[0] = (count[0]/bp.length)*100;
    		 percentage[1] = (count[1]/bp.length)*100;
    		 
    		 return percentage;
    	 }
    	 
     }

     
     public static numericBP getAve(numericBP[] bp){
    	 numericBP aveBP = new numericBP();
    	 
    	 for(int i=0; i<bp.length;i++){
    		 aveBP.Systolic = aveBP.Systolic + bp[i].Systolic;
    		 aveBP.Diastolic = aveBP.Diastolic + bp[i].Diastolic;
    		 aveBP.MAP = aveBP.MAP + bp[i].MAP;
    		 aveBP.pulse = aveBP.pulse + bp[i].pulse;
    	 }
    	 
    	 aveBP.Systolic = aveBP.Systolic/bp.length;
		 aveBP.Diastolic = aveBP.Diastolic/bp.length;
		 aveBP.MAP = aveBP.MAP/bp.length;
		 aveBP.pulse = aveBP.pulse/bp.length;
    	    	 
    	 return aveBP;
     }
     
     private static numericBP getSD(numericBP[] bp){
    	 numericBP SDBP = new numericBP();
    	 
    	 double[] sys = new double[bp.length];
    	 double[] dia = new double[bp.length];
    	 double[] mp = new double[bp.length];
    	 double[] pul = new double[bp.length];
    	 
    	 for(int i=0;i<bp.length;i++){
    		 sys[i] = bp[i].Systolic;
    		 dia[i] = bp[i].Diastolic;
    		 mp[i] = bp[i].MAP;
    		 pul[i] = bp[i].pulse;
    	 }
    	 
    	 DescriptiveStatistics ds = new DescriptiveStatistics(sys);
    	 SDBP.Systolic = ds.getStandardDeviation();
    	 ds = new DescriptiveStatistics(dia);
    	 SDBP.Diastolic = ds.getStandardDeviation();
    	 ds = new DescriptiveStatistics(mp);
    	 SDBP.MAP = ds.getStandardDeviation();
    	 ds = new DescriptiveStatistics(pul);
    	 SDBP.pulse = ds.getStandardDeviation();
    	 
    	 
    	 return SDBP;
     }

     
}
