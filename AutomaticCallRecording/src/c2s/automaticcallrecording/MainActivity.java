package c2s.automaticcallrecording;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {
	private ArrayList<String> recordingList;
	private ArrayAdapter<String> adapter;
	private ListView listCall;
	private EditText editTextSearch;
	private String AUDIO_PATH="";
	
	MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recordingList=new ArrayList<String>();
		initView();
		listAllFiles();
		
		listCall.setAdapter(adapter);
		listCall.setOnItemClickListener(this);
		listCall.setOnItemLongClickListener(this);
		
		listCall.setTextFilterEnabled(true);
		editTextSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				MainActivity.this.adapter.getFilter().filter(arg0);
			}
		});
	}

	//INIT the View
	private void initView() {
		// TODO Auto-generated method stub
		listCall=(ListView)findViewById(R.id.listViewAudioRecordingList);
		editTextSearch=(EditText)findViewById(R.id.editTextSearch);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.menu_help:
			startActivity(new Intent(this, HelpActivity2.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		AUDIO_PATH=parent.getItemAtPosition(position).toString();
		File root = new File(Environment.getExternalStorageDirectory(),
                "C2SCallRecording/"+AUDIO_PATH);
		Uri uri = Uri.parse(root.getAbsolutePath());
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		it.setDataAndType(uri,"video/3gpp");
		startActivity(it);
	}
	
	//list all files
	public void listAllFiles(){
		recordingList=new ArrayList<String>();
		File root = new File(Environment.getExternalStorageDirectory(),
                "C2SCallRecording");
		File[] fileList=root.listFiles();
		for(File file:fileList){
			recordingList.add(file.getName());
		}
		adapter=new ArrayAdapter<String>(this, R.layout.mylistview, recordingList);
		listCall.setAdapter(adapter);
		if(recordingList.size()==0){
			recordingList.add("No Call Records Found..");
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		AUDIO_PATH=parent.getItemAtPosition(position).toString();
		File root = new File(Environment.getExternalStorageDirectory(),
                "C2SCallRecording/"+AUDIO_PATH);
		if(AUDIO_PATH.equalsIgnoreCase("No Call Records Found..")){
		Toast.makeText(this, "No File content", Toast.LENGTH_LONG).show();	
		}else{
		Intent intent=new Intent();
		intent.setClass(this, HelpActiivty.class);
		intent.putExtra("path", root.getAbsolutePath());
		startActivity(intent);
		}
		return true;
	}

	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		listAllFiles();
	}
	
	
	
	

}
