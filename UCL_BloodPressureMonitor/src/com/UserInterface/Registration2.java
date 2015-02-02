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

public class Registration2 extends Activity {
	private generalCtrl GCtrl;
	public static userInfo userinfoobject;
	private static final String TAG = "Registration2";
    public void makeLogEntry(String s){
  		Log.d(TAG,s);
  	    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();
        userinfoobject = (userInfo) extras.getSerializable("usInfLR");
        setContentView(R.layout.registration2);
        GCtrl= new generalCtrl(this);
        GCtrl.actOncreat();
        Button btn_YesHT = (Button) findViewById(R.id.hypertensionYes);
        btn_YesHT.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.hypertensionYes);
		}
        });
        Button btn_NoHT = (Button) findViewById(R.id.hypertensionNo);
        btn_NoHT.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.hypertensionNo);
		}
        });
        Button btn_reg2Next = (Button) findViewById(R.id.reg2next);
        btn_reg2Next.setOnClickListener(new View.OnClickListener() {
      		
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.reg2next);
    		}
    	});
    }
}