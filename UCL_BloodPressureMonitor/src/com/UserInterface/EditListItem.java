package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditListItem extends Activity {
	private generalCtrl GCtrl;
	private static final String TAG = "EditListItem";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	makeLogEntry("Oncreate");
        GCtrl= new generalCtrl(this);
        setContentView(R.layout.editlistitem);
        final EditText txt_lineitem = (EditText) findViewById(R.id.ListItem);
       // final String item="h";
		makeLogEntry("before bundle");
        Bundle extras=getIntent().getExtras();
		makeLogEntry("after bundle");
        final String item = extras.getString("item");
        txt_lineitem.setText(item);
        final int position=extras.getInt("position");
        Button btn_deleteitem = (Button) findViewById(R.id.deleteitem);
        makeLogEntry("OKnamed");
        btn_deleteitem.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.deleteitem);
    		}
    	});
        makeLogEntry("OKbtnlogic");
        Button btn_saveitem = (Button) findViewById(R.id.savenewitem);
        makeLogEntry("savenamed");
        btn_saveitem.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			GCtrl.Buttonhandler(R.id.savenewitem);
      			Intent i= new Intent("com.example.newtest.DATALIST");
    			Bundle extras = new Bundle();
    			extras.putString("item",  txt_lineitem.getText().toString());
    			extras.putInt("position", position);
    			i.putExtras(extras);
    			startActivity(i);
    		}
    	});
        makeLogEntry("savedbtnlogic");
    }
    private void makeLogEntry(String s){
  		Log.d(TAG,s);
  	    }
    
    
}