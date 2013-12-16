package com.neonbats.mathlove;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import android.widget.Button;

public class DragActivity extends Activity {
	/**
	 * List of basic shape buttons
	 */
	LinearLayout shapeButtons;
	LinearLayout functionButtons;
	DrawingView drawingView;
	ToggleButton recurse;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.shapeButtons = (LinearLayout) findViewById(R.id.base_shape_menu);
		this.functionButtons = (LinearLayout) findViewById(R.id.color_picker_bar);
		for (int i = 1; i <= 7; i++) {
			if (i == 2)
				continue;
			this.shapeButtons.addView(new ShapeButton(i, this));
		}
		this.drawingView = (DrawingView) findViewById(R.id.drawing);

		((ToggleButton) findViewById(R.id.fill))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Shape.Fill = !Shape.Fill;
						DragActivity.this.drawingView.invalidate();
					}
				});

		((ToggleButton) findViewById(R.id.recurse))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						DragActivity.this.drawingView
								.setRecursing(!DragActivity.this.drawingView
										.isRecursing());
						DragActivity.this.drawingView.invalidate();
					}
				});

		((Button) findViewById(R.id.clear))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						DragActivity.this.drawingView.clearAll();
						DragActivity.this.drawingView.invalidate();
					}
				});

		this.functionButtons.addView(new ColorButton(this,
				Shape.BASE_SHAPE_COLOR, 1));
		this.functionButtons.addView(new ColorButton(this,
				Shape.FIRST_ITERATION_COLOR, 2));
		this.functionButtons.addView(new ColorButton(this, Shape.RECURSE_COLOR,
				3));

	}
}