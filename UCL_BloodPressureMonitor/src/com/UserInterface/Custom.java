package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Custom extends Activity {
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
        GCtrl= new generalCtrl(this);
        EditText txt_NoReadingsCust = (EditText) findViewById(R.id.noreadingscust);
        String NoReadingsCust =txt_NoReadingsCust.toString();
        EditText txt_IntervalCust = (EditText) findViewById(R.id.intervalcust);
        EditText txt_DurationCust = (EditText) findViewById(R.id.durationcust);
        Button btn_BeginCuffStudyCust = (Button) findViewById(R.id.begincuffstudycust);
        btn_BeginCuffStudyCust.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.begincuffstudycust);
    		}
            }); 
}
}