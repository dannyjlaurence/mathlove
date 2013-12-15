package com.neonbats.mathlove;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class LessonOne extends Activity {
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lessonone);
	}
	
	public void onClick(View v){
		 Intent myIntent = new Intent(LessonOne.this, LessonOneQuiz.class);
         startActivityForResult(myIntent, 0);	
         finish();
	}
}
