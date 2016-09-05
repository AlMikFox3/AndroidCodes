package com.example.smslocation;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, LocationListener {
	Button btn;
	EditText edt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	btn=(Button) findViewById(R.id.button1);
	edt=(EditText) findViewById(R.id.editText1);
	btn.setOnClickListener(this);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		LocationManager lm=(LocationManager)getSystemService(LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,this);
			}

	@Override
	public void onLocationChanged(Location l) {
		// TODO Auto-generated method stub
		
		
			double lat=l.getLatitude();
			double lon=l.getLongitude();
			String loc=lat+","+lon;
			//txt.setText(loc);
			SmsManager sms=SmsManager.getDefault();
			String mobNum=edt.getText().toString();
			//String msg=editMsg.getText().toString();
			sms.sendTextMessage(mobNum,null,loc,null,null);
			Toast.makeText(this,"message sent...",Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
