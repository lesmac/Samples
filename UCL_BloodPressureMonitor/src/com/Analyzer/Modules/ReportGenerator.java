package com.Analyzer.Modules;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.achartengine.GraphicalView;

import com.Common.R;
import com.Controller.AppState;
import com.DataInterface.ConfigAdapter.ConfigAdaptor;
import com.DataInterface.FileOp.FileOperator;
import com.DataStructure.numericBP;
import com.DataStructure.userInfo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Environment;

public class ReportGenerator {
	private Activity act;
	
	private numericBP[] bp;
	private userInfo user;
	private String username;
	
	
	private float y; // print indicator;
	
	private final float A4BmpSizeX = 210*5;
	private final float A4BmpSizeY = 297*5;
	
	private final float PARA_GAP = 40;
	private final float LINE_GAP = 20;
	private final float COL_GAP = 120;
	
	private final float CENTERX = A4BmpSizeX/2; 
	private final float LEFT_EDGE = 100;
	
	
	private final float Y_1st_LINE = 150;
	
	
	
	private final String[][] C_USERINFO = {{"Patient Name:", 
		                                      "Patient ID:", 
		                                      "Height:",  
		                                      "Weight:",},
		                                     {"Date of Birth:", 
		                                      "Age:", 
		                                      "Gender:", 
		                                      "Race:"}};
		                                                       
	private final String[] STUDY = {"Study Date:","Study Time:"};
	
	private final String[] OTHER = {"Attending Physician:","Technician:"};
	
	private final String[] Summary1 = {"Total Rec. Time",     
		                                 "Statistics Intervals from Ambulatory BP Device",
		                                 "Duration Day Time", 
		                                 "Duration Night Time",
		                                 "Measuring Method"};	
	private final String Summary2 = "Average sys/dia(mmHg)";	
	private final String[] Summary3 = {"Day Time",        	                                 
		                               "Valid measurements: ", 
		                               "Syst. BP readings above 135 mmHg (%)",
		                               "Diast. BP readings above 85 mmHg (%)"};	
	private final String[] Summary4 = {"Night/Wake-up Time",                              
		                                 "Valid measurements: 9 of 9 (100%)",
		                                 "Syst. BP readings above 125 mmHg (%)",
		                                 "Diast. BP readings above 80 mmHg (%)"};	                                 
	private final String[] Summary5 = {"Day-Time Average sys/dia(mmHg)", 	                                                                   
		                                 "Night-Time Average sys/dia(mmHg)",	                                                                   
		                                 "Diff.Day/Night Avg. sys/dia(%)"};
	private String[] Summary1_re = new String[5];
	private String   Summary2_re = "";
	private String[] Summary3_re = new String[4];
	private String[] Summary4_re = new String[4];	
	private String[] Summary5_re = new String[3];
	private String starttime = "";
	private String endtime = "";
	private HashMap<String,numericBP> stats;
	
    public ReportGenerator(Activity act){
    	y = 0;
    	this.act = act;
    }
    
    
    
    public void drawFullReport(){
    	AppState app = (AppState) act.getApplication();
    	username = app.getCurrentuser();
    	//get config here
    	 starttime = "1";
    	 endtime = "2";
    	
    	AnalyzerIO aIO = new AnalyzerIO(act);
    	bp = aIO.loadFromDatabase(starttime, endtime);
    	createSummary();
    	
    	Bitmap firstpage = drawFullReportpage1();
    	//Bitmap chart = drawChart();
    	

    	String filePath = app.getfolder();
    	File a=new File(filePath);
        //check the dir;
        if (!a.exists()){
            a.mkdir();
        }
    	File report = new File(filePath , "reportpage1.jpg");
    	
    	FileOperator fop = new FileOperator(report);
    	fop.writeBitmap(firstpage, Bitmap.CompressFormat.JPEG);
    }
    
    public void drawSimpleReport(){
    	
    }
    
    public Bitmap drawFullReportpage1(){
    	
    	Bitmap freport = Bitmap.createBitmap((int)A4BmpSizeX,(int) A4BmpSizeY, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(freport);
		Paint mPaint = new Paint();
		float temp = 0; // for temp y
		
		c.drawColor(Color.GRAY);
		//initialize paint
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setTextAlign(Paint.Align.CENTER);
		mPaint.setTextSize(20);
		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		
		c.drawText("AMBULATORY BLOOD PRESSURE REPORT", CENTERX,Y_1st_LINE, mPaint);
		
		mPaint.setTextSize(18);
		mPaint.setTextAlign(Paint.Align.LEFT);
		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
		
		/*
		//LOAD USERINFO here;
		user = ConfigAdaptor.loadSharedPref(username, act);
		// String[][] userinfo
		String[][] userinfo = new String[C_USERINFO.length][C_USERINFO[0].length];
		userinfo[0][0] = user.firstname + user.lastname;
		userinfo[0][1] = "";
		userinfo[0][2] = "";
		userinfo[0][3] = "";
		userinfo[1][0] = "";
		userinfo[1][1] = "";
		userinfo[1][2] = "";
		userinfo[1][3] = "";*/
		
		y = Y_1st_LINE + PARA_GAP;
	    for(int i=0;i<C_USERINFO.length;i++){
	        temp = y;
	  	    for(int j=0;j<C_USERINFO[i].length;j++){
	  		    temp = y + LINE_GAP*j;
	  		    c.drawText(C_USERINFO[i][j], LEFT_EDGE+CENTERX*i, temp, mPaint);  
	  		   //c.drawText(C_USERINFO[i][j]+userinfo[i][j], LEFT_EDGE+CENTERX*i, temp, mPaint); 
	        }
		}
	    
	    y = temp  + PARA_GAP;
	    
	    for(int i=0;i<STUDY.length;i++){
            temp = y + LINE_GAP*i;
	    	c.drawText(STUDY[i], LEFT_EDGE, temp, mPaint); 
	    }
		
	    for(int i=0;i<OTHER.length;i++){
	    	c.drawText(OTHER[i], LEFT_EDGE+CENTERX, y + LINE_GAP*i, mPaint); 
	    	if(i == OTHER.length-1){
	    		y = y + LINE_GAP*i;
	    	}
	    }
	    
		
	    		
		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
		
		y = y + PARA_GAP;
		
		c.drawText("Medication", LEFT_EDGE, y, mPaint);
		c.drawText("Comment", LEFT_EDGE, y = y + LINE_GAP, mPaint);
		c.drawText("Reason for test", LEFT_EDGE, y = y + LINE_GAP, mPaint);
		
		
		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
		
		c.drawText("Summary", LEFT_EDGE, y = y + PARA_GAP, mPaint);
		c.drawLine(LEFT_EDGE, y+5, LEFT_EDGE+80, y+5, mPaint);
		
		
		
		
		mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
		
		temp = y;   // record current line indicator;
		c.drawText("Statistics Summary from"+ starttime,
				LEFT_EDGE,y = y + PARA_GAP,mPaint);
		c.drawText("until" + endtime,
				LEFT_EDGE,y = y + LINE_GAP,mPaint);
		
		y = y + PARA_GAP - LINE_GAP;
		for(int i = 0; i<Summary1.length; i++){
			c.drawText(Summary1[i], LEFT_EDGE, y = y + LINE_GAP, mPaint);
		}
		
		c.drawText(Summary2, LEFT_EDGE, y = y + PARA_GAP, mPaint);
		
		y = y + PARA_GAP/2;
		
		for(int i = 0; i<Summary3.length; i++){
			if(i == 0){
				c.drawText(Summary3[i], LEFT_EDGE, y = y + LINE_GAP, mPaint);
			}
			else{
			    c.drawText(Summary3[i], LEFT_EDGE+20, y = y + LINE_GAP, mPaint);
			}
		}
		
		y = y + PARA_GAP/2;
		
		for(int i = 0; i<Summary4.length; i++){
			if(i == 0){
				c.drawText(Summary4[i], LEFT_EDGE, y = y + LINE_GAP, mPaint);
			}
			else{
			    c.drawText(Summary4[i], LEFT_EDGE+20, y = y + LINE_GAP, mPaint);
			}
		}
		
		y = y + PARA_GAP/3;
		for(int i = 0;i<Summary5.length;i++){
			c.drawText(Summary4[i], LEFT_EDGE, y = y + PARA_GAP*2/3, mPaint);
		}
		
		c.drawText("Statistics Summary from"+ starttime,
				LEFT_EDGE,y = y + PARA_GAP,mPaint);
		c.drawText("until" + endtime,
				LEFT_EDGE,y = y + LINE_GAP,mPaint);
		
		
		
		mPaint.setTextAlign(Paint.Align.CENTER);
        
		c.drawText(bp.length +" Bylood Pressure Measurement(s)", LEFT_EDGE+CENTERX+50, temp + PARA_GAP, mPaint);
		/*
		temp = temp + LINE_GAP;
		temp = temp + PARA_GAP - LINE_GAP;
		for(int i = 0; i<Summary1.length; i++){
			c.drawText(Summary1[i], LEFT_EDGE+CENTERX+50, temp = temp + LINE_GAP, mPaint);
		}
		
		c.drawText(Summary2, LEFT_EDGE+CENTERX+50, temp = temp + PARA_GAP, mPaint);
		
		temp = temp + PARA_GAP/2;
		
		for(int i = 0; i<Summary3.length; i++){
			if(i == 0){
				c.drawText(Summary3[i], LEFT_EDGE+CENTERX+50, temp = temp + LINE_GAP, mPaint);
			}
			else{
			    c.drawText(Summary3[i], LEFT_EDGE+CENTERX+50, temp = temp + LINE_GAP, mPaint);
			}
		}
		
		temp = temp + PARA_GAP/2;
		
		for(int i = 0; i<Summary4.length; i++){
			if(i == 0){
				c.drawText(Summary4[i], LEFT_EDGE+CENTERX+50, temp = temp + LINE_GAP, mPaint);
			}
			else{
			    c.drawText(Summary4[i], LEFT_EDGE+CENTERX+50, temp = temp + LINE_GAP, mPaint);
			}
		}
		
		temp = temp + PARA_GAP/3;
		for(int i = 0;i<Summary5.length;i++){
			c.drawText(Summary4[i], LEFT_EDGE+CENTERX+50, temp = temp + PARA_GAP*2/3, mPaint);
		}
		*/
		
		c.drawText(bp.length +" Blood Pressure Measurement(s)", LEFT_EDGE+CENTERX+50, y-LINE_GAP, mPaint);
		
	    y = y + PARA_GAP;
		temp = y + PARA_GAP/2;
		
		mPaint.setTextAlign(Paint.Align.LEFT);
		c.drawText("sys.BP", LEFT_EDGE, temp = temp + LINE_GAP , mPaint);
		c.drawText("dia.BP", LEFT_EDGE, temp = temp + LINE_GAP, mPaint);
		c.drawText("mn.BP", LEFT_EDGE, temp = temp + LINE_GAP, mPaint);
		c.drawText("Heart Rate", LEFT_EDGE, temp = temp + LINE_GAP, mPaint);
		
		
		mPaint.setTextAlign(Paint.Align.CENTER);
	
		c.drawText("Minimum", LEFT_EDGE + COL_GAP+50, y, mPaint);
		c.drawText("Maximum", LEFT_EDGE + COL_GAP*3+50, y, mPaint);
		c.drawText("Average", LEFT_EDGE + COL_GAP*5+50, y, mPaint);
		c.drawText("SD", LEFT_EDGE + COL_GAP*6+50, y, mPaint);
		
		y = y + PARA_GAP;	
		numericBP minSys = stats.get(StatsAnalyzer.KEY_MIN_SYS);
		numericBP maxSys = stats.get(StatsAnalyzer.KEY_MAX_SYS);
		c.drawText(Double.toString(minSys.Systolic), LEFT_EDGE + COL_GAP+50, y, mPaint);
		c.drawText(minSys.time.substring(0, 15), LEFT_EDGE + COL_GAP*2+50, y, mPaint);
		c.drawText(Double.toString(maxSys.Systolic), LEFT_EDGE + COL_GAP*3+50, y, mPaint);		
		c.drawText(maxSys.time.substring(0, 15), LEFT_EDGE + COL_GAP*4+50, y, mPaint);
		c.drawText(String.format("%.1f", stats.get(StatsAnalyzer.KEY_AVE).Systolic), 
				   LEFT_EDGE + COL_GAP*5+50, y, mPaint);
		c.drawText(String.format("%.1f", stats.get(StatsAnalyzer.KEY_SD).Systolic), 
				   LEFT_EDGE + COL_GAP*6+50, y, mPaint);
		
		y = y + LINE_GAP;
		numericBP minDia = stats.get(StatsAnalyzer.KEY_MIN_DIA);
		numericBP maxDia = stats.get(StatsAnalyzer.KEY_MAX_DIA);
		c.drawText(Double.toString(minDia.Diastolic), LEFT_EDGE + COL_GAP+50, y, mPaint);
		c.drawText(minDia.time.substring(0, 15), LEFT_EDGE + COL_GAP*2+50, y, mPaint);		
		c.drawText(Double.toString(maxDia.Diastolic), LEFT_EDGE + COL_GAP*3+50, y, mPaint);
		c.drawText(maxDia.time.substring(0, 15), LEFT_EDGE + COL_GAP*4+50, y, mPaint);
		c.drawText(String.format("%.1f", stats.get(StatsAnalyzer.KEY_AVE).Diastolic), 
				   LEFT_EDGE + COL_GAP*5+50, y, mPaint);		
		c.drawText(String.format("%.1f", stats.get(StatsAnalyzer.KEY_SD).Diastolic),
				   LEFT_EDGE + COL_GAP*6+50, y, mPaint);
		
		y = y + LINE_GAP;
		numericBP minMn = stats.get(StatsAnalyzer.KEY_MIN_MAP);
		numericBP maxMn = stats.get(StatsAnalyzer.KEY_MAX_MAP);
		c.drawText(Double.toString(minMn.MAP), LEFT_EDGE + COL_GAP+50, y, mPaint);
		c.drawText(minMn.time.substring(0, 15), LEFT_EDGE + COL_GAP*2+50, y, mPaint);
		c.drawText(Double.toString(maxMn.MAP), LEFT_EDGE + COL_GAP*3+50, y, mPaint);
		c.drawText(maxMn.time.substring(0, 15), LEFT_EDGE + COL_GAP*4+50, y, mPaint);			
		c.drawText(String.format("%.1f", stats.get(StatsAnalyzer.KEY_AVE).MAP), 
				   LEFT_EDGE + COL_GAP*5+50, y, mPaint);
		c.drawText(String.format("%.1f", stats.get(StatsAnalyzer.KEY_SD).MAP), 
				   LEFT_EDGE + COL_GAP*6+50, y, mPaint);
		
		y = y + LINE_GAP;
		c.drawText(Double.toString(stats.get(StatsAnalyzer.KEY_MIN_PULSE).pulse), 
				   LEFT_EDGE + COL_GAP+50, y, mPaint);
		c.drawText(Double.toString(stats.get(StatsAnalyzer.KEY_MAX_PULSE).pulse), 
				   LEFT_EDGE + COL_GAP*3+50, y, mPaint);
		c.drawText(String.format("%.1f", stats.get(StatsAnalyzer.KEY_AVE).pulse), 
				   LEFT_EDGE + COL_GAP*5+50, y, mPaint);
		c.drawText(String.format("%.1f", stats.get(StatsAnalyzer.KEY_SD).pulse), 
				   LEFT_EDGE + COL_GAP*6+50, y, mPaint);
		
		
        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        mPaint.setTextAlign(Paint.Align.LEFT);
        
		c.drawText("Interpretation", LEFT_EDGE, y = y + PARA_GAP, mPaint);
		c.drawLine(LEFT_EDGE, y+5, LEFT_EDGE+110, y+5, mPaint);
		
				
		
		c.save();  
		
		return freport;
    	
    }
    
   
    private Bitmap drawChart(){
    	String[] Linetitles = {"Sys","Dia","mnBP"};
    	List<Date[]> timestamp = new ArrayList<Date[]>();
    	List<double[]> BPvalues = new ArrayList<double[]>();
    	double[] sys = new double[bp.length];
    	double[] dia = new double[bp.length];
    	double[] mnBP = new double[bp.length];
    	Date[] dates = new Date[bp.length];
    	
    	for(int i = 0; i<bp.length;i++){
    		sys[i] = bp[i].Systolic;
    		dia[i] = bp[i].Diastolic;
    		mnBP[i] = bp[i].MAP;
    		dates[i] = StatsAnalyzer.convertTimestamp(bp[i].time);
    	}
    	
    	BPvalues.add(sys);
    	BPvalues.add(dia);
    	BPvalues.add(mnBP);
    	
    	timestamp.add(dates);
    	timestamp.add(dates);
    	timestamp.add(dates);
    	
    	
    	
    	GraphicalView graph = new SensorValuesChart().execute(act, timestamp, BPvalues, Linetitles,
    			                                              "BP", "time", "BPHR", 0, 300);
    	
    	Bitmap bmp = graph.toBitmap();
    	return bmp;
    }
    
    private void drawSummary(){
    	
    }
    
    private void createSummary(){
    	starttime = bp[0].time;
    	endtime = bp[bp.length-1].time;
    	
    	
    	stats = StatsAnalyzer.mathAnalysisBP(bp);
    	
    	ArrayList<numericBP> day = new ArrayList<numericBP>();
    	ArrayList<numericBP> night = new ArrayList<numericBP>();
    	ArrayList<String> times = new ArrayList<String>();
    	ArrayList<String> daytimes = new ArrayList<String>();
    	ArrayList<String> nighttimes = new ArrayList<String>();
    	for(int i = 0; i<bp.length;i++){
    		String time = bp[i].time.substring(11, 12);
    		times.add(time);
    		if((time.compareTo("22")<0)&&(time.compareTo("07")>=0)){
    			daytimes.add(time);
    			day.add(bp[i]);
    		}
    		else{
    			nighttimes.add(time);
    			night.add(bp[i]);
    		}
    	}
    	
    	
    	
    	String duration = StatsAnalyzer.calcDuration((String[])times.toArray());
    	String dayduration = StatsAnalyzer.calcDayDuration((String[])daytimes.toArray());
    	String nightduration = StatsAnalyzer.calcNightDuration((String[])nighttimes.toArray());
    	// generate summary1
    	Summary1_re[0] = duration;
    	Summary1_re[1] = "Not Applicable";
    	Summary1_re[2] = dayduration;
    	Summary1_re[3] = nightduration;
    	Summary1_re[4] = "Not Applicable";
    	
    	// generate Summary2
        numericBP ave = stats.get(StatsAnalyzer.KEY_AVE);
        Summary2_re = String.format("%.1f/%.1f", ave.Systolic,ave.Diastolic);
        
        //generate Summary3
        double[] daylimit = {135,85};
        double[] percentage = StatsAnalyzer.calcBPpercentage((numericBP[])day.toArray(), daylimit);
        Summary3_re[0] = "";
        Summary3_re[0] = String.format("%d", day.size());
    	Summary3_re[1] = String.format("%.1f",percentage[0]);;
    	Summary3_re[2] =String.format("%.1f", percentage[1]);;
    	
    	//generate Summary4
        double[] nightlimit = {125,80};
        double[] percentage2 = StatsAnalyzer.calcBPpercentage((numericBP[])night.toArray(), nightlimit);
        Summary4_re[0] = "";
        Summary4_re[0] = String.format("%d", night.size());
    	Summary4_re[1] = String.format("%.1f",percentage2[0]);;
    	Summary4_re[2] =String.format("%.1f", percentage2[1]);;
    	
    	//generate Summary5
    	numericBP dayAve = StatsAnalyzer.getAve((numericBP[])day.toArray());
    	numericBP nightAve = StatsAnalyzer.getAve((numericBP[])night.toArray());
    	
    	Summary5_re[0] = String.format("%.1f/%.1f", dayAve.Systolic,dayAve.Diastolic);
    	Summary5_re[1] = String.format("%.1f/%.1f", nightAve.Systolic,nightAve.Diastolic);
    	
    	double diffSysPer = ((nightAve.Systolic - dayAve.Systolic)/dayAve.Systolic)*100;
    	double diffDiaPer = ((nightAve.Diastolic - dayAve.Diastolic)/dayAve.Diastolic)*100;
    	
    	Summary5_re[2] = String.format("%.1f/%.1f", diffSysPer,diffDiaPer);
    }
    
}
