package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;  
import android.widget.EditText;

public class Registration1 extends Activity {
	public static Bundle extrastopass;
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GCtrl= new generalCtrl(this);
        setContentView(R.layout.registration1);
        extrastopass=getIntent().getExtras();
        //userinfoobject = extras.getSerializable;
        Button btn_ArrhthmY = (Button) findViewById(R.id.btn_registration1yes);
        btn_ArrhthmY.setOnClickListener(new View.OnClickListener() {
      		
      		@Override
      		public void onClick(View v) {
      			GCtrl.Buttonhandler(R.id.btn_registration1yes);
      		}
      	});
        GCtrl.actOncreat();
        Button btn_ArrhthmN = (Button) findViewById(R.id.btn_registration1no);
        btn_ArrhthmN.setOnClickListener(new View.OnClickListener() {
  		
  		@Override
  		public void onClick(View v) {
  			GCtrl.Buttonhandler(R.id.btn_registration1no);
  		}
  	});
    }
} 