package com.horizon.android.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.exception.CrashHandler;
import com.horizon.android.util.SystemStatusManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AutoLayoutActivity {

    CompositeSubscription mCompositeSubscription;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_left)
    ImageView btnLeft;
    @Bind(R.id.btn_right1)
    ImageView btnRight1;
    @Bind(R.id.btn_right2)
    ImageView btnRight2;

    FrameLayout layoutContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Application.getInstance().addAty(this);

        this.mCompositeSubscription = new CompositeSubscription();

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    private Resources getRes() {
        return Application.getRes();
    }

    private ImageLoader getImageLoader() {
        return Application.getImageLoader();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View layout = getLayoutInflater().inflate(R.layout.view_base, null);
        View vContent = getLayoutInflater().inflate(layoutResID, null);

        layoutContent = (FrameLayout) layout.findViewById(R.id.base_layout_content);
        layoutContent.addView(vContent);

        SystemStatusManager.setTranslucentStatus(this, R.color.main);

        super.setContentView(layout);

        ButterKnife.bind(this);
    }

    public void addSubscription(Subscription subscription) {
        this.mCompositeSubscription.add(subscription);
    }

    public void setTitle(String title) {
        if (title == null) {
            title = "";
        }
        tvTitle.setText(title);
    }

    @Override
    protected void onDestroy() {
        //在销毁时统一取消
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        Application.getInstance().removeAty(this);
        super.onDestroy();
    }

    @OnClick(R.id.btn_left)
    public void leftClick() {
        finish();
    }

}
