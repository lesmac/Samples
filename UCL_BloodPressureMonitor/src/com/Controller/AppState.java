package com.Controller;

import java.util.ArrayList;

import com.DataStructure.numericBP;

import android.app.Application;

public class AppState extends Application{
	
	public static final int STATE_LOGIN = 0x00000001;
	public static final int STATE_NEWUSER = 0x00000002;
	public static final int STATE_MANUAL_INPUT = 0x00000004;
	public static final int STATE_MANUAL_INPUT_DONE = 0x00000014;
	public static final int STATE_BLUETOOTH = 0x00000008;
	public static final int STATE_BLUETOOTH_DONE = 0x00000012;
	public static final int STATE_GENERATE_REPORT = 0x00000010;
	public static final int STATE_CONFIGURATION = 0x00000018;

	private static int bparrayctr;
	private static ArrayList<numericBP> bparraylist;
	
	private String currentuser;
	private String currentfolder;
	private int state;
	private String starttime;
	private String endtime;
	
	
	public void setCurrentuser(String username){
		if((state == STATE_LOGIN)||(state == STATE_NEWUSER)){
			currentuser = username;
		}
	}
	
	public String getCurrentuser(){
		return currentuser;
	}
	
	public  void setBPArray(ArrayList<numericBP> newarraylist){
		bparraylist=newarraylist;
	}
	
	public  ArrayList<numericBP> getBPArray(){
		return bparraylist;
	}
	
	public  numericBP getBPArrayAt(int bpindex){
		return bparraylist.get(bpindex);
	}
	
	public  void setBPArrayAt(int bpindex, numericBP newentry){
		//replaces the item at the index
		bparraylist.set(bpindex, newentry);
		}
	
	public  void addBPArrayAt(numericBP newentry){
		//puts a new item at the end
		bparraylist.add(newentry);
		}
	
	public  void setBPArrayCtr(int bpctr){
		bparrayctr=bpctr;
	}
	
	public  int getBPArrayCtr(){
		return bparrayctr;
	}
	
	public synchronized void setstate(int newstate){	
		state = newstate;
	}
	
	public int getstate(){
		return state;
	}
	
	public void setfolder(String filePath){
		currentfolder = filePath;
	}
	public String getfolder(){
		return currentfolder;
	}
	public String getReportStarttime(){
		if(starttime != null){
			return starttime;
		}
		else{
			return "0000-00-00 00:00:0000";
		}
	}
	public String getReportEndtime(){
		if(endtime != null){
			return endtime;
		}
		else{
			return "0000-00-00 00:00:0000";
		}
	}
	public void setStartime(String s){
		if(state == STATE_CONFIGURATION){
			starttime = s;
		}
	}
	public void setEndtime(String s){
		if(state == STATE_CONFIGURATION){
			endtime = s;
		}
	}
}
