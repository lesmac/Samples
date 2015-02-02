/* DatabaseAdaptor
 * need to discuss design of database
 */



package com.DataInterface.Databases;

import java.io.File;

import com.DataStructure.numericBP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	
	public static final String KEY_TIMESTAMP = "timestamp";
	public static final String KEY_SYSTOLIC = "systolic";
	public static final String KEY_DIASTOLIC = "diastolic";
	public static final String KEY_PULSE = "pulse";
	public static final String KEY_MAP = "map";
	private static final String TAG = "DBAdapter";
	private static final String DATABASE_TABLE = "BPstats";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE =
	"CREATE TABLE " + DATABASE_TABLE+ " (timestamp TEXT PRIMARY KEY," 
	+ "systolic DOUBLE , diastolic DOUBLE ,map DOUBLE,pulse DOUBLE)";
	
	private static String DATABASE_NAME;
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	public DBAdapter(Context ctx, String Database){
	    this.context = ctx;
	    DBAdapter.DATABASE_NAME = Database;
	    DBHelper = new DatabaseHelper(context);
	}
	private static class DatabaseHelper extends SQLiteOpenHelper{
	    DatabaseHelper(Context context){
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        Log.d(TAG, "DBHELPER CREATE");
	    }
	    @Override
	    public void onCreate(SQLiteDatabase db){
	        try {
	        	Log.d(TAG,"database create");
	            db.execSQL(DATABASE_CREATE);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
	    	/* to be decided by the apps requirment*/
	        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
	        + newVersion + ", which will destroy all old data");
	        db.execSQL("DROP TABLE IF EXISTS contacts");
	        onCreate(db);
	    }
	    
	}
	//destroy database
	public void destroyDB(){
		File dbfile = new File(db.getPath());
		SQLiteDatabase.deleteDatabase(dbfile);
	}
	
	
	//---opens the database---
	public DBAdapter open() throws SQLException
	{
	    db = DBHelper.getWritableDatabase();
	    return this;
	}
	//---closes the database---
	public void close()
	{
	    DBHelper.close();
	}
	//---insert a contact into the database---
	public long insertStats(numericBP bp)
	{
	    ContentValues initialValues = new ContentValues();
	    initialValues.put(KEY_SYSTOLIC, bp.Systolic);
	    initialValues.put(KEY_DIASTOLIC, bp.Diastolic);
	    initialValues.put(KEY_MAP, bp.MAP);
	    initialValues.put(KEY_PULSE, bp.pulse);
	    initialValues.put(KEY_TIMESTAMP, bp.time);
	    return db.insert(DATABASE_TABLE, null, initialValues);
	}
	//---deletes a particular stats---
	public boolean deleteStats(String timestamp){
	    return db.delete(DATABASE_TABLE, KEY_TIMESTAMP + "= '" + timestamp+"'", null) > 0;
	}
	//---retrieves all the stats---
	public Cursor getAllStats(){
	    return db.query(DATABASE_TABLE, new String[] {KEY_TIMESTAMP, KEY_SYSTOLIC,
	    KEY_DIASTOLIC,KEY_MAP}, null, null, null, KEY_TIMESTAMP, null);
	}
	//---retrieves a particular contact---
	public Cursor getStats(String timestamp) throws SQLException{
	    Cursor mCursor =
	    db.query(true, DATABASE_TABLE, new String[] {KEY_TIMESTAMP, KEY_SYSTOLIC,
	    	    KEY_DIASTOLIC,KEY_MAP,KEY_PULSE}, KEY_TIMESTAMP + "=" + "'"+ timestamp + "'", null,
	    null, null, KEY_TIMESTAMP, null);
	    if (mCursor != null) {
	        mCursor.moveToFirst();
	    }
	    return mCursor;
	}
	
	
	//---retrieves stats which is located in given period---
	public Cursor getPeriodStats(String timestart, String timestop){
		Cursor mCursor = 
		db.query(true, DATABASE_TABLE, new String[] {KEY_TIMESTAMP, KEY_SYSTOLIC,
	    	    KEY_DIASTOLIC,KEY_MAP,KEY_PULSE}, KEY_TIMESTAMP + ">= '" + timestart + "' AND " + KEY_TIMESTAMP + "<= '" + timestop +"'", 
	    	    null, null, null, null, KEY_TIMESTAMP, null);
		if(mCursor!= null){
			mCursor.moveToFirst();
		}
		
		return mCursor;
		
	}
	//---updates a contact---
	public boolean updateStats(numericBP bp){
		ContentValues args = new ContentValues();
		args.put(KEY_SYSTOLIC, bp.Systolic);
		args.put(KEY_DIASTOLIC, bp.Diastolic);
		args.put(KEY_MAP, bp.MAP);
		args.put(KEY_PULSE, bp.pulse);
		return db.update(DATABASE_TABLE, args, KEY_TIMESTAMP + "= '" + bp.time +"'", null) > 0;
	}
}
