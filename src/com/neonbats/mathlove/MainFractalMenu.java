package com.neonbats.mathlove;

import com.neonbats.mathlove.TriangleMenu.MenuType;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class MainFractalMenu extends Activity implements OnTouchListener, AnimationListener {

	private TriangleMenu m;
	private Animation zoomIn;
	private Point touchPt;
	private Animation zoomOut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		RelativeLayout top = (RelativeLayout) findViewById(R.id.sierpinski);
		m = new TriangleMenu(this, MenuType.FRACTAL);		
		top.addView(m);
		m.setOnTouchListener(this);
		
		int exitAnim = Animation.INFINITE;	
		
		ComponentName a = getCallingActivity();
		
		if (a != null) {
			
			if (a.getClassName().equals("com.neonbats.mathlove.MainLesson")) {
				
				exitAnim = R.anim.zoom_exit_left;
				zoomOut = AnimationUtils.loadAnimation(getApplicationContext(), exitAnim);        
				zoomOut.setAnimationListener(this);
				zoomOut.setFillAfter(true);
				m.startAnimation(zoomOut);
			
			} else if (a.equals(MainDrawing.class)) {
			
				exitAnim = R.anim.zoom_exit_right;
				zoomOut = AnimationUtils.loadAnimation(getApplicationContext(), exitAnim);        
				zoomOut.setAnimationListener(this);
				zoomOut.setFillAfter(true);
				m.startAnimation(zoomOut);
			
			} else if (a.equals(MainGallery.class)) {
			
				exitAnim = R.anim.zoom_exit_bottom;
				zoomOut = AnimationUtils.loadAnimation(getApplicationContext(), exitAnim);        
				zoomOut.setAnimationListener(this);
				zoomOut.setFillAfter(true);
				m.startAnimation(zoomOut);
			
			}
			
		}

	}

	
	/**
	 * OnTouch method of OnTouchListener interface
	 * 
	 * Currently, this does animation between activities ... TODO - modify to zoom in/out within an activity
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		touchPt = new Point();
		touchPt.set((int) event.getX(), (int) event.getY());

		// Initialize animation= infinite <---- CHANGE THIS
		int enterAnim = Animation.INFINITE;
		
		if (m.inTriangleNumber(touchPt) == 1) { // left triangle
			// first draw small triangles then zoom
			enterAnim = R.anim.zoom_enter_left;
			m.setSelection(1);
			
		} else if (m.inTriangleNumber(touchPt) == 2) { // right triangle
			// first draw small triangles then zoom
			enterAnim = R.anim.zoom_enter_right;
			m.setSelection(2);
		} else if (m.inTriangleNumber(touchPt) == 3) { // bottom triangle
			// first draw small triangles then zoom
			enterAnim = R.anim.zoom_enter_bottom;
			m.setSelection(3);
		}
		
		m.postInvalidate();
		
		zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), enterAnim);        
		zoomIn.setAnimationListener(this);
		
		zoomIn.setFillAfter(true);
		m.startAnimation(zoomIn);
		
		return true;
	}


	@Override
	public void onAnimationEnd(Animation animation) {
		// Start next activity
		
		ComponentName a = getCallingActivity();
		
		if ((a != null) && (a.getClassName().equals("com.neonbats.mathlove.MainLesson") 
				|| a.getClassName().equals("com.neonbats.mathlove.MainDrawing") 
				|| a.getClassName().equals("com.neonbats.mathlove.MainGallery"))) {
			
		} else {

			Class goTo = MainFractalMenu.class;		

			if (m.inTriangleNumber(touchPt) == 1) { // left triangle
				goTo = MainLesson.class;
			} else if (m.inTriangleNumber(touchPt) == 2) { // right triangle
				goTo = MainDrawing.class;
			} else if (m.inTriangleNumber(touchPt) == 3) { // bottom triangle
				goTo = MainGallery.class;
			}

			Intent i = new Intent(this, goTo);
			startActivity(i);
		}
		
		
	}


	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAnimationStart(Animation animation) {

	}
	
	
}
