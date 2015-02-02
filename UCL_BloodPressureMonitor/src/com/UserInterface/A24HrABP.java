package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class A24HrABP extends Activity {
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a24hrabp);
        GCtrl= new generalCtrl(this);
        EditText txt_NoReadings24 = (EditText) findViewById(R.id.noreadings24);
        EditText txt_Interval24 = (EditText) findViewById(R.id.interval24);
        EditText txt_Duration24 = (EditText) findViewById(R.id.duration24);
        Button btn_BeginCuffStudy24 = (Button) findViewById(R.id.begincuffstudy24);
        btn_BeginCuffStudy24.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.begincuffstudy24);
    		}
            });
    }
    
    
}