package com.neonbats.mathlove;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LessonMap extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.lessonmap);
		 
		 int currLevel;
		 SharedPreferences sharedPref = getSharedPreferences("com.neonbats.mathlove.levels",0);
		 
		 for(int i = 1; i <= 10; i++){
			 String tempLevel = "levelunlock" + i;
			 currLevel = sharedPref.getInt(tempLevel, 0);
			 
			 if(currLevel == 0){
				 switch(i){
				 case 1: LessonMap.this.findViewById(R.id.button1).setBackgroundColor(0xFFCC3232);
				 case 2: LessonMap.this.findViewById(R.id.button2).setBackgroundColor(0xFFCC3232);
				 case 3: LessonMap.this.findViewById(R.id.button3).setBackgroundColor(0xFFCC3232);
				 case 4: LessonMap.this.findViewById(R.id.button4).setBackgroundColor(0xFFCC3232);
				 case 5: LessonMap.this.findViewById(R.id.button5).setBackgroundColor(0xFFCC3232);
				 case 6: LessonMap.this.findViewById(R.id.button6).setBackgroundColor(0xFFCC3232);
				 case 7: LessonMap.this.findViewById(R.id.button7).setBackgroundColor(0xFFCC3232);
				 case 8: LessonMap.this.findViewById(R.id.button8).setBackgroundColor(0xFFCC3232);
				 case 9: LessonMap.this.findViewById(R.id.button9).setBackgroundColor(0xFFCC3232);
				 case 10: LessonMap.this.findViewById(R.id.button10).setBackgroundColor(0xFFCC3232);
				 }
			 } else {
				 if(currLevel == 1){
					 switch(i){
					 case 1: LessonMap.this.findViewById(R.id.button1).setBackgroundColor(0xFFAAAAAA);
					 case 2: LessonMap.this.findViewById(R.id.button2).setBackgroundColor(0xFFAAAAAA);
    				 case 3: LessonMap.this.findViewById(R.id.button3).setBackgroundColor(0xFFAAAAAA);
    				 case 4: LessonMap.this.findViewById(R.id.button4).setBackgroundColor(0xFFAAAAAA);
    				 case 5: LessonMap.this.findViewById(R.id.button5).setBackgroundColor(0xFFAAAAAA);
    				 case 6: LessonMap.this.findViewById(R.id.button6).setBackgroundColor(0xFFAAAAAA);
    				 case 7: LessonMap.this.findViewById(R.id.button7).setBackgroundColor(0xFFAAAAAA);
    				 case 8: LessonMap.this.findViewById(R.id.button8).setBackgroundColor(0xFFAAAAAA);
    				 case 9: LessonMap.this.findViewById(R.id.button9).setBackgroundColor(0xFFAAAAAA);
    				 case 10: LessonMap.this.findViewById(R.id.button10).setBackgroundColor(0xFFAAAAAA);
    				 }
				 } else {
					 switch(i){
					 case 1: LessonMap.this.findViewById(R.id.button1).setBackgroundColor(0xFF608341);
    				 case 2: LessonMap.this.findViewById(R.id.button2).setBackgroundColor(0xFF608341);
    				 case 3: LessonMap.this.findViewById(R.id.button3).setBackgroundColor(0xFF608341);
    				 case 4: LessonMap.this.findViewById(R.id.button4).setBackgroundColor(0xFF608341);
    				 case 5: LessonMap.this.findViewById(R.id.button5).setBackgroundColor(0xFF608341);
    				 case 6: LessonMap.this.findViewById(R.id.button6).setBackgroundColor(0xFF608341);
    				 case 7: LessonMap.this.findViewById(R.id.button7).setBackgroundColor(0xFF608341);
    				 case 8: LessonMap.this.findViewById(R.id.button8).setBackgroundColor(0xFF608341);
    				 case 9: LessonMap.this.findViewById(R.id.button9).setBackgroundColor(0xFF608341);
    				 case 10: LessonMap.this.findViewById(R.id.button10).setBackgroundColor(0xFF608341);
    				 }
				 }
				 
			 }
		 }
		  
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		int currLevel;
		 SharedPreferences sharedPref = getSharedPreferences("com.neonbats.mathlove.levels",0);
		 
		 for(int i = 1; i <= 10; i++){
			 String tempLevel = "levelunlock" + i;
			 currLevel = sharedPref.getInt(tempLevel, 0);
			 
			 if(currLevel == 0){
				 switch(i){
				 case 1: LessonMap.this.findViewById(R.id.button1).setBackgroundColor(0xFFCC3232);
				 case 2: LessonMap.this.findViewById(R.id.button2).setBackgroundColor(0xFFCC3232);
				 case 3: LessonMap.this.findViewById(R.id.button3).setBackgroundColor(0xFFCC3232);
				 case 4: LessonMap.this.findViewById(R.id.button4).setBackgroundColor(0xFFCC3232);
				 case 5: LessonMap.this.findViewById(R.id.button5).setBackgroundColor(0xFFCC3232);
				 case 6: LessonMap.this.findViewById(R.id.button6).setBackgroundColor(0xFFCC3232);
				 case 7: LessonMap.this.findViewById(R.id.button7).setBackgroundColor(0xFFCC3232);
				 case 8: LessonMap.this.findViewById(R.id.button8).setBackgroundColor(0xFFCC3232);
				 case 9: LessonMap.this.findViewById(R.id.button9).setBackgroundColor(0xFFCC3232);
				 case 10: LessonMap.this.findViewById(R.id.button10).setBackgroundColor(0xFFCC3232);
				 }
			 } else {
				 if(currLevel == 1){
					 switch(i){
					 case 1: LessonMap.this.findViewById(R.id.button1).setBackgroundColor(0xFFAAAAAA);
					 case 2: LessonMap.this.findViewById(R.id.button2).setBackgroundColor(0xFFAAAAAA);
    				 case 3: LessonMap.this.findViewById(R.id.button3).setBackgroundColor(0xFFAAAAAA);
    				 case 4: LessonMap.this.findViewById(R.id.button4).setBackgroundColor(0xFFAAAAAA);
    				 case 5: LessonMap.this.findViewById(R.id.button5).setBackgroundColor(0xFFAAAAAA);
    				 case 6: LessonMap.this.findViewById(R.id.button6).setBackgroundColor(0xFFAAAAAA);
    				 case 7: LessonMap.this.findViewById(R.id.button7).setBackgroundColor(0xFFAAAAAA);
    				 case 8: LessonMap.this.findViewById(R.id.button8).setBackgroundColor(0xFFAAAAAA);
    				 case 9: LessonMap.this.findViewById(R.id.button9).setBackgroundColor(0xFFAAAAAA);
    				 case 10: LessonMap.this.findViewById(R.id.button10).setBackgroundColor(0xFFAAAAAA);
    				 }
				 } else {
					 switch(i){
					 case 1: LessonMap.this.findViewById(R.id.button1).setBackgroundColor(0xFF608341);
    				 case 2: LessonMap.this.findViewById(R.id.button2).setBackgroundColor(0xFF608341);
    				 case 3: LessonMap.this.findViewById(R.id.button3).setBackgroundColor(0xFF608341);
    				 case 4: LessonMap.this.findViewById(R.id.button4).setBackgroundColor(0xFF608341);
    				 case 5: LessonMap.this.findViewById(R.id.button5).setBackgroundColor(0xFF608341);
    				 case 6: LessonMap.this.findViewById(R.id.button6).setBackgroundColor(0xFF608341);
    				 case 7: LessonMap.this.findViewById(R.id.button7).setBackgroundColor(0xFF608341);
    				 case 8: LessonMap.this.findViewById(R.id.button8).setBackgroundColor(0xFF608341);
    				 case 9: LessonMap.this.findViewById(R.id.button9).setBackgroundColor(0xFF608341);
    				 case 10: LessonMap.this.findViewById(R.id.button10).setBackgroundColor(0xFF608341);
    				 }
				 }
				 
			 }
		 }
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	// Implement the OnClickListener callback
    public void onClick(View v) {
      // do something when the button is clicked
    	
    	final Dialog dialog;
    	
    	SharedPreferences sharedPref = getSharedPreferences("com.neonbats.mathlove.levels",0);
     	int currLevel = 0;
     	int currLevel2 = 0;
     	
    	switch(v.getId()){
    	case R.id.button1:
    		

			dialog = new Dialog(LessonMap.this);
        	dialog.setContentView(R.layout.lessonpopup);
        	dialog.setTitle("Lesson 1");
        	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Fractals 101");
        	((TextView)dialog.findViewById(R.id.lessonText)).setText("This lesson will introduce you to fractals, including how they are defined, what they look like, and a brief quiz.");
        	
        	Button lesson = (Button)dialog.findViewById(R.id.start);
        	lesson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	 Intent myIntent = new Intent(LessonMap.this, LessonOne.class);
                     startActivityForResult(myIntent, 0);
                     dialog.dismiss();                     
                }

            });
        	
        	dialog.show();
		
    		
    		
    		break;
    	case R.id.button2:
    		currLevel = sharedPref.getInt("level1", 0);
    		
    		if(currLevel == 1){
    			dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Lesson 2");
            	dialog.show();
    		} else {
    			dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 1 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 1!");
            
            	dialog.show();
    		}
    		
    		break;
    	case R.id.button3:
    		currLevel = sharedPref.getInt("level1", 0);
    		
    		if(currLevel == 1){	
	    		dialog = new Dialog(LessonMap.this);
	        	dialog.setContentView(R.layout.lessonpopup);
	        	dialog.setTitle("Lesson 3");
	        	dialog.show();
    		} else {
    			dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 1 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 1!");
            
            	dialog.show();
    		}
    		break;
    	case R.id.button4:
    		currLevel = sharedPref.getInt("level2", 0);
    		
    		if(currLevel == 1){	
	    		dialog = new Dialog(LessonMap.this);
	        	dialog.setContentView(R.layout.lessonpopup);
	        	dialog.setTitle("Lesson 4");
	        	dialog.show();
    		} else {
	        	dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 2 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 2!");
            
            	dialog.show();
    		}
    		break;
    	case R.id.button5:
    		currLevel = sharedPref.getInt("level2", 0);
    		
    		if(currLevel == 1){	
	    		dialog = new Dialog(LessonMap.this);
	        	dialog.setContentView(R.layout.lessonpopup);
	        	dialog.setTitle("Lesson 5");
	        	dialog.show();
    		} else {
	        	dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 2 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 2!");
            
            	dialog.show();
    		}
    		break;
    	case R.id.button6:
    		currLevel = sharedPref.getInt("level3", 0);
    		
    		if(currLevel == 1){	
	    		dialog = new Dialog(LessonMap.this);
	        	dialog.setContentView(R.layout.lessonpopup);
	        	dialog.setTitle("Lesson 6");
	        	dialog.show();
    		} else {
	        	dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 3 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 3!");
            
            	dialog.show();
    		}
    		break;
    	case R.id.button7:
    		currLevel = sharedPref.getInt("level4", 0);
    		currLevel2 = sharedPref.getInt("level5", 0);
    		
    		if(currLevel != 1){	
    			dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 4 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 4!");
            
            	dialog.show();
    		} else {
    			if (currLevel2 != 1) {
    				dialog = new Dialog(LessonMap.this);
                	dialog.setContentView(R.layout.lessonpopup);
                	dialog.setTitle("Level 5 not completed");
                	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
                	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 5!");
                
                	dialog.show();
    				
    			} else {
    				dialog = new Dialog(LessonMap.this);
    	        	dialog.setContentView(R.layout.lessonpopup);
    	        	dialog.setTitle("Lesson 7");
    	        	dialog.show();
    			}
    		}
    		
    		break;
    	case R.id.button8:
    		currLevel = sharedPref.getInt("level6", 0);
    		
    		if(currLevel == 1){	
	    		dialog = new Dialog(LessonMap.this);
	        	dialog.setContentView(R.layout.lessonpopup);
	        	dialog.setTitle("Lesson 8");
	        	dialog.show();
    		} else {
	        	dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 6 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 6!");
            
            	dialog.show();
	    	}
    		break;
    	case R.id.button9:
    		currLevel = sharedPref.getInt("level7", 0);
    		
    		if(currLevel == 1){	
	    		dialog = new Dialog(LessonMap.this);
	        	dialog.setContentView(R.layout.lessonpopup);
	        	dialog.setTitle("Lesson 9");
	        	dialog.show();
    		} else {
	        	dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 7 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 7!");
            
            	dialog.show();
	    	}
    		
    		break;
    	case R.id.button10:
    		currLevel = sharedPref.getInt("level8", 0);
    		currLevel2 = sharedPref.getInt("level9", 0);
    		
    		if(currLevel != 1){	
    			dialog = new Dialog(LessonMap.this);
            	dialog.setContentView(R.layout.lessonpopup);
            	dialog.setTitle("Level 8 not completed");
            	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
            	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 8!");
            
            	dialog.show();
    		} else {
    			if (currLevel2 != 1) {
    				dialog = new Dialog(LessonMap.this);
                	dialog.setContentView(R.layout.lessonpopup);
                	dialog.setTitle("Level 9 not completed");
                	((TextView)dialog.findViewById(R.id.lessonHeader)).setText("Wait!");
                	((TextView)dialog.findViewById(R.id.lessonText)).setText("You need to complete level 9!");
                
                	dialog.show();
    				
    			} else {
    				dialog = new Dialog(LessonMap.this);
    	        	dialog.setContentView(R.layout.lessonpopup);
    	        	dialog.setTitle("Lesson 10");
    	        	dialog.show();
    			}
    		}
    		
    		break;
    		
    		
    	
    	}
    	
    	
    	    	
    }
}
