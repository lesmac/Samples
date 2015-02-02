package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import com.Analyzer.Modules.ReportGenerator;
import com.Common.R;
import com.Controller.AppState;
import com.DataInterface.ConfigAdapter.ConfigAdaptor;
import com.DataInterface.Databases.DBAdapter;
import com.DataInterface.FileOp.FileOperator;
import com.DataStructure.numericBP;
import com.DataStructure.userInfo;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class test1 extends Activity {
	
	ImageView iv;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		 
		iv = (ImageView) findViewById(R.id.imageView1);
		 
		 Button testfop = (Button) findViewById(R.id.button1);
		 testfop.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	testfile();
	            }
	        });
		 
		 Button testdatabase = (Button) findViewById(R.id.button2);
		 testdatabase.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	testdb();
	            }
	        });
		 
		 Button testconfig = (Button) findViewById(R.id.button3);
		 testconfig.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
                     testcanvas();
	            }
	        });
		 
		 Button StatBT = (Button) findViewById(R.id.button4);
		 StatBT.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	testbt();
	            }
	        });
		
		
		 
	}
	
	
	
	private void testdb(){
		String TAG = "databasetest";
		
		DBAdapter db = new DBAdapter(this,"data");
		db.open();
		//db.create();
		Log.d(TAG,"database Created");
		
		//generate data to store
		
		numericBP[] bp = new numericBP[10];
		
		for(int i=0; i< bp.length;i++){
			bp[i] = new  numericBP();
			bp[i].Diastolic = (byte) i;
			bp[i].MAP = (byte) i;
			bp[i].Systolic = (byte) i;
			bp[i].time = "00-00-00 00:0"+(i+1)+":0000";
		}
		
		//long id =  db.insertStats(bp[0]);
		//insert data
		Log.d(TAG,"insert start");
		for(int i = 0; i<bp.length; i++){
			long id = db.insertStats(bp[i]);
			if(id == -1){
				Log.d(TAG, "insert failed Rowid:"+i);
			}
			else{
				Log.d(TAG,"insert succeed");
			}
		}
		
		
		//load data
		numericBP[] bpload = new numericBP[40];

		TextView display = (TextView) findViewById(R.id.textView1);
		display.setText("");
		Cursor c = db.getAllStats();		
		if (c.moveToFirst())
		{
			int i = 0;
		    do {
		        bpload[i] = loadstat(c);
		        display.append(bpload[i].time + "\n");
		        i++;
		    } while (c.moveToNext());
		}
		
		
		c = db.getStats("00-00-00 00:01:0000");
		if (c.moveToFirst())
		{
		    bpload[19] = loadstat(c);
		    display.setText(bpload[19].time + "\n");
		}
		
		
		c = db.getPeriodStats("00-00-00 00:03:0000", "00-00-00 00:07:0000");
		if (c.moveToFirst())
		{
			int i = 10;
		    do {
		        bpload[i] = loadstat(c);
		        display.append(bpload[i].time + "\n");
		        i++;
		    } while (c.moveToNext());
		}
		
		
		//update stats
		numericBP bpupdate = new numericBP();
		bpupdate.time = bp[4].time;
		c = db.getStats(bp[4].time);
		if (c.moveToFirst())
		{
		    bpload[18] = loadstat(c);
		    display.setText(bpload[18].Systolic + bpload[18].time+"\n");
		}
		db.updateStats(bpupdate);
		c = db.getStats(bp[4].time);
		if (c.moveToFirst())
		{
		    bpload[18] = loadstat(c);
		    display.append(bpload[18].Systolic + bpload[18].time+"\n");
		}
		
				
		//delete stats
		for(int i = bp.length; i<bp.length; i++){
			if(!db.deleteStats(bp[i].time)){
				Log.d("delete", "fail rowid"+i);
			}
		}
		
		db.close();
		db.destroyDB();
				
	}
	private numericBP loadstat(Cursor c){		
		numericBP bp = new numericBP();
		
		bp.Systolic = c.getInt(c.getColumnIndex(DBAdapter.KEY_SYSTOLIC));
		bp.Diastolic = c.getInt(c.getColumnIndex(DBAdapter.KEY_DIASTOLIC));
		bp.MAP = c.getInt(c.getColumnIndex(DBAdapter.KEY_MAP));
		bp.time = c.getString(c.getColumnIndex(DBAdapter.KEY_TIMESTAMP));
		
		return bp;
	}
	
	
	
	private void testmail(){
		Intent emailIntent=new Intent(Intent.ACTION_SEND); 
	    String [] tos= {"xxxx@gmail.com"};
		//String[] ccs = { "gegeff@gmail.com" };  
		//String[] bccs = {"fdafda@gmail.com"}; 
		emailIntent.putExtra(Intent.EXTRA_EMAIL, tos);
		//emailIntent.putExtra(Intent.EXTRA_CC, ccs); 
		emailIntent.putExtra(Intent.EXTRA_TEXT, "body"); 
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject"); 
		
		
		
		//ArrayList<Uri> imageUris = new ArrayList<Uri>(); 
		//imageUris.add(Uri.parse("file:///sdcard/Chrysanthemum.jpg")); 
		//imageUris.add(Uri.parse("file:///sdcard/Desert.jpg"));       
		//emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris); 
		//emailIntent.setType("image/*"); 
		emailIntent.setType("message/rfc882"); 
		Intent.createChooser(emailIntent, "Choose Email Client"); 
		startActivity(emailIntent); 
    }
	


    private void testfile(){
    	InputStream is = this.getResources().openRawResource(R.raw.p);
    	Bitmap bmp = BitmapFactory.decodeStream(is);
    			
    	File file = new File(getFilesDir(),"report.jpg");
    	FileOperator fop = new FileOperator(file);
    	
    	Log.d("TEST",file.getAbsolutePath());
    	Log.d("TEST","write start");

    	fop.writeBitmap(bmp,Bitmap.CompressFormat.JPEG);
    	
    	for(int i=0; i<10000;i++){
    		for(int j=0; j<10000;j++){
    			int c = 0;
    		}
    	}
    	
    	Log.d("TEST","read start");
    	
    	Bitmap loadimage = fop.loadBitmap();
    	
    	iv.setImageBitmap(loadimage);
    	
    }


    private void testbt(){
    	Intent bt = new Intent("com.userInterface.bluetooth");
    	startActivity(bt);
    }
    
	private void testcanvas(){
		
	     
		
		ReportGenerator r = new ReportGenerator(test1.this);
		
		//Bitmap bmp = r.drawFullReportpage1();
		
		//iv.setImageBitmap(bmp);
		r.drawFullReport();
		AppState app = (AppState) getApplication();
		
    	File file = new File(app.getfolder(),"reportpage1.jpg");
    	FileOperator fop = new FileOperator(file);
    	Bitmap loadimage = fop.loadBitmap();
		Log.d("TEST",file.getAbsolutePath());
		
		iv.setImageBitmap(loadimage);
	}
		
	
}
