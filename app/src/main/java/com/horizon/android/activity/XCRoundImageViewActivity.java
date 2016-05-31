package com.horizon.android.activity;

import android.os.Bundle;

import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.widget.ReflectionImageView;
import com.horizon.android.widget.XCRoundImageView;

import butterknife.Bind;

public class XCRoundImageViewActivity extends BaseActivity {

    @Bind(R.id.circle_image)
    XCRoundImageView circleImageView;

    @Bind(R.id.relection_image)
    ReflectionImageView reflectionImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xcround_image_view);
        setTitle("圆形圆角图片");

        getImageLoader().displayImage("http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg", circleImageView, Application.getInstance().getDefaultOptions());

        getImageLoader().displayImage("http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg", reflectionImageView, Application.getInstance().getDefaultOptions());

    }
}
