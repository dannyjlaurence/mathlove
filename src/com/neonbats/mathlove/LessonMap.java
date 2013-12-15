package com.neonbats.mathlove;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class LessonMap extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.lessonmap);
		 
		 LinearLayout ll = new LinearLayout(this);
		 ll.setBackgroundResource(R.drawable.path);
		 
	}
	
}
