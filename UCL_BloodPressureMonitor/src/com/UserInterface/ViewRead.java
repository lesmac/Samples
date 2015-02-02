package com.UserInterface;



import java.util.Calendar;

import com.Common.R;
import com.Controller.AppState;
import com.Controller.generalCtrl;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class ViewRead extends Activity {
	public generalCtrl GCtrl;
    private void makeLogEntry(String s){
  		Log.d("view read",s);
  	    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GCtrl= new generalCtrl(this);
        AppState app = (AppState) getApplication();
        makeLogEntry("A new GCtrl has been created");
  //      makeLogEntry("GCtrl.bparrayctr is"+GCtrl.bparrayctr);
        setContentView(R.layout.viewread);
        Bundle extras=getIntent().getExtras();
        TimePicker VRTime= (TimePicker) findViewById(R.id.timePickerVR);
        VRTime.setCurrentHour(extras.getInt("hourER"));
        VRTime.setCurrentMinute(extras.getInt("minuteER"));
        TextView ReadNum = (TextView) findViewById(R.id.readingnumview);
        ReadNum.setText(String.valueOf((app.getBPArrayCtr()+1)));
        EditText showDia = (EditText) findViewById(R.id.ShowDia);
        Double currentDia=app.getBPArrayAt(app.getBPArrayCtr()).Diastolic;
        showDia.setText(Double.toString(currentDia));
        EditText showSys = (EditText) findViewById(R.id.ShowSys);
        Double currentSys=app.getBPArrayAt(app.getBPArrayCtr()).Systolic;
        showSys.setText(Double.toString(currentSys));
       // showDia.setText(String.valueOf(//unpack bundle to get numericBP from enterreading));
        //should be color coded based on where it falls in the BP
        //healthiness spectrum.
        //So need 3 things from analyser: sys value, dia value,
        //and a color for the background of each.
    Button btn_nextRead = (Button) findViewById(R.id.nextRead);
    btn_nextRead.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.nextRead);
		}
        });
    Button btn_ManReadDone = (Button) findViewById(R.id.manreaddone);
    btn_ManReadDone.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.manreaddone);
		}
        });
}
}