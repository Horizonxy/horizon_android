package com.horizon.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.widget.ReflectionImageView;
import com.horizon.android.widget.XCRoundImageView;

import butterknife.Bind;
import butterknife.OnClick;

public class XCRoundImageViewActivity extends BaseActivity {

    @Bind(R.id.circle_image)
    XCRoundImageView circleImageView;

    @Bind(R.id.relection_image)
    ReflectionImageView reflectionImageView;

    @Bind(R.id.iv_normal)
    ImageView ivNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xcround_image_view);
        setTitle("圆形圆角图片");

        getImageLoader().displayImage("http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg", circleImageView, Application.getInstance().getDefaultOptions());

        getImageLoader().displayImage("http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg", reflectionImageView, Application.getInstance().getDefaultOptions());

        getImageLoader().displayImage("http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg", ivNormal, Application.getInstance().getDefaultOptions());
    }

    @OnClick(R.id.iv_normal)
    void normalClick(View v){
        int[] screenLocation = new int[2];
		v.getLocationOnScreen(screenLocation);

		String url = "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg";

		Intent intent = new Intent(XCRoundImageViewActivity.this, PictureDetailActivity.class);
		intent.putExtra(Constants.BUNDLE_PIC_URL, url);
		intent.putExtra(Constants.BUNDLE_PIC_LEFT, screenLocation[0]);
		intent.putExtra(Constants.BUNDLE_PIC_TOP, screenLocation[1]);
		intent.putExtra(Constants.BUNDLE_PIC_WIDTH, v.getWidth());
		intent.putExtra(Constants.BUNDLE_PIC_HEIGHT, v.getHeight());
		startActivity(intent);
		overridePendingTransition(0, 0);
    }
}
