package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BTRead extends Activity {
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GCtrl= new generalCtrl(this);
        setContentView(R.layout.btread);
        Button btn_BT = (Button) findViewById(R.id.OKBT);
        btn_BT.setOnClickListener(new View.OnClickListener() {
        		@Override
        		public void onClick(View v) {
        			GCtrl.Buttonhandler(R.id.OKBT);
        		}
        	});
}
}
