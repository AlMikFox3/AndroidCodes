package com.example.broaddemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {
	public static int getNetworkInfo(Context context)
	{
		ConnectivityManager conn=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);//Class.connect....service
		NetworkInfo net=conn.getActiveNetworkInfo();
		if(net!=null)
		{
			switch(net.getType())
			{
			case ConnectivityManager.TYPE_MOBILE:
				return 1;
			case ConnectivityManager.TYPE_WIFI:
				return 2;
			
			
			}
			
		}
		else
		{
			return 0;
		}
		return 0;
	}
}
