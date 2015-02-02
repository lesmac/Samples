package com.UserInterface;



import com.Common.R;
import com.Controller.AppState;
import com.Controller.generalCtrl;
import com.DataInterface.ConfigAdapter.ConfigAdaptor;
import com.DataStructure.userInfo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CuffOrManualChoice extends Activity {
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cufformanualchoice);
        

    	
        GCtrl = new generalCtrl(this);
        GCtrl.actOncreat();
        Button btn_CuffConnect = (Button) findViewById(R.id.cuffconnect);
        btn_CuffConnect.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.cuffconnect);
		}
        });
		// calls virtual CuffInstr activity by passing R.id.OKCuffInstr as btn_gen
		// and @string/cuffinstr as infotext
		// to InfoOK.java
        Button btn_manual = (Button) findViewById(R.id.manualopt);
        btn_manual.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.manualopt);
		}
        });
		// calls virtual ManualInfo activity by passing R.id.BPInfoRead as btn_gen
		// and @string/bpHowTo as infotext
		// to InfoOK.java
    }
}