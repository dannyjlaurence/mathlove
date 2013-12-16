package com.neonbats.mathlove;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Chase
 * 
 */
public class ShapeButton extends Button implements OnClickListener {
	/***/
	Shape.BasicShape s;
	/***/
	Paint paint;
	/***/
	Path path;
	/***/
	DragActivity drag;

	/**
	 * @param sides
	 *            number of sides each shape should have
	 */
	public ShapeButton(int sides, DragActivity drag1) {
		super(drag1);
		Point p = new Point(0, 0);
		this.s = new Shape.BasicShape(p, sides);
		this.path = new Path();
		this.paint = new Paint();
		this.drag = drag1;
		this.setOnClickListener(this);
	}

	/**
	 * Draw the button
	 */
	@Override
	public void onDraw(Canvas c) {
		this.s.base.x = this.getWidth() / 2;
		this.s.base.y = this.getHeight() / 2;
		int size;
		if (this.getWidth() > this.getHeight())
			size = this.getHeight() / 4;
		else
			size = this.getWidth() / 4;
		int tilt = 0;
		if (this.s.sides == 4)
			tilt = 45;
		this.s.setShape(this.s.base, this.s.sides, tilt, size);
		this.paint.setColor(Color.CYAN);
		// path.setFillType(FillType.EVEN_ODD);
		this.paint.setStrokeWidth(3);
		this.s.draw(c, this.paint, this.path);
	}

	/**
	 * 
	 */
	@Override
	public void onClick(View v) {
		Point p = new Point(this.drag.drawingView.getWidth() / 2,
				this.drag.drawingView.getHeight() / 2);
		Shape.BasicShape bS = new Shape.BasicShape(p, this.s.sides);
		this.drag.drawingView.addShape(bS);
		if (this.drag.drawingView.isRecursing()) {
			Point s = new Point(p);
			s.x += 45;
			this.drag.drawingView.addShape(new Shape.RecursiveShape(bS, s));
		}
		this.drag.drawingView.invalidate();
	}
}
