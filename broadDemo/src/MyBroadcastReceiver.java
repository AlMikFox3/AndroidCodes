import com.example.broaddemo.NetworkStatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class MyBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		int status=NetworkStatus.getNetworkInfo(context);
		switch(status)
		{
		case 1:Toast.makeText(context, "MOB INTERNET", Toast.LENGTH_LONG).show();
			break;
		case 2:Toast.makeText(context, "WI-FI", Toast.LENGTH_LONG).show();
			break;
		default:Toast.makeText(context, "NO CONNECTIVITY", Toast.LENGTH_LONG).show();	
		
		
		}

	}

}
