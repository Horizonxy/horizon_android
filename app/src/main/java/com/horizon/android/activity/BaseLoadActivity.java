package com.horizon.android.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.horizon.android.R;
import com.horizon.android.util.SystemStatusManager;
import com.horizon.android.widget.MonIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseLoadActivity extends BaseActivity {

    @Bind(R.id.base_init_load)
    MonIndicator mInitLoad;
    @Bind(R.id.base_no_net)
    LinearLayout llNoNet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View layout = getLayoutInflater().inflate(R.layout.view_base_load, null);
        View vContent = getLayoutInflater().inflate(layoutResID, null);

        layoutContent = (FrameLayout) layout.findViewById(R.id.base_layout_content);
        layoutContent.addView(vContent);

        SystemStatusManager.setTranslucentStatus(this, R.color.main);

        super.setContentView(layout);

        ButterKnife.bind(this);

        initBody();
    }

    public void initBody(){
        mInitLoad.setVisibility(View.VISIBLE);
        layoutContent.setVisibility(View.GONE);
        llNoNet.setVisibility(View.GONE);
    }

    public void initAfter(){
        mInitLoad.setVisibility(View.GONE);
        layoutContent.setVisibility(View.VISIBLE);
        llNoNet.setVisibility(View.GONE);
    }

    public void noNet(){
        mInitLoad.setVisibility(View.GONE);
        layoutContent.setVisibility(View.GONE);
        llNoNet.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.base_btn_retry)
    void clickRetry(){

    }


}
