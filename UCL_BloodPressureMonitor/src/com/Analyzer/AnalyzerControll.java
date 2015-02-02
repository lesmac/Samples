package com.Analyzer;

import com.Analyzer.Modules.AnalyzerIO;
import com.Analyzer.Modules.ReportGenerator;
import com.Analyzer.Modules.StatsAnalyzer;
import com.DataStructure.numericBP;

import android.app.Activity;



public class AnalyzerControll {
	//generate simple version report
	public final static int CMD_REPORT_SIMPLE = 301;         
	//generate full version report
	public final static int CMD_REPORT_FULL = 302;           
	//Check the validation of the input data and store it
	public final static int CMD_VALIDATION_CHECK_STORED = 303;   
	public final static int CMD_VALIDATION_CHECK_ONLY = 304;
	
	//for cmd return information
	//CMD process ok
	public final static int RESULT_OK = 0;
	//general error
	public final static int ERROR = -1;
	//
	
	private String validCheckResult;
	private Activity ctx;
	
	public AnalyzerControll(){
	   ;
	}
	
	public int analyzerCMD(int cmd, Activity act,numericBP bp){
		
		switch(cmd){
		    case CMD_REPORT_SIMPLE:
		    	 return generateReport(act,false);
		    case CMD_REPORT_FULL:
		    	 return generateReport(act,true);
		    case CMD_VALIDATION_CHECK_STORED:
		    	 return validationCheck(act,true,bp);
		    case CMD_VALIDATION_CHECK_ONLY:
		    	 return validationCheck(act,true,bp);
		    default:
		    	 //error
		    	 return ERROR;
		}
		
				
	}
	public String getCheckResult(){
		return validCheckResult;
	}
	
	
	private int generateReport(Activity act, boolean opt){
		
		
		ReportGenerator rg = new ReportGenerator(act);
	
		
		if(opt){
			rg.drawFullReport();
		}
		else{
		    rg.drawSimpleReport();
		}
		
		return RESULT_OK;
	}
	
	private int validationCheck(Activity act, boolean opt,final numericBP bp){
		//check data validation
		if(bp == null){
			return ERROR;
		}
		else{
		    String result = StatsAnalyzer.CheckDataValidation(bp);
		    if(!result.equals("OK")){
		    	validCheckResult = result;
		    	return ERROR;
		    }
		    if(opt){
			    AnalyzerIO aIO = new AnalyzerIO(act);
			    return aIO.storeInDatabase(bp);
		    }
		    validCheckResult = "OK";
		    return RESULT_OK;
		}
	}
	

}
