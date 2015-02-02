package com.Controller;

import java.io.Serializable;

import android.app.Activity;

public abstract class controller implements Serializable{
	public Activity act;
	//changed above from private
	public controller(Activity act){
		this.act = act;   // record current activity
	}
	public void setCurrentActivity(Activity act){
		this.act = act;
	}
	
	// provide these method matching activity lifecylce
	public boolean actOncreat(){
		return true;
	}
	public void actOndestroy(){
		;
	}
	public void actOnstart(){
		;
	}
	public void actOnstop(){
		;
	}
	public void actOnResume(){
		;
	}
	public void actOnRestart(){
		;
	}
	
	
	
	//not for dealing with exception
	public static void ErrorHandler(){
		
	}

	public abstract void Buttonhandler(int id);
}
