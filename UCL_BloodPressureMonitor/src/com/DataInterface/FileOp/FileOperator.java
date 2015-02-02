/*FileOperator
 * Created By           Kyle
 * Date:                11/11/2013
 * Description:         This class is used for reading or writing a png file for the app. 
 */
package com.DataInterface.FileOp;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FileOperator {
    private File file;
    
    public static final int QUALITY_100 = 100;
    
    
    public FileOperator(File file){
    	this.file = file;
    }
    
    public synchronized void writeBitmap(Bitmap image, Bitmap.CompressFormat format){
    	File pic = file;
    	try{
    	    FileOutputStream fos = new FileOutputStream(pic);
    	    image.compress(format, QUALITY_100, fos);
    	    fos.flush();
    	    fos.close();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public synchronized Bitmap loadBitmap(){
    	try{
    	    FileInputStream fis = new FileInputStream(file); 
    	    Bitmap bmp = BitmapFactory.decodeStream(fis);
    	    fis.close();
    	    return bmp;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    
    public synchronized boolean deletefile(){
    	return file.delete();
    }
    
    
}
