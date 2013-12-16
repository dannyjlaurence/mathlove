package com.neonbats.mathlove;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * The following code is adapted from Sue Smith's "Android SDK: Create a Drawing
 * App – Interface Creation" tutorial series at
 * http://mobile.tutsplus.com/tutorials
 * /android/android-sdk-create-a-drawing-app-interface-creation/
 * 
 * @author Chase
 * 
 */
public class DrawingView extends View {

	/** drawing path */
	private Path drawPath;
	/** drawing paint */
	private Paint drawPaint;
	/** canvas paint */
	private Paint canvasPaint;
	/** initial color */
	private int paintColor = 0xFF00FFFF;
	/** canvas */
	private Canvas drawCanvas;
	/** canvas bitmap */
	private Bitmap canvasBitmap;

	/** true if this is recursing */
	private boolean recursing;

	/** list of shapes in this DrawingView */
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	/**
	 * Create a drawing area
	 * 
	 * @param context
	 * @param attrs
	 */
	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
	}

	/**
	 * prepare area for drawing
	 */
	private void setupDrawing() {
		setDrawPath(new Path());
		setDrawPaint(new Paint());
		getDrawPaint().setColor(
				this.getContext().getResources().getColor(R.color.aqua));
		getDrawPaint().setAntiAlias(true);
		getDrawPaint().setStrokeWidth(2);
		getDrawPaint().setStyle(Paint.Style.STROKE);
		getDrawPaint().setStrokeJoin(Paint.Join.ROUND);
		getDrawPaint().setStrokeCap(Paint.Cap.ROUND);
		setCanvasPaint(new Paint(Paint.DITHER_FLAG));
	}

	/**
	 * prepare canvas whenever size changes
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		setCanvasBitmap(Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888));
		setDrawCanvas(new Canvas(getCanvasBitmap()));
	}

	/**
	 * 
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		for (Shape s : getShapes()) {
			getDrawPaint().setColor(Color.RED);
			s.draw(canvas, getDrawPaint(), getDrawPath());
		}
	}

	/** shape to change during action move */
	Shape adjusting;
	/** if shape is being moved */
	boolean translate;
	/** if shape is being resized and rotated */
	boolean resizeAndRotate;
	/** start point for tracking */
	private Point start;

	/**
	 * handle inside canvas touching
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Point touch = new Point((int) event.getX(), (int) event.getY());

		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			if (this.adjusting != null) {
				if (this.translate)
					translate(this.adjusting, touch);
				else if (this.resizeAndRotate)
					resize(this.adjusting, touch);
			} else
				translateAll(touch);
			invalidate();
			break;
		case MotionEvent.ACTION_DOWN:
			this.start = touch;

			for (Shape s : this.shapes) {
				if (s.containsBoarder(touch)) {
					this.resizeAndRotate = true;
					this.adjusting = s;
					break;
				}

				if (s.containsCenter(touch)) {
					this.translate = true;
					this.adjusting = s;
					break;
				}
			}
			break;

		case MotionEvent.ACTION_UP:
			this.adjusting = null;
			this.translate = false;
			this.resizeAndRotate = false;
			break;
		default:
			break;
		}
		this.start = touch;
		return true;
	}

	/**
	 * translate all shapes
	 * 
	 * @param touch
	 */
	private void translateAll(Point touch) {
		for (Shape s : this.shapes) {
			translate(s, touch);
		}

	}

	/**
	 * 
	 * @param s
	 * @param touch
	 */
	void translate(Shape s, Point touch) {
		if (s instanceof Shape.BasicShape) {
			Shape.BasicShape bS = (Shape.BasicShape) s;
			int disX = touch.x - this.start.x;
			int disY = touch.y - this.start.y;
			Point bSP = bS.base;
			bSP.x += (disX);
			bSP.y += (disY);
			bS.setShape(bSP, s.sides, s.rotation, s.rad);
		}

		if (s instanceof Shape.RecursiveShape) {
			Shape.RecursiveShape rS = (Shape.RecursiveShape) s;
			rS.base.x += (touch.x - this.start.x);
			rS.base.y += (touch.y - this.start.y);
			rS.setShape(rS.base, rS.tiltAngle, rS.scale);
		}
	}

	/**
	 * 
	 * @param s
	 * @param touch
	 */
	void resize(Shape s, Point touch) {
		if (s instanceof Shape.BasicShape) {

			double angleAdd = Math.atan2(touch.y - this.start.y, touch.x
					- this.start.x)
					* 180 / Math.PI;
			// TODO improve resizing
			if (!s.containsBoarder(touch))
				if (s.containsCenter(touch))
					s.rad--;
				else
					s.rad++;
			if (s.rad > Shape.min)
				s.rad = (int) Shape.min;
			if (angleAdd < 0)
				angleAdd += 360;
			angleAdd += s.rotation;
			if (angleAdd > 360)
				angleAdd -= 360;
			((Shape.BasicShape) s).setShape(s.base, s.sides, (int) angleAdd,
					s.rad);
		}

		if (s instanceof Shape.RecursiveShape) {
			// TODO implement scaling
			Shape.RecursiveShape rS = (Shape.RecursiveShape) s;
			double scaleAdjust = 0;
			if (!s.containsBoarder(touch))
				if (s.containsCenter(touch))
					scaleAdjust = -0.1;
				else
					scaleAdjust = 0.1;

			double angleAdd = Math.atan2(touch.y - this.start.y, touch.x
					- this.start.x);
			rS.setShape(rS.base, rS.tiltAngle + angleAdd, rS.scale
					+ scaleAdjust);

		}

	}

	/**
	 * get current paint
	 * 
	 * @return this views current paint
	 */
	public Paint getDrawPaint() {
		return this.drawPaint;
	}

	/**
	 * set current paint
	 * 
	 * @param drawPaint1
	 */
	public void setDrawPaint(Paint drawPaint1) {
		this.drawPaint = drawPaint1;
	}

	/**
	 * get drawingPath
	 * 
	 * @return this views current drawingPath
	 */
	public Path getDrawPath() {
		return this.drawPath;
	}

	/**
	 * set drawingPath
	 * 
	 * @param drawPath1
	 */
	public void setDrawPath(Path drawPath1) {
		this.drawPath = drawPath1;
	}

	/**
	 * 
	 * @return this view's picture as a bitmap
	 */
	public Bitmap getCanvasBitmap() {
		return this.canvasBitmap;
	}

	/**
	 * set this view's bitmap image
	 * 
	 * @param canvasBitmap1
	 */
	public void setCanvasBitmap(Bitmap canvasBitmap1) {
		this.canvasBitmap = canvasBitmap1;
	}

	/**
	 * 
	 * @return the canvas this drawingView's image is placed on
	 */
	public Canvas getDrawCanvas() {
		return this.drawCanvas;
	}

	/**
	 * set the drawingview's main image
	 * 
	 * @param drawCanvas1
	 */
	public void setDrawCanvas(Canvas drawCanvas1) {
		this.drawCanvas = drawCanvas1;
	}

	/**
	 * 
	 * @return the paint used on this DrawingView's canvas currently
	 */
	public Paint getCanvasPaint() {
		return this.canvasPaint;
	}

	/**
	 * set this DrawingView's canvas
	 * 
	 * @param canvasPaint1
	 */
	public void setCanvasPaint(Paint canvasPaint1) {
		this.canvasPaint = canvasPaint1;
	}

	/**
	 * 
	 * @return current color being used
	 */
	public int getPaintColor() {
		return this.paintColor;
	}

	/**
	 * set the current color to the according hexadecimal color
	 * 
	 * @param paintColor1
	 */
	public void setPaintColor(int paintColor1) {
		this.paintColor = paintColor1;
	}

	/**
	 * @return the shapes in this image
	 */
	public ArrayList<Shape> getShapes() {
		return this.shapes;
	}

	/**
	 * 
	 * @param shapes1
	 *            set the shapes of this image
	 */
	public void setShapes(ArrayList<Shape> shapes1) {
		this.shapes = shapes1;
	}

	/**
	 * adds shape and then sorts list
	 * 
	 * @param s
	 *            shape to add
	 */
	public void addShape(Shape s) {
		this.shapes.add(s);
		Collections.sort(this.shapes);
	}

	public void setRecurse() {
		ArrayList<Shape.BasicShape> toRecurse = new ArrayList<Shape.BasicShape>();
		for (Shape s : shapes) {
			if (s instanceof Shape.BasicShape) {
				toRecurse.add((Shape.BasicShape) s);
			}
		}
		for (Shape.BasicShape s : toRecurse) {
			Point p = new Point(s.base);
			p.x += 45;
			this.shapes.add(new Shape.RecursiveShape(s, p));
		}
	}

	public void removeRecurse() {
		ArrayList<Shape.RecursiveShape> toRemove = new ArrayList<Shape.RecursiveShape>();
		for (Shape s : shapes) {
			if (s instanceof Shape.RecursiveShape) {
				toRemove.add((Shape.RecursiveShape) s);
			}
			if (s instanceof Shape.BasicShape)
				((Shape.BasicShape) s).recursiveShape = null;
		}
		for (Shape.RecursiveShape s : toRemove) {
			this.shapes.remove(s);
		}
	}

	public boolean isRecursing() {
		return recursing;
	}

	public void setRecursing(boolean recursing) {
		this.recursing = recursing;
		if (this.recursing)
			this.setRecurse();
		else
			this.removeRecurse();
		invalidate();
	}

	public void clearAll() {
		this.shapes.clear();
		this.invalidate();
	}
}
