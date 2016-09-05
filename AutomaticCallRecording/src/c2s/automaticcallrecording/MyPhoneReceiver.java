package c2s.automaticcallrecording;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;


public class MyPhoneReceiver extends BroadcastReceiver {

	//MediaRecorder recorder;
    //TelephonyManager telManager;
    //boolean recordStarted;
    private Context ctx;
    static boolean status = false;
    //Time today;
    String phoneNumber;
    //byte[] incrept;
    //byte[] decrpt;
    //String selected_song_name;
    
    public static MediaRecorder recorder;
	File audioFile=null;
	static final String TAG="MediaRecording";
	
	static boolean isIncomingNumber=false;
	static String inComingNumber;
    
    @Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
    	ctx = context;
        String action = intent.getAction();
        //Log.i("action>>>>>>>>>>>>>>>>>", "" + action);
        Bundle extras=intent.getExtras();
        String state=extras.getString(TelephonyManager.EXTRA_STATE);
        //recorder=new MediaRecorder();
        //Toast.makeText(context, state, Toast.LENGTH_LONG).show();
        long currentDateTime = System.currentTimeMillis();
		Date currentDate = new Date(currentDateTime);
	    DateFormat df = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
	    String dateString=df.format(currentDate);
        if(extras!=null){
        	//Toast.makeText(ctx, state, Toast.LENGTH_LONG).show();
            phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            
        	if (state.equalsIgnoreCase("RINGING")) {
        		isIncomingNumber=true;
        		phoneNumber = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                inComingNumber=phoneNumber;
        		//Toast.makeText(context, state+" ----> "+phoneNumber, Toast.LENGTH_LONG).show();
        	} 
        	if (state.equalsIgnoreCase("IDLE")) {
                //Log.i("number >>>>>>>>>>>>>>", "" + this.getResultData());
                isIncomingNumber=false;
                inComingNumber="MyNumber";
                
               // Toast.makeText(context, "OFFHOOK:"+inComingNumber, Toast.LENGTH_LONG).show();
                if(status==true){
                	//Toast.makeText(context, state+" current status:"+status, Toast.LENGTH_LONG).show();
                	status=false;
                	recorder.stop();
                	recorder.release();
                }
        	}
        	if (state.equalsIgnoreCase("OFFHOOK")) {
        		//Toast.makeText(context, "OFFHOOK:"+status, Toast.LENGTH_LONG).show();
        		status=true;
        		if(isIncomingNumber){
        			isIncomingNumber=false;
        		}
        		else{
        			inComingNumber="MyNumber";
        		}
        			
        		//Toast.makeText(context, "OFFHOOK:"+status, Toast.LENGTH_LONG).show();
        		if(status==true){
        		File dir=Environment.getExternalStorageDirectory();
        		long available_space=dir.getFreeSpace()/1048576;
        		//finding the SD Card size....
        		if(available_space<=20){
        			status=false;
        			return;
        		}
        		MyDataBase md=new MyDataBase(ctx);
        		SQLiteDatabase sd=md.getWritableDatabase();
        		ContentValues cv=new ContentValues();
        		
        		File root = new File(Environment.getExternalStorageDirectory(),
                        "C2SCallRecording");
        		if (!root.exists()) {
                 root.mkdirs();
        		}
        	    
        		try{
        			
        			audioFile=File.createTempFile("PhoneCall_NO_"+inComingNumber.replace("+", "")+"_"+dateString, ".3gp",root);
        			cv.put(md.callRecording_call, audioFile.getPath());
        			sd.insert(md.table_callRecording, md.callRecording_call, cv);
        			cv.clear();
        			md.close();
        			sd.close();
        			//Log.e("NUMBER SAVE",audioFile.getPath());
        		}catch (Exception e) {
        			// TODO: handle exception
        			//Log.e(TAG, "external storage acesse error");
        			//Toast.makeText(ctx, "ERROR in CREATING FILE", Toast.LENGTH_LONG).show();
                	
        			return;
        		}
        		
        		//Creating the MediaRecorder and specifying audio source,output format, encoder & output format
        		recorder=new MediaRecorder();
        		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        		recorder.setAudioSamplingRate(16000);
        		recorder.setOutputFile(audioFile.getAbsolutePath());
        		try {
					recorder.prepare();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//Toast.makeText(context, "ERROR PREPARE", Toast.LENGTH_LONG).show();
				}
        		recorder.start();
        		}

        	}
        }
       
    }
    
    


}
