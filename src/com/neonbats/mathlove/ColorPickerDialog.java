/*
 * Copyright (C) 2010 Daniel Nilsson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.neonbats.mathlove;

import java.util.Locale;

import com.neonbats.mathlove.ColorPickerView.OnColorChangedListener;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ColorPickerDialog extends Dialog implements
		ColorPickerView.OnColorChangedListener, View.OnClickListener {

	private ColorPickerView mColorPicker;

	private ColorPickerPanelView mOldColor;
	private ColorPickerPanelView mNewColor;

	private EditText mHexVal;
	private boolean mHexValueEnabled = false;
	private ColorStateList mHexDefaultTextColor;

	private OnColorChangedListener mListener;

	public interface OnColorChangedListener {
		public void onColorChanged(int color);
	}

	public ColorPickerDialog(Context context, int initialColor) {
		super(context);

		init(initialColor);
	}

	private void init(int color) {
		// To fight color banding.
		getWindow().setFormat(PixelFormat.RGBA_8888);

		setUp(color);

	}

	private void setUp(int color) {

		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(R.layout.dialog_color_picker, null);

		setContentView(layout);

		setTitle(R.string.dialog_color_picker);

		this.mColorPicker = (ColorPickerView) layout
				.findViewById(R.id.color_picker_view);
		this.mOldColor = (ColorPickerPanelView) layout
				.findViewById(R.id.old_color_panel);
		this.mNewColor = (ColorPickerPanelView) layout
				.findViewById(R.id.new_color_panel);

		this.mHexVal = (EditText) layout.findViewById(R.id.hex_val);
		this.mHexVal.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
		this.mHexDefaultTextColor = this.mHexVal.getTextColors();

		this.mHexVal
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {

					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							InputMethodManager imm = (InputMethodManager) v
									.getContext().getSystemService(
											Context.INPUT_METHOD_SERVICE);
							imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
							String s = ColorPickerDialog.this.mHexVal.getText()
									.toString();
							if (s.length() > 5 || s.length() < 10) {
								try {
									int c = ColorPickerPreference
											.convertToColorInt(s.toString());
									ColorPickerDialog.this.mColorPicker
											.setColor(c, true);
									ColorPickerDialog.this.mHexVal
											.setTextColor(ColorPickerDialog.this.mHexDefaultTextColor);
								} catch (IllegalArgumentException e) {
									ColorPickerDialog.this.mHexVal
											.setTextColor(Color.RED);
								}
							} else {
								ColorPickerDialog.this.mHexVal
										.setTextColor(Color.RED);
							}
							return true;
						}
						return false;
					}
				});

		((LinearLayout) this.mOldColor.getParent()).setPadding(
				Math.round(this.mColorPicker.getDrawingOffset()), 0,
				Math.round(this.mColorPicker.getDrawingOffset()), 0);

		this.mOldColor.setOnClickListener(this);
		this.mNewColor.setOnClickListener(this);
		this.mColorPicker.setOnColorChangedListener(this);
		this.mOldColor.setColor(color);
		this.mColorPicker.setColor(color, true);

	}

	@Override
	public void onColorChanged(int color) {

		this.mNewColor.setColor(color);

		if (this.mHexValueEnabled)
			updateHexValue(color);

		/*
		 * if (mListener != null) { mListener.onColorChanged(color); }
		 */

	}

	public void setHexValueEnabled(boolean enable) {
		this.mHexValueEnabled = enable;
		if (enable) {
			this.mHexVal.setVisibility(View.VISIBLE);
			updateHexLengthFilter();
			updateHexValue(getColor());
		} else
			this.mHexVal.setVisibility(View.GONE);
	}

	public boolean getHexValueEnabled() {
		return this.mHexValueEnabled;
	}

	private void updateHexLengthFilter() {
		if (getAlphaSliderVisible())
			this.mHexVal
					.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
							9) });
		else
			this.mHexVal
					.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
							7) });
	}

	private void updateHexValue(int color) {
		if (getAlphaSliderVisible()) {
			this.mHexVal.setText(ColorPickerPreference.convertToARGB(color)
					.toUpperCase(Locale.getDefault()));
		} else {
			this.mHexVal.setText(ColorPickerPreference.convertToRGB(color)
					.toUpperCase(Locale.getDefault()));
		}
		this.mHexVal.setTextColor(this.mHexDefaultTextColor);
	}

	public void setAlphaSliderVisible(boolean visible) {
		this.mColorPicker.setAlphaSliderVisible(visible);
		if (this.mHexValueEnabled) {
			updateHexLengthFilter();
			updateHexValue(getColor());
		}
	}

	public boolean getAlphaSliderVisible() {
		return this.mColorPicker.getAlphaSliderVisible();
	}

	/**
	 * Set a OnColorChangedListener to get notified when the color selected by
	 * the user has changed.
	 * 
	 * @param listener
	 */
	public void setOnColorChangedListener(OnColorChangedListener listener) {
		this.mListener = listener;
	}

	public int getColor() {
		return this.mColorPicker.getColor();
	}


	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.new_color_panel) {
			if (this.mListener != null) {
				this.mListener.onColorChanged(this.mNewColor.getColor());
			}
		}
		dismiss();
	}

	@Override
	public Bundle onSaveInstanceState() {
		Bundle state = super.onSaveInstanceState();
		state.putInt("old_color", this.mOldColor.getColor());
		state.putInt("new_color", this.mNewColor.getColor());
		return state;
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		this.mOldColor.setColor(savedInstanceState.getInt("old_color"));
		this.mColorPicker
				.setColor(savedInstanceState.getInt("new_color"), true);
	}
}
