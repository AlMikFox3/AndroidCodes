package c2s.automaticcallrecording;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
	
	public static final String DATABASE="recipies.db";
	public static final int version=1;
	
	public MyDataBase(Context context) {
		super(context, DATABASE, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase sd) {
		// TODO Auto-generated method stub
		sd.execSQL(create_callRecording);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
        arg0.execSQL("drop table if exists "+table_callRecording);
	}

	//Ninja Server Tasks
	//table
	public static final String table_callRecording="call_recording";
	//column
	public static final String callRecording_call="current_call";
	public static final String create_callRecording="create table "+table_callRecording+" ("+
	""+callRecording_call+" text)";

}
