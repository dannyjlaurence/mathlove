package com.neonbats.mathlove;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryImageAdapter extends BaseAdapter {
	private Context mContext;

	private Integer[] mImageIds = { R.drawable.image2,
			R.drawable.image3, R.drawable.image4, R.drawable.image5,
			R.drawable.image6, R.drawable.image7, R.drawable.image8 };

	public GalleryImageAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// Override this method according to your need
	@Override
	public View getView(int index, View view, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		ImageView i = new ImageView(mContext);

		i.setImageResource(mImageIds[index]);
		i.setLayoutParams(new Gallery.LayoutParams(200, 200));

		i.setScaleType(ImageView.ScaleType.FIT_XY);

		return i;
	}
}
