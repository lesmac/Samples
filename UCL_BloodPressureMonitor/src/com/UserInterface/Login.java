package com.UserInterface;



import com.Common.R;
import com.Controller.generalCtrl;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;



public class Login extends Activity {
	private generalCtrl GCtrl;
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
    //  setstate(STATE_LOGIN);
      GCtrl= new generalCtrl(this);
      setContentView(R.layout.login);
      EditText txt_username = (EditText) findViewById(R.id.loginun);
      String loginun=txt_username.toString();
      EditText txt_password = (EditText) findViewById(R.id.loginpwd);
      String loginpwd=txt_password.toString();
      //somehow need to verify the above un and pwd
      GCtrl.actOncreat();
      Button btn_login = (Button) findViewById(R.id.login);
      btn_login.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.login);
		}
	});

      //
		//sends username and password to db, 
		//using CreateSharedPreference() and after translating from textview 
		//eg act(findViewByID(R.id.loginusername)).getText().toString()
		//prepare intent 
		//prepare bundle
		//intent and bundle to choice.java
      Button btn_register = (Button) findViewById(R.id.loginregister);
     // btn_register.Buttonhandler(R.id.loginregister);
      btn_register.setOnClickListener(new View.OnClickListener() {
  		
		@Override
		public void onClick(View v) {
			GCtrl.Buttonhandler(R.id.loginregister);
		}
	});
  }
  
  
}
