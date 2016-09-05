package com.example.smsdemo;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText editNo,editMsg;
	Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNo=(EditText) findViewById(R.id.editText1);
        editMsg=(EditText) findViewById(R.id.editText2);
        btnSend=(Button) findViewById(R.id.button1);
        btnSend.setOnClickListener(this);
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
 		SmsManager sms=SmsManager.getDefault();
		String mobNum=editNo.getText().toString();
		String msg=editMsg.getText().toString();
		sms.sendTextMessage(mobNum,null, msg,null,null);
		Toast.makeText(this,"message sent...",Toast.LENGTH_LONG).show();
		
	}
    
}
