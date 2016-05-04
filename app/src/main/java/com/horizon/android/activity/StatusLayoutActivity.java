package com.horizon.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.util.SystemStatusManager;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusLayoutActivity extends AutoLayoutActivity {

    @Bind(R.id.btn_left)
    ImageView btnBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_top)
    RelativeLayout rlTop;
    @Bind(R.id.iv_banner)
    ImageView ivBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.transparent);
        setContentView(R.layout.activity_status_layout);
        Application.getInstance().addAty(this);

        ButterKnife.bind(this);

        tvTitle.setText("布局延伸");

        int statusHeight = getStatusBarHeight(this);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) rlTop.getLayoutParams();
        lp.topMargin = statusHeight;
        rlTop.setLayoutParams(lp);
    }

    /**
     * 获得状态栏高度
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    @OnClick(R.id.btn_left)
    void backClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        Application.getInstance().removeAty(this);
        super.onDestroy();
    }
}
