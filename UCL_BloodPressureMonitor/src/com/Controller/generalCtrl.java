package com.Controller; 
  
import java.io.Serializable; 
import java.util.ArrayList;
import java.util.Calendar; 
import java.util.GregorianCalendar; 

import org.apache.commons.lang3.time.DateFormatUtils;
  
import com.Analyzer.AnalyzerControll;
import com.Analyzer.Modules.AnalyzerIO;
import com.Common.R; 
import com.DataInterface.ConfigAdapter.ConfigAdaptor; 
import com.DataStructure.*; 
import com.UserInterface.*; 
  
  
import android.app.Activity; 
import android.content.Context; 
import android.content.Intent; 
import android.os.Bundle; 
import android.util.Log; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.TimePicker; 
import android.widget.Toast;
import android.content.Intent; 
  
public class generalCtrl extends controller implements Serializable{ 
      
    private static final String TAG = "generalCtrl"; 
    private AppState app;
      
    private void makeLogEntry(String s){ 
        Log.d(TAG,s); 
        } 
      
    public generalCtrl(Activity act) { 
        super(act); 
        app = (AppState)act.getApplication();
    } 
       
    public void Buttonhandler (int id){ 
  
        switch (id){ 
          
        //A24HrABP.java buttons follow 
        case R.id.begincuffstudy24: 
            this.act.startActivity(new Intent("com.example.newtest.BTREAD")); 
        break; 
          
        //BTRead.java buttons follow 
        case R.id.OKBT: 
            this.act.startActivity(new Intent("com.DataInterface.bluetooth")); 
        break; 
              
          
        //Virtual CuffInstr.java buttons follow 
        case 2: 
            this.act.startActivity(new Intent("com.DataInterface.bluetooth")); 
        break; 
          
          
        //CuffOrManualChoice.java buttons follow 
        case R.id.cuffconnect: 
            //calls virtual CuffInstr  
            Intent i= new Intent("com.example.newtest.INFOOK"); 
            Bundle extras1 = new Bundle(); 
            String cuffinstr=this.act.getString(R.string.cuffinstr); 
            extras1.putString("infotext", cuffinstr); 
            int cuff=2; 
            extras1.putInt("buttoncode", cuff); 
            i.putExtras(extras1); 
            app.setstate(AppState.STATE_BLUETOOTH);
            this.act.startActivity(i); 
        break; 
        case R.id.manualopt: 
            // calls virtual ManualInfo activity by passing info eg @string/bpHowTo as infotext 
            // to InfoOK.java 
        	app.setstate(AppState.STATE_MANUAL_INPUT);
            Intent j= new Intent("com.example.newtest.INFOOK"); 
            Bundle extras2 = new Bundle(); 
            String maninstr=this.act.getString(R.string.bpHowTo); 
            extras2.putString("infotext", maninstr); 
            int manual=3; 
            extras2.putInt("buttoncode", manual); 
            j.putExtras(extras2); 
            this.act.startActivity(j); 
        break; 
          
        //Custom.java buttons follow 
        case R.id.begincuffstudycust: 
            //Calls Bluetooth Controller, passes it configs from Custom.java 
            EditText txt_NoReadingsCust = (EditText) this.act.findViewById(R.id.noreadingscust); 
            String numrdgs=txt_NoReadingsCust.getText().toString(); 
            int numrdgsint=Integer.parseInt(numrdgs); 
            EditText txt_IntervalCust = (EditText) this.act.findViewById(R.id.intervalcust); 
            String interval=txt_IntervalCust.getText().toString(); 
            int intervalint=Integer.parseInt(interval); 
            EditText txt_DurationCust = (EditText) this.act.findViewById(R.id.durationcust); 
            String durtn=txt_DurationCust.getText().toString(); 
            int durint=Integer.parseInt(durtn); 
            makeLogEntry("Number of Readings "+numrdgs+". Interval "+intervalint+". Duration "+ 
            durint+"."); 
            makeLogEntry("begincuffstudy"); 
            this.act.startActivity(new Intent("com.example.newtest.BTREAD")); 
        break; 
          
        //CustomOr24.java buttons follow 
        case R.id.a24HrABP: 
            this.act.startActivity(new Intent("com.example.newtest.A24HRABP")); 
        break; 
        case R.id.custom: 
            this.act.startActivity(new Intent("com.example.newtest.CUSTOM")); 
        break; 
          
        //DataList.java buttons follow 
        case R.id.allok: 
            //prepare intent 
            //prepare bundle with information on parameters of report 
            //for now editlistitem 
            makeLogEntry("beforetimeframe"); 
            AnalyzerIO aIO = new AnalyzerIO(act);
            //edited for arraylist change
            ArrayList<numericBP> bps = app.getBPArray();
            for(int a=0; a<bps.size();a++){
            	//below line changed from(bps[a]) to bps.get(a)
            	aIO.storeInDatabase(bps.get(a));
                makeLogEntry("afterdatalist array"); 
            }
            app.setstate(AppState.STATE_MANUAL_INPUT_DONE);
            makeLogEntry("manual state set"); 
            this.act.startActivity(new Intent("com.example.newtest.TIMEFRAME")); 
            makeLogEntry("afterintenttoeditlist"); 
        break; 
          
        /* 
        //Virtual Done.java buttons follow 
         //May take this out. 
        case R.id.nextbpmeas: 
            //Opens CuffOrManualChoice.java 
        break; 
              
        //EditListItem.java buttons follow 
        case R.id.deleteitem: 
            //deletes line item from the database 
        break; 
        case R.id.okitem: 
            //this means a line has been edited, so send new item to database to replace previous entry for it. 
        break; 
              
            */
        //EnterReading.java buttons follow 
        case R.id.OKRead: 
            //initialize array if it is the beginning 
            if(app.getBPArrayCtr()==0){ 
             //   int arraysize=100; 
                ArrayList<numericBP> bparraylist= new ArrayList<numericBP>();
               // numericBP[] bparray=new numericBP[arraysize]; 
/*                for(int bpa=0; bpa<bparray.length;bpa++){ 
                    bparray[bpa] = new numericBP(); 
                } */
              //  app.setBPArray(bparray); 
            } 
            //put systolic value in bparray 
            EditText enterSys = (EditText) this.act.findViewById(R.id.EnterSys); 
            String es=enterSys.getText().toString(); 
            makeLogEntry(es); 
            makeLogEntry("EnterReading edit text systolic is"+Double.parseDouble(es)); 
            numericBP newentryER=new numericBP(); 
            newentryER.Systolic=Double.parseDouble(es); 
              
            //put diastolic value in bparray 
            EditText enterDia = (EditText) this.act.findViewById(R.id.EnterDia); 
            String ed=enterDia.getText().toString(); 
            makeLogEntry(ed); 
            makeLogEntry("Enter Reading edit text diastolic is"+Double.parseDouble(ed)); 
            newentryER.Diastolic=Double.parseDouble(ed); 
              
            //get time 
            makeLogEntry("value for month before stored in numbp is" + EnterReading.aMonth); 
            newentryER.time="String"; 
            GregorianCalendar setcalendar=new GregorianCalendar(EnterReading.aYear, EnterReading.aMonth,  
                    EnterReading.aDay, EnterReading.aHour, EnterReading.aMinute); 
            String strUsCal=DateFormatUtils.format(setcalendar, "yyyy-MM-dd HH:mm:ss")+"00"; 
            newentryER.time=strUsCal; 
              
            //check in log 
            makeLogEntry("the calendar string is "+strUsCal); 
            makeLogEntry("the first diastolic in the array is "+app.getBPArrayAt(0).Diastolic); 
            makeLogEntry("the current diastolic in the array before reassigment is "+app.getBPArrayAt(app.getBPArrayCtr()).Diastolic); 
            makeLogEntry("bparrayctr is"+app.getBPArrayCtr()); 
            if (app.getBPArrayCtr()==0){
            	app.addBPArrayAt(newentryER);
            }
            app.setBPArrayAt(app.getBPArrayCtr(), newentryER); 
            makeLogEntry("the current timestamp in the array is "+app.getBPArrayAt(app.getBPArrayCtr()).time); 
            int newcount=app.getBPArrayCtr()+1; 
            app.setBPArrayCtr(newcount); 
            Intent vr= new Intent("com.example.newtest.ENTERREADING"); 
            //Bundle extrasER = new Bundle(); 
            //vr.putExtras(extrasER); 
            this.act.startActivity(vr); 
        break; 
        case R.id.clearread: 
            this.act.startActivity(new Intent("com.example.newtest.ENTERREADING")); 
        break; 
          
        //was part of viewread buttons, now part of enterreading buttons 
        case R.id.manreaddone:  
            //decided to copy/paste from enterreading, as they are the same now. 
/*          enterDia = (EditText) this.act.findViewById(R.id.EnterDia); 
            ed=enterDia.getText().toString(); 
            makeLogEntry(ed); 
            //makeLogEntry("edit text diastolic is"+Double.parseDouble(d2)); 
            numericBP newentry2=new numericBP(); 
            newentry2.Diastolic=Double.parseDouble(ed); 
            enterSys = (EditText) this.act.findViewById(R.id.EnterSys); 
            es=enterSys.getText().toString(); 
            makeLogEntry(es); 
            makeLogEntry("edit text systolic is"+Double.parseDouble(es)); 
            newentry2.Systolic=Double.parseDouble(es); 
            makeLogEntry("the first diastolic in the array is "+AppState.getBPArrayAt(0).Diastolic); 
            makeLogEntry("the current diastolic in the array is "+AppState.getBPArrayAt(AppState.getBPArrayCtr()).Diastolic); 
                    makeLogEntry("bparrayctr is"+AppState.getBPArrayCtr()); 
                    //check for changes, if so, update 
                    if ((AppState.getBPArrayAt(AppState.getBPArrayCtr()).Diastolic!=newentry2.Diastolic)|| 
                            (AppState.getBPArrayAt(AppState.getBPArrayCtr()).Systolic!=newentry2.Systolic)){ 
                        AppState.setBPArrayAt(AppState.getBPArrayCtr(), newentry2); 
                    } 
            for (int n=0;n<AppState.getBPArray().length;n++){ 
            makeLogEntry("bparray is"+AppState.getBPArrayAt(n).Diastolic); 
            }*/
              
            //initialize array if it is the beginning 
        	if(app.getBPArrayCtr()==0){ 
        	 ArrayList<numericBP> bparraylist= new ArrayList<numericBP>();
        	}
        	 //put systolic value in bparray 
            EditText enterSys2 = (EditText) this.act.findViewById(R.id.EnterSys); 
            String es2=enterSys2.getText().toString(); 
            makeLogEntry(es2); 
            makeLogEntry("EnterReading edit text systolic is"+Double.parseDouble(es2)); 
            numericBP newentryER2=new numericBP(); 
            newentryER2.Systolic=Double.parseDouble(es2); 
              
            //put diastolic value in bparray 
            EditText enterDia2 = (EditText) this.act.findViewById(R.id.EnterDia); 
            String ed2=enterDia2.getText().toString(); 
            makeLogEntry(ed2); 
            makeLogEntry("Enter Reading edit text diastolic is"+Double.parseDouble(ed2)); 
            newentryER2.Diastolic=Double.parseDouble(ed2); 
              
            //get time           
            makeLogEntry("value for month is" + EnterReading.aMonth); 
            newentryER2.time="String"; 
            GregorianCalendar setcalendar2=new GregorianCalendar(EnterReading.aYear, EnterReading.aMonth,  
                    EnterReading.aDay, EnterReading.aHour, EnterReading.aMinute); 
            String strUsCal2= DateFormatUtils.format(setcalendar2, "yyyy-MM-dd HH:mm:ss")+"00";
            newentryER2.time=strUsCal2; 
              
            //check in log 
            makeLogEntry("the current calendar string is "+strUsCal2); 
            makeLogEntry("the first diastolic in the array is "+app.getBPArrayAt(0).Diastolic); 
            makeLogEntry("bparrayctr is"+app.getBPArrayCtr()); 
            //set array entry 
            if (app.getBPArrayCtr()==0){
            	app.addBPArrayAt(newentryER2);
            }
            app.setBPArrayAt(app.getBPArrayCtr(), newentryER2); 
            makeLogEntry("the first timestamp in the array is "+app.getBPArrayAt(0).time); 
            makeLogEntry("the current timestamp in the array is "+app.getBPArrayAt(app.getBPArrayCtr()).time); 
            makeLogEntry("the current diastolic in the array is "+app.getBPArrayAt(app.getBPArrayCtr()).Diastolic);
            
                     
            this.act.startActivity(new Intent ("com.example.newtest.DATALIST")); 
        break; 
          
          
        //Virtual IfReg1Yes.java buttons follow 
        case 1: 
            //Opens Login.java, but for now reg1             
            this.act.startActivity(new Intent("com.example.newtest.LOGIN")); 
            makeLogEntry("open login activity"); 
        break; 
          
        //Login.java buttons follow 
        case R.id.loginregister: 
        	app.setstate(AppState.STATE_NEWUSER);

            Intent lr= new Intent("com.example.newtest.REGISTRATION1"); 
            Bundle extraslr = new Bundle(); 
            userInfo lrUInf=new userInfo(); 
            extraslr.putSerializable("usInfLR", lrUInf); 
            lr.putExtras(extraslr); 
            this.act.startActivity(lr); 
        break; 
        case R.id.login: 
            app.setstate(AppState.STATE_LOGIN);
            userInfo lusInf=new userInfo(); 
            EditText lUsername = (EditText) this.act.findViewById(R.id.loginun); 
            String currentuser = lUsername.getText().toString(); 
            
            if(currentuser.equals("")){
            	Toast.makeText(act.getBaseContext(),
            			"please enter username",
            			Toast.LENGTH_LONG).show();
            	break;
            }
            
            lusInf = ConfigAdaptor.loadSharedPref(currentuser, act);
            
            if(!lusInf.username.equals(currentuser)){
            	Toast.makeText(act.getBaseContext(),
            			currentuser + " does not exist",
            			Toast.LENGTH_LONG).show();
            }
            //how to match user with existing shared pref? Don't want to create new  
            //shared pref if existing user 
            EditText lpwd = (EditText) this.act.findViewById(R.id.loginpwd); 
            String currentpwd = lpwd.getText().toString(); 
            if(!lusInf.password.equals(currentpwd)){
            	Toast.makeText(act.getBaseContext(),
            			"Wrong Password, please enter again",
            			Toast.LENGTH_LONG).show();
            	break;
            }
            app.setCurrentuser(currentuser);       
            this.act.startActivity(new Intent("com.example.newtest.RPTORMEAS")); 
        break; 
          
        //Virtual  ManualInfo.java buttons follow 
        case 3: 
            this.act.startActivity(new Intent("com.example.newtest.ENTERREADING")); 
        break; 
          
        //Registration1.java buttons follow 
        case R.id.btn_registration1yes: 
            // calls virtual IfReg1Yes activity by passing info to InfoOK.java 
            Intent k= new Intent("com.example.newtest.INFOOK"); 
            Bundle extras = new Bundle(); 
            String warning=this.act.getString(R.string.arrhythmiaWarning); 
            extras.putString("infotext", warning); 
            int wrng=1; 
            extras.putInt("buttoncode", wrng); 
            k.putExtras(extras); 
            this.act.startActivity(k); 
        break; 
        case R.id.btn_registration1no: 
            Intent r1n= new Intent("com.example.newtest.REGISTRATION2"); 
            Bundle extrasr1n = Registration1.extrastopass; 
                //  extraslr; 
            /*userInfo lrUInf=usInf; 
            extraslr.putSerializable("usInfLR", lrUInf); 
            lr.putExtras(extraslr);*/
            r1n.putExtras(extrasr1n); 
            userInfo testing =(userInfo)r1n.getExtras().getSerializable("usInfLR"); 
            makeLogEntry("reg1no"+testing.username); 
            this.act.startActivity(new Intent(r1n)); 
        break; 
          
        //Registration2.java buttons follow 
        case R.id.hypertensionYes: 
            Registration2.userinfoobject.hypertension=true; 
        break; 
        case R.id.hypertensionNo: 
            Registration2.userinfoobject.hypertension=true; 
        break; 
        case R.id.reg2next: 
            userInfo reg2uinfo=Registration2.userinfoobject; 
            EditText txt_UserFirstName = (EditText) this.act.findViewById(R.id.firstName); 
            reg2uinfo.firstname=txt_UserFirstName.getText().toString(); 
            EditText txt_UserLastName = (EditText) this.act.findViewById(R.id.lastName); 
            reg2uinfo.lastname=txt_UserLastName.getText().toString(); 
            EditText txt_UserDOB = (EditText) this.act.findViewById(R.id.dob); 
            reg2uinfo.DOB=txt_UserDOB.getText().toString(); 
            EditText txt_GPName = (EditText) this.act.findViewById(R.id.gpName); 
            reg2uinfo.GPName=txt_GPName.getText().toString(); 
            makeLogEntry("fn"+reg2uinfo.firstname); 
            makeLogEntry("ln"+reg2uinfo.lastname); 
            makeLogEntry("dob"+reg2uinfo.DOB); 
            makeLogEntry("gp"+reg2uinfo.GPName); 
            Intent r2= new Intent("com.example.newtest.REGISTRATION3"); 
            Bundle extrasr2 = new Bundle(); 
            extrasr2.putSerializable("usInfR2", reg2uinfo); 
            r2.putExtras(extrasr2); 
            this.act.startActivity(r2); 
        break; 
          
        //Registration3.java buttons follow 
        case R.id.medicationYes: 
            Registration3.r3userinfoobject.medication=true; 
            //sets a boolean so that when they click next, 
            //there is a toast if they don't fill out the medication list 
        break; 
        case R.id.medicationNo: 
            Registration3.r3userinfoobject.medication=false; 
        break; 
        case R.id.reg3next: 
            userInfo reg3uinfo=Registration3.r3userinfoobject; 
            //should send a toast if all fields not filled out properly 
            EditText txt_Medications = (EditText) this.act.findViewById(R.id.specifykind); 
            reg3uinfo.medications=txt_Medications.getText().toString(); 
            EditText txt_NHSnum = (EditText) this.act.findViewById(R.id.nhsNo); 
            reg3uinfo.NHSnum=txt_NHSnum.getText().toString(); 
            EditText txt_username = (EditText) this.act.findViewById(R.id.username); 
            reg3uinfo.username=txt_username.getText().toString(); 
            EditText txt_password = (EditText) this.act.findViewById(R.id.password); 
            reg3uinfo.password=txt_password.getText().toString();  
            makeLogEntry(reg3uinfo.medications); 
            makeLogEntry(reg3uinfo.NHSnum); 
            makeLogEntry(reg3uinfo.username); 
            makeLogEntry(reg3uinfo.password); 
            if(reg3uinfo.medication){ 
                makeLogEntry("medication true"); 
            } else if (reg3uinfo.medication==false){ 
                makeLogEntry("medication false"); 
                //there is some problem here, the medication is always set to false. 
            }else{ 
                makeLogEntry("someproblem"); 
            } 
            ConfigAdaptor.CreateSharedPref(reg3uinfo, act); 
            app.setCurrentuser(reg3uinfo.username);      
            this.act.startActivity(new Intent("com.example.newtest.CUFFORMANUALCHOICE")); 
        break; 
          

   
        case R.id.emailrpt: 
            //calls user's email client, attaches report file to an email 
        	Intent emailIntent=new Intent(Intent.ACTION_SEND); 
    	    String [] tos= {"xxxx@gmail.com"};
    		//String[] ccs = { "gegeff@gmail.com" };  
    		//String[] bccs = {"fdafda@gmail.com"}; 
    		emailIntent.putExtra(Intent.EXTRA_EMAIL, tos);
    		//emailIntent.putExtra(Intent.EXTRA_CC, ccs); 
    		emailIntent.putExtra(Intent.EXTRA_TEXT, "body"); 
    		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject"); 
    		
    		
    		
    		//ArrayList<Uri> imageUris = new ArrayList<Uri>(); 
    		//imageUris.add(Uri.parse("file:///sdcard/Chrysanthemum.jpg")); 
    		//imageUris.add(Uri.parse("file:///sdcard/Desert.jpg"));       
    		//emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris); 
    		//emailIntent.setType("image/*"); 
    		emailIntent.setType("message/rfc882"); 
    		Intent.createChooser(emailIntent, "Choose Email Client"); 
    		act.startActivity(emailIntent); 
        break; 
 
          
        //RptOrMeas.java buttons follow 
        case R.id.makereport: 
            this.act.startActivity(new Intent ("com.example.newtest.TIMEFRAME")); 
        break; 
        case R.id.newmeas: 
            this.act.startActivity(new Intent ("com.example.newtest.CUFFORMANUALCHOICE")); 
        break; 
          
        //TimeFrame.java buttons follow 
        case R.id.btn_entertime: 
            //sends begintime and endtime values to db, after translating from edittext  
            //eventually db can create a sub-database and then that information is used to go to the DataList.java 
            //screen as in other report-making pathways. 
            GregorianCalendar begintimedate=new GregorianCalendar(TimeFrame.aYear, TimeFrame.aMonth,  
                    TimeFrame.aDay, TimeFrame.aHour, TimeFrame.aMinute); 
            String beginstring=DateFormatUtils.format(begintimedate, "yyyy-MM-dd HH:mm:ss")+"00";; 
            GregorianCalendar endtimedate=new GregorianCalendar(TimeFrame.bYear, TimeFrame.bMonth,  
                    TimeFrame.bDay, TimeFrame.bHour, TimeFrame.bMinute); 
            String endstring=DateFormatUtils.format(endtimedate, "yyyy-MM-dd HH:mm:ss")+"00";; 
            makeLogEntry("TimeFrame starts at "+beginstring+" and ends at "+endstring); 
            //prepare intent  
            //prepare bundle 
            //intent and bundle to DataList.java, for now, set to Report.java 
            //generate report here;
            app.setStartime(beginstring);
            app.setEndtime(endstring);
            
            app.setstate(AppState.STATE_GENERATE_REPORT);
            AnalyzerControll aC = new AnalyzerControll();
            aC.analyzerCMD(AnalyzerControll.CMD_REPORT_FULL, act, null);
            
            
            this.act.startActivity(new Intent ("com.example.newtest.REPORT")); 
        break; 
          
          
        //decided to comment out viewread as it seems a bit unnecessary. 
/*      //ViewRead.java buttons follow 
        case R.id.nextRead: 
            EditText showDia = (EditText) this.act.findViewById(R.id.ShowDia); 
            String d=showDia.getText().toString(); 
            makeLogEntry(d); 
            makeLogEntry("edit text diastolic is"+Double.parseDouble(d)); 
            numericBP newentry=new numericBP(); 
            newentry.Diastolic=Double.parseDouble(d); 
            EditText showSys = (EditText) this.act.findViewById(R.id.ShowSys); 
            String s=showSys.getText().toString(); 
            makeLogEntry(s); 
            makeLogEntry("edit text systolic is"+Double.parseDouble(s)); 
            newentry.Systolic=Double.parseDouble(s); 
            makeLogEntry("the first diastolic in the array is "+AppState.getBPArrayAt(0).Diastolic); 
            makeLogEntry("the current diastolic in the array is "+AppState.getBPArrayAt(AppState.getBPArrayCtr()).Diastolic); 
                    makeLogEntry("bparrayctr is"+AppState.getBPArrayCtr()); 
                    //check for changes, if so, update 
                    if ((AppState.getBPArrayAt(AppState.getBPArrayCtr()).Diastolic!=newentry.Diastolic)|| 
                            (AppState.getBPArrayAt(AppState.getBPArrayCtr()).Systolic!=newentry.Systolic)){ 
                        AppState.setBPArrayAt(AppState.getBPArrayCtr(), newentry); 
                    } 
                    int newcount=AppState.getBPArrayCtr()+1; 
                    AppState.setBPArrayCtr(newcount); 
            this.act.startActivity(new Intent ("com.example.newtest.ENTERREADING")); 
  
        break;*/
          
        default:  
            //error 
        break; 
        } 
    } 
} 