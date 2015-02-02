package com.UserInterface;



import java.io.File;
import java.io.InputStream;

import com.Common.R;
import com.Controller.AppState;
import com.Controller.generalCtrl;
import com.DataInterface.FileOp.FileOperator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Report extends Activity {
	private generalCtrl GCtrl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        GCtrl=new generalCtrl(this);
        ImageView rptHere=(ImageView) findViewById(R.id.reportImg);
        
        AppState app = (AppState) getApplication();
        Log.d("test",app.getfolder());
        File report = new File(app.getfolder() + "reportpage1.jpg");
        FileOperator fop = new FileOperator(report);
              
        rptHere.setImageBitmap(fop.loadBitmap()); 
        		
        Button btn_EmailRpt = (Button) findViewById(R.id.emailrpt);
        btn_EmailRpt.setOnClickListener(new View.OnClickListener() {
      		@Override
      		public void onClick(View v) {
      			GCtrl.Buttonhandler(R.id.emailrpt);
      		}
              });
    }
   
   
    
}
