package c2s.automaticcallrecording;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HelpActiivty extends Activity implements OnClickListener {
private Button btnDelete,btnShare;
private String path;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		Bundle bundle=getIntent().getExtras();
		path=bundle.getString("path");
		btnDelete=(Button)findViewById(R.id.buttonDeleteRecord);
		btnShare=(Button)findViewById(R.id.buttonShare);
		btnDelete.setOnClickListener(this);
		btnShare.setOnClickListener(this);
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()){
		case R.id.buttonDeleteRecord:
			File file=new File(path);
			file.delete();
			Toast.makeText(this, "Delelted!!", Toast.LENGTH_LONG).show();
			finish();
			break;
		case R.id.buttonShare:
			Intent sendIntent = new Intent(Intent.ACTION_SEND);
			sendIntent.setType("video/3gp");
			sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));

			sendIntent.putExtra(Intent.EXTRA_TEXT, "Enjoy the Recording");
			startActivity(Intent.createChooser(sendIntent, "Email:"));
			finish();
			break;
		}
	}
	
	

}
