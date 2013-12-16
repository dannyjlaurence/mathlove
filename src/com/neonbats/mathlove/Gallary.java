package com.neonbats.mathlove;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class Gallary extends Activity {

	ImageView selectedImage;
	private Integer[] mImageIds = { R.drawable.image2,
			R.drawable.image3, R.drawable.image4, R.drawable.image5,
			R.drawable.image6, R.drawable.image7, R.drawable.image8 };

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);

		Gallery gallery = (Gallery) findViewById(R.id.gallery1);
		this.selectedImage = (ImageView) findViewById(R.id.imageView1);
		gallery.setSpacing(1);
		gallery.setAdapter(new GalleryImageAdapter(this));

		// clicklistener for Gallery
		gallery.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// show the selected Image
				Gallary.this.selectedImage
						.setImageResource(Gallary.this.mImageIds[position]);
			}
		});
	}

}