package com.neonbats.mathlove;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

class TriangleMenu extends View {
	
	// according to which menu it is, three buttons do different things
	public enum MenuType { FRACTAL, DRAWING, LESSONS, GALLERY }
	private MenuType menu;
	private String b1, b2, b3;
	private int selection;
	
	// array of points on triangle, 5 values for x, 3 values for y
	float[] px = new float[5];
	float[] py = new float[3];
	
	public TriangleMenu(Context context, MenuType m) {
		super(context);
		menu = m;
		selection = 0;
		if (m == MenuType.FRACTAL) {
			b1 = "Go To Lessons";
			b2 = "Design a Fractal";
			b3 = "View Gallery";
		} else if (m == MenuType.LESSONS) {
			b1 = "View All Lessons";
			b2 = "Next Lesson";
			b3 = "Placement Test";
		} else if (m == MenuType.DRAWING) {
			b1 = "Seed";
			b2 = "Random";
			b3 = "Blank";
		} else if (m == MenuType.GALLERY) {
			b1 = "Fractal Images";
			b2 = "Saved Images";
			b3 = "";
		}
	}
	
	/**
	 * Selection = left, right or bottom
	 */
	protected void onDraw(Canvas canv) {
		
		Path p = new Path();
		Paint canvPaint = new Paint();
		float textX1 = 0, textY1 = 0, textX2 = 0, textY2 = 0, textX3 = 0, textY3 = 0;

		// Draw triangle in canvas according to position 
		// (all three buttons of the menu will be on the same RelativeLayout)
		
		int k = 150;
		
		px[0] = 0;
		px[1] = canv.getWidth()/4;
		px[2] = canv.getWidth()/2;
		px[3] = (float) ((0.75)*canv.getWidth());
		px[4] = canv.getWidth();
		
		py[0] = canv.getHeight()/4 - k;
		py[1] = canv.getHeight()/2 - k/2;
		py[2] = (float) ((0.75)*canv.getHeight());
		
		if (selection == 1) {
			
			p.moveTo(px[0], py[0]);
			p.lineTo(px[1], py[0]);
			p.lineTo((px[0]+px[1])/2, (py[0]+py[1])/2);
			p.lineTo(px[0], py[0]);
			
			p.moveTo(px[1], py[0]);
			p.lineTo(px[2], py[0]);
			p.lineTo((px[1]+px[2])/2, (py[0]+py[1])/2);
			p.lineTo(px[1], py[0]);
			
			p.moveTo((px[0]+px[1])/2, (py[0]+py[1])/2);
			p.lineTo((px[1]+px[2])/2, (py[0]+py[1])/2);
			p.lineTo(px[1], py[1]);
			
		} else {
			p.moveTo(px[0], py[0]);
			p.lineTo(px[2], py[0]);
			p.lineTo(px[1], py[1]);
			p.lineTo(px[0], py[0]);
			textX1 = canv.getWidth()/4;
			textY1 = (float) ((0.3)*(py[1]-py[0]) + py[0]);
		}
		
		canvPaint.setColor(Color.BLUE);
		canv.drawPath(p, canvPaint);
		
		if (selection == 2) {
			
			p.moveTo(px[2], py[0]);
			p.lineTo(px[3], py[0]);
			p.lineTo((px[2]+px[3])/2, (py[0]+py[1])/2);
			p.lineTo(px[2], py[0]);
			
			p.moveTo(px[3], py[0]);
			p.lineTo(px[4], py[0]);
			p.lineTo((px[3]+px[4])/2, (py[0]+py[1])/2);
			p.lineTo(px[3], py[0]);
			
			p.moveTo((px[2]+px[3])/2, (py[0]+py[1])/2);
			p.lineTo((px[3]+px[4])/2, (py[0]+py[1])/2);
			p.lineTo(px[3], py[1]);
			
		} else {
			p.moveTo(px[2], py[0]);
			p.lineTo(px[4], py[0]);
			p.lineTo(px[3], py[1]);
			p.lineTo(px[2], py[0]);
			textX2 = (float) ((0.75)*canv.getWidth());
			textY2 = (float) ((0.3)*(py[1]-py[0]) + py[0]);
		}
		
		canvPaint.setColor(Color.BLUE);
		canv.drawPath(p, canvPaint);
		
		if (selection == 3) {
			
			p.moveTo(px[1], py[1]);
			p.lineTo(px[2], py[1]);
			p.lineTo((px[1]+px[2])/2, (py[1]+py[2])/2);
			p.lineTo(px[1], py[1]);
			
			p.moveTo(px[2], py[1]);
			p.lineTo(px[3], py[1]);
			p.lineTo((px[2]+px[3])/2, (py[1]+py[2])/2);
			p.lineTo(px[2], py[1]);
			
			p.moveTo((px[1]+px[2])/2, (py[1]+py[2])/2);
			p.lineTo((px[2]+px[3])/2, (py[1]+py[2])/2);
			p.lineTo(px[2], py[2]);
			p.lineTo((px[1]+px[2])/2, (py[1]+py[2])/2);
			
		} else {
			p.moveTo(px[1], py[1]);
			p.lineTo(px[3], py[1]);
			p.lineTo(px[2], py[2]);
			p.lineTo(px[1], py[1]);
			textX3 = canv.getWidth()/2;
			textY3 = (float) ((0.3)*(py[2]-py[1]) + py[1]);
		}

		canvPaint.setColor(Color.BLUE);
		canv.drawPath(p, canvPaint);
		
		canvPaint.setColor(Color.WHITE);
		canvPaint.setTextSize(30);
		canvPaint.setTextAlign(Paint.Align.CENTER);
		
		if (selection == 1) {
			canv.drawText(b2, textX2, textY2, canvPaint);
			canv.drawText(b3, textX3, textY3, canvPaint);
		} else if (selection == 2) {
			canv.drawText(b1, textX1, textY1, canvPaint);			
			canv.drawText(b3, textX3, textY3, canvPaint);
		} else if (selection == 3) {
			canv.drawText(b1, textX1, textY1, canvPaint);
			canv.drawText(b2, textX2, textY2, canvPaint);
		} else {
			canv.drawText(b1, textX1, textY1, canvPaint);
			canv.drawText(b2, textX2, textY2, canvPaint);
			canv.drawText(b3, textX3, textY3, canvPaint);
		}

	}
	
	/**
	 * Input a point, returns which triangle this point is in (left=1, right=2, or bottom=3)
	 */
	public int inTriangleNumber(Point pt) {
		
		int n = 3; // triangle = 3 sides
		
		// Make point arrays
		ArrayList<Float> t1x = new ArrayList<Float>(3);
		ArrayList<Float> t1y = new ArrayList<Float>(3);
		ArrayList<Float> t2x = new ArrayList<Float>(3);
		ArrayList<Float> t2y = new ArrayList<Float>(3);
		ArrayList<Float> t3x = new ArrayList<Float>(3);
		ArrayList<Float> t3y = new ArrayList<Float>(3);

		t1x.add(px[0]);
		t1x.add(px[2]);
		t1x.add(px[1]);
		
		t2x.add(px[2]);
		t2x.add(px[4]);
		t2x.add(px[3]);

		t3x.add(px[1]);
		t3x.add(px[3]);
		t3x.add(px[2]);

		t1y.add(py[0]);
		t1y.add(py[0]);
		t1y.add(py[1]);
		
		t2y.add(py[0]);
		t2y.add(py[0]);
		t2y.add(py[1]);

		t3y.add(py[1]);
		t3y.add(py[1]);
		t3y.add(py[2]);

		if (Shape.contains(n, t1x, t1y, pt)) { // left triangle
			return 1;
		} else if (Shape.contains(n, t2x, t2y, pt)) { // right triangle
			return 2;
		} else if (Shape.contains(n, t3x, t3y, pt)) { // bottom triangle
			return 3;
		}
		
		return 0;
	}
	
	public void setSelection(int i) {
		selection = i;
	}

	
}

