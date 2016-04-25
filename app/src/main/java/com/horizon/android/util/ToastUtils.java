package com.horizon.android.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.horizon.android.R;

public class ToastUtils {

	@SuppressLint("InflateParams")
	public static void show(Activity cxt, String str, int drawable) {
		if(null == str) {
			str = "";
		}
		LayoutInflater inflater = LayoutInflater.from(cxt);
		View layout = inflater.inflate(R.layout.view_toast, (ViewGroup) ((Activity) cxt).findViewById(R.id.ll_toast));
		
		if(drawable != 0){
			ImageView image = (ImageView) layout.findViewById(R.id.tv_img_toast);
			image.setImageResource(drawable);
			image.setVisibility(View.VISIBLE);
		}
		TextView text = (TextView) layout.findViewById(R.id.tv_text_toast);
		text.setText(str); 
		
		Toast toast = new Toast(cxt);
		// toast.setGravity(Gravity.RIGHT | Gravity.TOP, 12, 40);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}
	
	public static void show(Activity  cxt, String str) {
		show(cxt, str, 0);
	}

}
