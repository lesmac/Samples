/*
 * This class is used for input and output data for Analyzer;
 */

package com.Analyzer.Modules;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.Analyzer.AnalyzerControll;
import com.Controller.AppState;
import com.DataInterface.Databases.DBAdapter;
import com.DataStructure.numericBP;

public class AnalyzerIO {
	
	private Activity act;
	private String user;
	
	public AnalyzerIO(Activity act){
		this.act = act;
		AppState app = (AppState) act.getApplication();
		user = app.getCurrentuser();
	}
	
	
	public int storeInDatabase(numericBP bp){
		DBAdapter db = new DBAdapter(act,user);
		db.open();
		long id = db.insertStats(bp);
		db.close();
		if(id == -1){
			return AnalyzerControll.ERROR;
		}
		else{
			return AnalyzerControll.RESULT_OK;
		}
		
	}
	
		
	public numericBP loadFromDatabase(String timestamp){
		DBAdapter db = new DBAdapter(act,user);
		numericBP bp = new numericBP();
		
		try{
		    Cursor c = db.getStats(timestamp);		
		    if (c.moveToFirst()){
			    bp = loadstat(c);	
			    return bp;
		    }
		    else{
		    	//cursor is empty
			    return null;
		    }		
		}catch(SQLException e){
			e.printStackTrace(); 
			return null;
		}finally{
			db.close();
		}
		
	}
	
	public numericBP[] loadFromDatabase(String timestart, String timestop){
		DBAdapter db = new DBAdapter(act,user);
		try{
		    //Cursor c = db.getPeriodStats(timestart,timestop);	
		    Cursor c = db.getAllStats();
		    
		    if (c.moveToFirst()){
		    	numericBP[] bp = new numericBP[c.getCount()];
		    	int i = 0;
			    do {
			        bp[i] = loadstat(c);			     
			        i++;
			    } while (c.moveToNext());	
			    return bp;
		    }
		    else{
		    	//cursor is empty
			    return null;
		    }		
		}catch(SQLException e){
			e.printStackTrace(); 
			return null;
		}finally{
			db.close();
		}
	}
	
	
	private numericBP loadstat(Cursor c){		
		numericBP bp = new numericBP();
		
		bp.Systolic = c.getInt(c.getColumnIndex(DBAdapter.KEY_SYSTOLIC));
		bp.Diastolic = c.getInt(c.getColumnIndex(DBAdapter.KEY_DIASTOLIC));
		bp.MAP = c.getInt(c.getColumnIndex(DBAdapter.KEY_MAP));
		bp.time = c.getString(c.getColumnIndex(DBAdapter.KEY_TIMESTAMP));
		
		return bp;
	}


}
