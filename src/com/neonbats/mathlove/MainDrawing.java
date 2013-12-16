package com.neonbats.mathlove;

import com.neonbats.mathlove.TriangleMenu.MenuType;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainDrawing extends Activity implements OnTouchListener, AnimationListener {
	
	private TriangleMenu m;
	private int ct;
	private Point touchPt;
	private Animation zoomOut;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		RelativeLayout top = (RelativeLayout) findViewById(R.id.sierpinski);
		m = new TriangleMenu(this, MenuType.DRAWING);		
		top.addView(m);
		m.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		touchPt = new Point();
		touchPt.set((int) event.getX(), (int) event.getY());

		if (m.inTriangleNumber(touchPt) == 1 ||
				m.inTriangleNumber(touchPt) == 2) { // left triangle
			
			final Dialog d = new Dialog(this);
			Button ok = new Button(this);
			ok.setText("ok");
			
			ok.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					d.dismiss();
					ct = 0;
					return true;
				}
				
			});
			d.setTitle("Under Construction ...");
			d.setContentView(ok);
			if (ct == 0) {
				d.show();
				ct = 1;
			} 
			
		} else if (m.inTriangleNumber(touchPt) == 3) { // bottom triangle

			// TODO -- INSERT CODE TO GO TO BLANK CANVAS ACTIVITY HERE
			
		}
		
		return true;
	}
	
	@Override
	public void onBackPressed() {
	    
//		// Initialize animation= infinite <---- CHANGE THIS
//		int exitAnim = Animation.INFINITE;
//		exitAnim = R.anim.zoom_exit_bottom;
//		
//		zoomOut = AnimationUtils.loadAnimation(getApplicationContext(), exitAnim);        
//		zoomOut.setAnimationListener(this);
//		
//		m.startAnimation(zoomOut);
		
		Intent i = new Intent(this, MainFractalMenu.class);
		i.putExtra("classFrom", MainDrawing.class);
		startActivity(i);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		Intent i = new Intent(this, MainFractalMenu.class);
		startActivity(i);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
	}

}
