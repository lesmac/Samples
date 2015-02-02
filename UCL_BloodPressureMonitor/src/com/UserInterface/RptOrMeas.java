package com.UserInterface;



import com.Common.R;
import com.Controller.AppState;
import com.Controller.generalCtrl;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RptOrMeas extends Activity {
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rptormeas);
        GCtrl= new generalCtrl(this);
        String root = Environment.getExternalStorageDirectory().toString();
    	String appSpace = getResources().getString(R.string.app_name);
    	String filePath = root + "/" + appSpace + "/" ;
    	
    	AppState app = (AppState) getApplication();
    	
    	app.setfolder(filePath);
    	Log.d("test", app.getfolder());
    	
    	
        Button btn_MakeReport = (Button) findViewById(R.id.makereport);
        btn_MakeReport.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.makereport);
    		}
            });
        Button btn_NewMeas = (Button) findViewById(R.id.newmeas);
        btn_NewMeas.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.newmeas);
    		}
            });
    }
    
    
}