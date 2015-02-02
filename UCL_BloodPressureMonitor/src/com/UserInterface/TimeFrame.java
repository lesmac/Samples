package com.UserInterface;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import com.Analyzer.AnalyzerControll;
import com.Analyzer.Modules.StatsAnalyzer.SysCompare;
import com.Common.R;
import com.Controller.AppState;
import com.Controller.generalCtrl;
import com.DataStructure.numericBP;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.net.Uri;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeFrame extends Activity {
private generalCtrl GCtrl;
Button StartTime, StartDate, EndTime, EndDate;
static final int STIME_DIALOG_ID=0;
static final int SDATE_DIALOG_ID=1;
static final int ETIME_DIALOG_ID=2;
static final int EDATE_DIALOG_ID=3;
public static int aHour,aMinute, aDay, aMonth, aYear;
public static int bHour,bMinute, bDay, bMonth, bYear;
String begin=null;
String end;
String TAG="TimeFrame";
/*boolean starttimezero;
boolean endtimezero;*/

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Log.d(TAG+"b", begin);
      GCtrl= new generalCtrl(this);
      
  /*    AppState app = (AppState) getApplication();
      
      app.setstate(AppState.STATE_CONFIGURATION);
      if (app.getstate() == AppState.STATE_BLUETOOTH_DONE){
    	  Log.d("test bt done",app.getBPArray()[0].time);

    	  AnalyzerControll aC = new AnalyzerControll();
    	  aC.analyzerCMD(AnalyzerControll.CMD_VALIDATION_CHECK_STORED, this, 
    			         app.getBPArray()[0]);
     	  Log.d(TAG+"A", begin);
      }
      else if(app.getstate() == AppState.STATE_MANUAL_INPUT_DONE){
    	  numericBP[] bps = app.getBPArray();
    	  ArrayList<numericBP> bplist = new ArrayList<numericBP>();
     	  for(int i=0;i<bps.length;i++){
     		 bplist.add(bps[i]);
     	  }
     	  Collections.sort(bplist, new SysCompare());
     	  begin = bplist.get(0).time;
     	  end= bplist.get(bplist.size()-1).time;
     	  Log.d(TAG, begin);
      }
      Log.d(TAG+"c", begin);*/
      
            
      setContentView(R.layout.timeframe);
      Log.d(TAG+"d", begin);
     GregorianCalendar now=new GregorianCalendar();
       now= (GregorianCalendar)Calendar.getInstance();
       aHour=now.get(Calendar.HOUR_OF_DAY);
       aMinute=now.get(Calendar.MINUTE);
       aDay=now.get(Calendar.DAY_OF_MONTH);
       aMonth=now.get(Calendar.MONTH);
       aYear=now.get(Calendar.YEAR);
       bHour=now.get(Calendar.HOUR_OF_DAY);
       bMinute=now.get(Calendar.MINUTE);
       bDay=now.get(Calendar.DAY_OF_MONTH);
       bMonth=now.get(Calendar.MONTH);
       bYear=now.get(Calendar.YEAR);
     /*char[] chAr= begin.toCharArray();
	aYear=Integer.valueOf(chAr[0]+chAr[1]+chAr[2]+chAr[3]);
	aMonth=Integer.valueOf(chAr[5]+chAr[6]);
	aDay=Integer.valueOf(chAr[8]+chAr[9]);
	aHour=Integer.valueOf(chAr[11]+chAr[12]);
	aMinute=Integer.valueOf(chAr[14]+chAr[15]);
	
    char[] chArb= begin.toCharArray();
	bYear=Integer.valueOf(chArb[0]+chArb[1]+chArb[2]+chArb[3]);
	bMonth=Integer.valueOf(chArb[5]+chArb[6]);
	Log.d(TAG, String.valueOf(bMonth));
	bDay=Integer.valueOf(chArb[8]+chArb[9]);
	bHour=Integer.valueOf(chArb[11]+chArb[12]);
	bMinute=Integer.valueOf(chArb[14]+chArb[15]);*/
	//aSecond=Integer.valueOf(chAr[17]+chAr[18]);
       Log.d(TAG+"f", begin);
      StartTime= (Button) findViewById(R.id.StartTime);
      StartTime.setText("Time of First Measurement is "+String.valueOf(aHour)+":"+String.valueOf(aMinute));
      
      StartTime.setOnClickListener(new View.OnClickListener(){
      	public void onClick (View v){
      		showDialog(STIME_DIALOG_ID);
      	}
      });
      StartDate= (Button) findViewById(R.id.StartDate);
      StartDate.setText("Date of First Measurement is "+getmonth(aMonth)+" "+String.valueOf(aDay)+", "+String.valueOf(aYear));
      StartDate.setOnClickListener(new View.OnClickListener(){
      	public void onClick (View v){
      		showDialog(SDATE_DIALOG_ID);
      	}
      });
      EndTime= (Button) findViewById(R.id.EndTime);
      EndTime.setText("Time of Last Measurement is "+String.valueOf(bHour)+":"+String.valueOf(bMinute));
      EndTime.setOnClickListener(new View.OnClickListener(){
      	public void onClick (View v){
      		showDialog(ETIME_DIALOG_ID);
      	}
      });
      EndDate= (Button) findViewById(R.id.EndDate);
      EndDate.setText("Date of Last Measurement is "+getmonth(bMonth)+" "+String.valueOf(bDay)+", "+String.valueOf(bYear));
      EndDate.setOnClickListener(new View.OnClickListener(){
      	public void onClick (View v){
      		showDialog(EDATE_DIALOG_ID);
      	}
      });

      Button btn_entertime = (Button) findViewById(R.id.btn_entertime);
      btn_entertime.setOnClickListener(new View.OnClickListener() {
  		@Override
  		public void onClick(View v) {
  			GCtrl.Buttonhandler(R.id.btn_entertime);
  		}
          });

      
  }
  
  //Time methods follow
  private TimePickerDialog.OnTimeSetListener startTimeSetListener=
  		new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					aHour=hourOfDay;
					aMinute=minute;
					StartTime.setText("Time :"+aHour+":"+aMinute);
				}
			};
  private DatePickerDialog.OnDateSetListener startDateSetListener=
			   new DatePickerDialog.OnDateSetListener() {
							
							@Override
							public void onDateSet(DatePicker view, int year, int month, int day) {
								aYear=year;
								aMonth=month;
								aDay=day;
								StartDate.setText("Date of Measurement is "+getmonth(aMonth)+" "+String.valueOf(aDay)+", "+String.valueOf(aYear));
								}
	 		};
  private TimePickerDialog.OnTimeSetListener endTimeSetListener=
	 			 new TimePickerDialog.OnTimeSetListener() {
	 							
	 						@Override
	 						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	 								aHour=hourOfDay;
	 								aMinute=minute;
	 								StartTime.setText("Time :"+aHour+":"+aMinute);
	 							}
	 		};
	private DatePickerDialog.OnDateSetListener endDateSetListener=
	 			new DatePickerDialog.OnDateSetListener() {
	 										
	 						@Override
	 						public void onDateSet(DatePicker view, int year, int month, int day) {
	 								aYear=year;
	 								aMonth=month;
	 								aDay=day;
	 								StartDate.setText("Date of Measurement is "+getmonth(aMonth)+" "+String.valueOf(aDay)+", "+String.valueOf(aYear));
	 							}
	 		};
	 		
			@Override
			protected Dialog onCreateDialog(int timeordate){
				switch(timeordate){
				case STIME_DIALOG_ID: return new TimePickerDialog
						(this, startTimeSetListener, aHour, aMinute, false);
				case SDATE_DIALOG_ID: return new DatePickerDialog
						(this, startDateSetListener, aYear, aMonth, aDay);
				case ETIME_DIALOG_ID: return new TimePickerDialog
						(this, endTimeSetListener, bHour, bMinute, false);
				case EDATE_DIALOG_ID: return new DatePickerDialog
						(this, endDateSetListener, bYear, bMonth, bDay);
			}
				return null;
				}
			public String getmonth(int amonth){
				String monthstring = "error";
				switch(amonth){
				case 0:monthstring="January";
				 break;
				case 1:monthstring="February";
				 break;
				case 2:monthstring="March";
				 break;
				case 3:monthstring="April";
				 break;
				case 4:monthstring="May";
			    break;
				case 5:monthstring="June";
				break;
				case 6:monthstring="July";
				break;
				case 7:monthstring="August";
				break;
				case 11:monthstring="December";
				break;
				case 8:monthstring="September";
				break;
				case 9:monthstring="October";
				break;
				case 10:monthstring="November";
				break;
				}
			//default:umonthstring="error";
				return monthstring;
		}
			
}
	 
  
