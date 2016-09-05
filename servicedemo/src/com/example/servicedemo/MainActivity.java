package com.example.servicedemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	Button btnstart,btnstop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnstart=(Button) findViewById(R.id.button1);
		btnstop=(Button) findViewById(R.id.button2);
		btnstart.setOnClickListener(this);
		btnstop.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		
		case R.id.button1:
			startService();
			break;
		case R.id.button2:	
			stopService();
			break;
	}
	}

	private void stopService() {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.setClass(this, MyService.class);
		stopService(intent);
	}

	private void startService() {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.setClass(this, MyService.class);
		startService(intent);
	}
}
