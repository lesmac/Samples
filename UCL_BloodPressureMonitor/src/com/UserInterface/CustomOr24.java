package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomOr24 extends Activity {
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customor24);
        GCtrl= new generalCtrl(this);
    Button btn_24HrABP = (Button) findViewById(R.id.a24HrABP);
    btn_24HrABP.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.a24HrABP);
    		}
    	});
    Button btn_Custom = (Button) findViewById(R.id.custom);
    btn_Custom.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.custom);
		}
	});
}
}
