package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;
import com.DataStructure.userInfo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration3 extends Activity {
	private static final String TAG = "Registration3";
	public static userInfo r3userinfoobject;
    public void makeLogEntry(String s){
  		Log.d(TAG,s);
  	    }
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GCtrl = new generalCtrl(this);
        setContentView(R.layout.registration3);
        Bundle extras=getIntent().getExtras();
        r3userinfoobject = (userInfo) extras.getSerializable("usInfR2");
        GCtrl.actOncreat();
        Button btn_YesMed = (Button) findViewById(R.id.medicationYes);
        //send this to db
        Button btn_NoMed = (Button) findViewById(R.id.medicationNo);
        //send this to db
        Button btn_reg3Next = (Button) findViewById(R.id.reg3next);
        btn_reg3Next.setOnClickListener(new View.OnClickListener() {
      		
      		@Override
      		public void onClick(View v) {
      			GCtrl.Buttonhandler(R.id.reg3next);
      		}
      	});
    }
    
    
}
