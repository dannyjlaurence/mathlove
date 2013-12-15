package com.neonbats.mathlove;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class LessonMap extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.lessonmap);
	}
	
	
	// Implement the OnClickListener callback
    public void onClick(View v) {
      // do something when the button is clicked
    	Log.i("test","totally worked");
    	Dialog dialog;
    	
    	switch(v.getId()){
    	case R.id.button1:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 1");
        	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Fractals 101");
        	((TextView)dialog.findViewById(R.id.lessonText)).setText("This lesson will introduce you to fractals, including how they are defined, what they look like, and a brief quiz.");
        	
        	
        	dialog.show();
    		
    		break;
    	case R.id.button2:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 2");
        	dialog.show();
    		
    		break;
    	case R.id.button3:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 3");
        	dialog.show();
    		
    		break;
    	case R.id.button4:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 4");
        	dialog.show();
    		
    		break;
    	case R.id.button5:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 5");
        	dialog.show();
    		
    		break;
    	case R.id.button6:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 6");
        	dialog.show();
    		
    		break;
    	case R.id.button7:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 7");
        	dialog.show();
    		
    		break;
    	case R.id.button8:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 8");
        	dialog.show();
    		
    		break;
    	case R.id.button9:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 9");
        	dialog.show();
    		
    		break;
    	case R.id.button10:
    		
    		dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 10");
        	dialog.show();
    		
    		break;
    		
    		
    	
    	}
    	    	
    }
	
}
