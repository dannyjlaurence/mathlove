package com.neonbats.mathlove;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;

public class LessonMap extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.lessonmap);
		 
		 TableLayout ll = (TableLayout)findViewById(R.id.grid_view);
		 ll.setBackgroundResource(R.drawable.path);
		 
	}
	
}
