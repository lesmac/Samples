/*package com.DataInterface.BlueToothHDP;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.Common.R;
import com.DataInterface.BlueToothHDP.*;




public class BluetoothHDPActivity extends Activity {

	private BluetoothController BTctrl;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BTctrl = new BluetoothController(this);
		setContentView(R.layout.bluetoothhdp);
		BTctrl.actOncreat();
		 Button registerAppButton = (Button) findViewById(R.id.button_start_bt);
	        registerAppButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	BTctrl.Buttonhandler(1);
	            }
	        });

	        // Initiates application unregistration through {@link BluetoothHDPService}.
	        Button unregisterAppButton = (Button) findViewById(R.id.button_bt_done);
	        unregisterAppButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	BTctrl.Buttonhandler(2);;
	            }
	        });

	        // Initiates channel creation through {@link BluetoothHDPService}.  Some devices will
	        // initiate the channel connection, in which case, it is not necessary to do this in the
	        // application.  When pressed, the user is asked to select from one of the bonded devices
	        // to connect to.
	        Button connectButton = (Button) findViewById(R.id.button_connect_channel);
	        connectButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	BTctrl.Buttonhandler(3);
	            }
	        });

	        // Initiates channel disconnect through {@link BluetoothHDPService}.
	        Button disconnectButton = (Button) findViewById(R.id.button_disconnect_channel);
	        disconnectButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	BTctrl.Buttonhandler(4);
	            }
	        });
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		BTctrl.actOndestroy();
	}
	@Override
	protected void onStart() {
		super.onStart();
		BTctrl.actOnstart();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		BTctrl.actOnActivityResult(requestCode, resultCode, data);
	}
	
	
	public void connectChannel(){
		BTctrl.connectChannel();
	}
	public void setDevice(int which){
		BTctrl.setDevice(which);
	}
}
*/