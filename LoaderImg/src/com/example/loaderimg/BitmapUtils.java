package com.example.loaderimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils {
	public static Bitmap getFitSampleBitmap(String imgPath,int width,int height){
		BitmapFactory.Options options = new  BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imgPath, options);
		options.inSampleSize = getFitSampleSize(width,height,options);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(imgPath, options);
	}

	private static int getFitSampleSize(int width, int height, BitmapFactory.Options options) {
		int sampleSize = 1;
		if (options.outHeight > height || options.outWidth > width) {
			int widthRadio = Math.round((float)options.outWidth / (float) width);
			int heightRadio = Math.round((float)options.outHeight / (float) height);
			sampleSize = Math.min(widthRadio, heightRadio);
		}
		return sampleSize;
	}
}
