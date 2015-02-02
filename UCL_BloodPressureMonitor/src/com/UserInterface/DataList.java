/*package com.UserInterface;



import java.util.ArrayList;

import com.Common.R;
import com.Controller.AppState;
import com.Controller.generalCtrl;
import com.DataStructure.numericBP;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;

public class DataList extends Activity {
	private static final String TAG = "DataList";
	private generalCtrl GCtrl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GCtrl= new generalCtrl(this);
    setContentView(R.layout.datalistview);

    final ListView listview = (ListView) findViewById(R.id.listview);
    AppState app = (AppState)getApplication();    
    ArrayList<numericBP> bps = app.getBPArray();
    String[] values = new String[bps.length] ;
    for(int i = 0; i<values.length;i++){
    	values[i] = String.format((i+1)+" %.0f/%.0f"+bps(i).time, bps[i].Systolic,bps[i].Diastolic);
    }
    
    
    if (getIntent().hasExtra("position")) {
    	//the above would be the case if called by editlistitem, sending a correction
        Bundle extras=getIntent().getExtras();
        final int position=extras.getInt("position");
        values[position]=extras.getString("item");
    }
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, values);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClickListener() {
        	   @Override
        	   public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
        	      Object listItem = listview.getItemAtPosition(position);
        	      String item=listItem.toString();
        	      makeLogEntry(item);
      			Intent i= new Intent("com.example.newtest.EDITLISTITEM");
    			Bundle extras = new Bundle();
    			extras.putString("item", item);
    			extras.putInt("position", position);
    			i.putExtras(extras);
    			startActivity(i);
        	   } 
        	});
        Button btn_AllOK = (Button) findViewById(R.id.allok);
		makeLogEntry(values[0].toString());
        btn_AllOK.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			makeLogEntry("before all ok");
    			GCtrl.Buttonhandler(R.id.allok);
    		}
            });
    }
  private void makeLogEntry(String s){
		Log.d(TAG,s);
	    }
}

public class DataList extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist);
        Button btn_AllOK = (Button) findViewById(R.id.allok);
        btn_AllOK.Buttonhandler(R.id.allok);
    }
}
*/