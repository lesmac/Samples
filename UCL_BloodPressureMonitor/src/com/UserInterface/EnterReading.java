package com.UserInterface;



import java.util.Calendar;

import com.Common.R;
import com.Controller.AppState;
import com.Controller.generalCtrl;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class EnterReading extends Activity {
	Button ertime,erdate;
	private generalCtrl GCtrl;
    static final int TIME_DIALOG_ID=0;
    static final int DATE_DIALOG_ID=1;
//    public static int userhour, userminute, userday, usermonth, useryear;
    public static int aHour,aMinute, aDay, aMonth, aYear;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GCtrl= new generalCtrl(this);
        setContentView(R.layout.enterreading);
        TextView ReadNum = (TextView) findViewById(R.id.readingnum);
        AppState app = (AppState) getApplication();
        ReadNum.setText(String.valueOf((app.getBPArrayCtr()+1)));
        Calendar now=Calendar.getInstance();
         aHour=now.get(Calendar.HOUR_OF_DAY);
         aMinute=now.get(Calendar.MINUTE);
         aDay=now.get(Calendar.DAY_OF_MONTH);
         aMonth=now.get(Calendar.MONTH);
         aYear=now.get(Calendar.YEAR);

        ertime= (Button) findViewById(R.id.ERTime);
        ertime.setText("Time of Measurement is "+String.valueOf(aHour)+":"+String.valueOf(aMinute));
        ertime.setOnClickListener(new View.OnClickListener(){
        	public void onClick (View v){
        		showDialog(TIME_DIALOG_ID);
        	}
        });
        erdate= (Button) findViewById(R.id.ERDate);
        erdate.setText("Date of Measurement is "+getmonth(aMonth)+" "+String.valueOf(aDay)+", "+String.valueOf(aYear));
        erdate.setOnClickListener(new View.OnClickListener(){
        	public void onClick (View v){
        		showDialog(DATE_DIALOG_ID);
        	}
        });

        Button btn_OKRead = (Button) findViewById(R.id.OKRead);
        btn_OKRead.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.OKRead);
    		}
            });
        Button btn_ManReadDone = (Button) findViewById(R.id.manreaddone);
        btn_ManReadDone.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.manreaddone);
    		}
            });
        Button btn_clearRead = (Button) findViewById(R.id.clearread);
        btn_clearRead.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.clearread);
    		}
            });
    }
    
    //Time methods follow
    private TimePickerDialog.OnTimeSetListener ourTimeSetListener=
    		new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					aHour=hourOfDay;
					aMinute=minute;
					ertime.setText("Time :"+aHour+":"+aMinute);
				}
			};
			private DatePickerDialog.OnDateSetListener ourDateSetListener=
			   new DatePickerDialog.OnDateSetListener() {
							
							@Override
							public void onDateSet(DatePicker view, int year, int month, int day) {
								aYear=year;
								aMonth=month;
								aDay=day;
								erdate.setText("Date of Measurement is "+getmonth(aMonth)+" "+String.valueOf(aDay)+", "+String.valueOf(aYear));
								}
	 		};
	 		
			@Override
			protected Dialog onCreateDialog(int timeordate){
				switch(timeordate){
				case TIME_DIALOG_ID: return new TimePickerDialog
						(this, ourTimeSetListener, aHour, aMinute, false);
				case DATE_DIALOG_ID: return new DatePickerDialog
						(this, ourDateSetListener, aYear, aMonth, aDay);
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
	 