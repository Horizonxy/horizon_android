package com.horizon.android.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.exception.CrashHandler;
import com.horizon.android.util.SystemStatusManager;
import com.horizon.android.util.log.LogUtils;
import com.horizon.android.widget.MonIndicator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.autolayout.AutoLayoutActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity extends AutoLayoutActivity {

    protected Application app;
    protected Context mCxt;
    protected LayoutInflater mInflater;
    Resources res;
    ImageLoader imageLoader;
    private CompositeSubscription mCompositeSubscription;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_left)
    ImageView btnLeft;
    @Bind(R.id.btn_right1)
    ImageView btnRight1;
    @Bind(R.id.btn_right2)
    ImageView btnRight2;
    @Bind(R.id.base_init_load)
    MonIndicator mInitLoad;
    @Bind(R.id.base_no_net)
    LinearLayout llNoNet;
    @Bind(R.id.base_btn_retry)
    Button btnRetry;

    FrameLayout layoutContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = Application.getInstance();
        app.addAty(this);

        mCxt = this;
        mInflater = getLayoutInflater();
        res = Application.getRes();
        imageLoader = Application.getImageLoader();

        this.mCompositeSubscription = new CompositeSubscription();

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    public void setContentView(@LayoutRes int layoutResID, boolean isNeedLoadView){
        setContentView(layoutResID);

        if(isNeedLoadView) {
            initBody();
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View layout = mInflater.inflate(R.layout.view_base, null);
        View vContent = mInflater.inflate(layoutResID, null);

        layoutContent = (FrameLayout) layout.findViewById(R.id.base_layout_content);
        layoutContent.addView(vContent);

        SystemStatusManager.setTranslucentStatus(this, R.color.main);

        super.setContentView(layout);

        ButterKnife.bind(this);
    }

    public void initBody(){
        mInitLoad.setVisibility(View.VISIBLE);
        layoutContent.setVisibility(View.GONE);
        llNoNet.setVisibility(View.GONE);
    }

    public void addSubscription(Subscription subscription) {
        this.mCompositeSubscription.add(subscription);
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

    public void setTitle(String title) {
        if(title == null){
            title = "";
        }
        tvTitle.setText(title);
    }

    @OnClick(R.id.base_btn_retry)
    void clickRetry(){

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
    public void leftClick(){
        finish();
    }

}
