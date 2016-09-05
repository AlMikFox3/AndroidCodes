package text.speech;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener, OnInitListener {
	EditText edit;
	Button btn;
	TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=(EditText) findViewById(R.id.editText1);
        btn= (Button) findViewById(R.id.button1);
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
		tts=new TextToSpeech(this, this);
		
	}


	@Override
	public void onInit(int arg0) {
		// TODO Auto-generated method stub
		String str= edit.getText().toString();
		tts.setLanguage(Locale.CANADA);
		tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);
		
	}
    
}
