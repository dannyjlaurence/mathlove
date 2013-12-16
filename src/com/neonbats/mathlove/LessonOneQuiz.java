package com.neonbats.mathlove;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class LessonOneQuiz extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lessononequiz);
	}
	
	// Event handler for radio buttons 
    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
        case R.id.radio0:
            if (checked)
                Toast.makeText(this, "Incorrect, this is a Mandlebrot set! Try again", Toast.LENGTH_LONG).show();
            break;
        case R.id.radio1:
            if (checked)
                Toast.makeText(this, "Coastlines are actually fractals. The less distance you use to measure them, the longer they get!", Toast.LENGTH_LONG).show();
            break;
        case R.id.radio2:
            if (checked)
                Toast.makeText(this, "Correct! This is a Venn Diagram.", Toast.LENGTH_LONG).show();
            	SharedPreferences sharedPref = getSharedPreferences("com.neonbats.mathlove.levels",0);
            	SharedPreferences.Editor editor = sharedPref.edit();
            	
            	 Log.i("test","Writing this shit");
    			 
            	
            	editor.putInt("level1", 1);
            	editor.putInt("levelunlock1", 2);
            	editor.putInt("levelunlock3", 1);
            	editor.putInt("levelunlock2", 1);
            	
            	editor.commit();
            	
            	Log.i("test","Wrote that shit");
            	
            	finish();
            break;
        }
    }

}
