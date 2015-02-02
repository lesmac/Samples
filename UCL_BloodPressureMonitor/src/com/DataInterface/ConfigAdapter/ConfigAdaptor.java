package com.DataInterface.ConfigAdapter;

import com.DataStructure.userInfo;

import android.app.Activity;
import android.content.SharedPreferences;

public class ConfigAdaptor {
	

	
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_DATABASE = "database";
	private static final String KEY_FIRSTNAME= "firstname";
	private static final String KEY_DOB = "dob";
	private static final String KEY_LASTNAME = "lastname";
	private static final String KEY_GPNAME = "GPname";
	private static final String KEY_HYPERTENSION = "hypertension";
	private static final String KEY_MEDICATION = "medication";
	private static final String KEY_MEDICATIONS = "medications";
	private static final String KEY_NHSNUM = "NHSno";
	private static final String KEY_CHOSENUN = "chosenun";
	private static final String KEY_CHOSENPWD = "chosenpwd";
	

	
	
	//creat SharedPreferences
	public static void CreateSharedPref(userInfo user, Activity act){
		//get SharePreference Object
		SharedPreferences prefs = act.getSharedPreferences(user.username, act.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		//save the values
		editor.putString(KEY_USERNAME, user.username);
		editor.putString(KEY_PASSWORD, user.password);
		editor.putString(KEY_DATABASE, user.Database);
		editor.putString(KEY_FIRSTNAME, user.firstname);
		editor.putString(KEY_LASTNAME, user.lastname);
		editor.putString(KEY_DOB, user.DOB);
		editor.putString(KEY_GPNAME, user.GPName);
		editor.putBoolean(KEY_HYPERTENSION, user.hypertension);
		editor.putBoolean(KEY_MEDICATION, user.medication);
		editor.putString(KEY_MEDICATIONS, user.medications);
		editor.putString(KEY_NHSNUM, user.NHSnum);
		editor.commit();
				
	}
	
	public static userInfo loadSharedPref(String username, Activity act){
		userInfo user = new userInfo();
		SharedPreferences Pref = act.getSharedPreferences(username, act.MODE_PRIVATE);
		
		//load values
		user.username = Pref.getString(KEY_USERNAME, "");
		user.password = Pref.getString(KEY_PASSWORD, "");
		user.Database = Pref.getString(KEY_DATABASE, "");
		user.firstname = Pref.getString(KEY_FIRSTNAME, "");
		user.lastname = Pref.getString(KEY_LASTNAME, "");
		user.DOB = Pref.getString(KEY_DOB, "");
		user.GPName = Pref.getString(KEY_GPNAME, "");
		user.hypertension = Pref.getBoolean(KEY_HYPERTENSION, false);
		user.medication = Pref.getBoolean(KEY_MEDICATION, false);
		user.medications = Pref.getString(KEY_MEDICATIONS, "");
		user.NHSnum = Pref.getString(KEY_NHSNUM, "");

		return user;
	}
	
	
}
