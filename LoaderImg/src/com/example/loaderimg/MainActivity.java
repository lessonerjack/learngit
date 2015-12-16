package com.example.loaderimg;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	private static final String TAG = "imgUri=====";
    private Button takePhone;
	private ImageView showPicture;
	private String filePath = "";
	private Uri imgUri;
	private static final int PICTURE_CODE = 0;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/out_put.jpg";
        takePhone = (Button) findViewById(R.id.btn_loader_img);
        takePhone.setOnClickListener(this);
        showPicture = (ImageView) findViewById(R.id.iv_show_picture);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.btn_loader_img:
			if (judgeSdCard()) {
				File file = new File(filePath);
				imgUri = Uri.fromFile(file);
				Log.i(TAG, imgUri.toString());
				Log.i(TAG, filePath);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
				startActivityForResult(intent, PICTURE_CODE);
			}else {
				Toast.makeText(getApplicationContext(), "Ã»ÓÐsd¿¨", Toast.LENGTH_LONG).show();
			}
			
			break;

		default:
			break;
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case PICTURE_CODE:
			if (resultCode == RESULT_OK) {
				Bitmap bitmap = null;
				int requestWidth = showPicture.getWidth();
				int requestHeight = showPicture.getHeight();
//				bitmap = BitmapFactory.decodeFile(filePath);
				bitmap = BitmapUtils.getFitSampleBitmap(filePath, requestWidth, requestHeight);
				showPicture.setImageBitmap(bitmap);
			}
			break;

		default:
			break;
		}
	}
	private boolean judgeSdCard(){
		return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		
	}

}
