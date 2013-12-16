package com.neonbats.mathlove;

import com.neonbats.mathlove.ColorPickerDialog.OnColorChangedListener;

import android.widget.Button;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.View.OnClickListener;

public class ColorButton extends Button implements OnClickListener {
	/** color of the button */
	int color;
	/** outline of button */
	Path path = new Path();
	/** paint to draw button */
	Paint paint = new Paint();
	/** toggle telling which color is being changed in the Shape */
	final int COLOR_TO_CHANGE;
	/** parent activity */
	DragActivity d;

	/**
	 * @param context
	 * @param color1
	 * @param setting
	 */
	public ColorButton(DragActivity context, int color1, final int setting) {
		super(context);
		this.color = color1;
		this.COLOR_TO_CHANGE = setting;
		this.d = context;
		this.setOnClickListener(this);
		this.paint.setColor(this.color);
	}

	/**
 * 
 */

	@Override
	public void onDraw(Canvas c) {
		this.path.moveTo(this.getWidth() / 4, this.getHeight() / 4);
		this.path.lineTo(this.getWidth() / 4, this.getHeight() * 3 / 4);
		this.path.lineTo(this.getWidth() * 3 / 4, this.getHeight() * 3 / 4);
		this.path.lineTo(this.getWidth() * 3 / 4, this.getHeight() / 4);
		this.path.lineTo(this.getWidth() / 4, this.getHeight() / 4);
		this.paint.setColor(this.color);
		c.drawPath(this.path, this.paint);
	}

	/**
	 * 
	 */
	@Override
	public void onClick(View v) {

		// initialColor is the initially-selected color to be shown in the
		// rectangle on the left of the arrow.
		// for example, 0xff000000 is black, 0xff0000ff is blue. Please be aware
		// of the initial 0xff which is the alpha.
		ColorPickerDialog a = new ColorPickerDialog(getContext(), this.color);

		a.setAlphaSliderVisible(true);

		a.setOnColorChangedListener(new OnColorChangedListener() {

			@Override
			public void onColorChanged(int color) {
				ColorButton.this.color = color;
				System.out.println("color: " + color);
				System.out.println("alpha: " + (color >> 24));
				if (ColorButton.this.COLOR_TO_CHANGE == 1) {
					Shape.BASE_SHAPE_COLOR = ColorButton.this.color;
					Shape.BASE_SHAPE_COLOR_ALPHA = color >> 24;
				}
				if (ColorButton.this.COLOR_TO_CHANGE == 2) {
					Shape.FIRST_ITERATION_COLOR = ColorButton.this.color;
					Shape.FIRST_ITERATION_COLOR_ALPHA = color >> 24;
				}
				if (ColorButton.this.COLOR_TO_CHANGE == 3) {
					Shape.RECURSE_COLOR = ColorButton.this.color;
					Shape.RECURSE_COLOR_ALPHA = color >> 24;
				}
				ColorButton.this.d.drawingView.invalidate();

				ColorButton.this.paint.setColor(ColorButton.this.color);
				ColorButton.this.invalidate();
			}
		});
		a.show();

	}
}
