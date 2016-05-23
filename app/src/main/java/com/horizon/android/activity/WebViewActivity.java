package com.horizon.android.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.util.log.LogUtils;
import com.horizon.android.web.WebChromeClient;
import com.horizon.android.web.WebView;
import com.horizon.android.web.WebViewClient;
import com.horizon.android.web.WebViewView;

import butterknife.Bind;

public class WebViewActivity extends BaseLoadActivity implements WebViewView {

    @Bind(R.id.web_view)
    WebView mWevView;
    @Bind(R.id.progress)
    ProgressBar mProgress;

    boolean firstLoadAfter;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        firstLoadAfter = true;

        mWevView.setWebChromeClient(new WebChromeClient(this));
        mWevView.setWebViewClient(new WebViewClient(this));

        url = getIntent().getStringExtra(Constants.BUNDLE_WEBVIEW_URL);

        if(url != null) {
            if(!url.startsWith("http")){
                url = "http://".concat(url);
            }
            firstLoad();
        }
    }

    private void firstLoad(){
        if(isNetworkAvailable()) {
            mWevView.loadUrl(url);
        } else {
            noNet();
        }
    }

    @Override
    void clickRetry() {
        initBody();
        firstLoad();
    }

    @Override
    public void firstLoadAfter(){
        if(firstLoadAfter){
            initAfter();
            firstLoadAfter = false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    back();
                    return true;
                default:
                    break;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void back(){
        if (url != null) {
            if(url.equals(mWevView.getUrl())){
                finish();
            } else if (null != mWevView.getUrl() && mWevView.getUrl().startsWith("data:")) {//页面请求失败，报错
                if (mWevView.canGoBackOrForward(-2)) {
                    mWevView.goBackOrForward(-2);
                    finish();
                } else if (mWevView.canGoBack()) {
                    mWevView.goBack();
                }else {
                    finish();
                }
            } else if (mWevView.canGoBack()) {
                mWevView.goBack();
            } else {
                finish();
            }
        } else if(mWevView.canGoBack()){
            mWevView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onReceivedTitle(String title) {
        setTitle(title);
    }

    @Override
    public void onProgressChanged(int newProgress) {
        LogUtils.e("newProgress: "+newProgress);
        if(newProgress == 100){
            mProgress.setVisibility(View.GONE);
        } else {
            if(mProgress.getVisibility() == View.GONE){
                mProgress.setVisibility(View.VISIBLE);
            }
            mProgress.setProgress(newProgress);
        }
    }

    private boolean isNetworkAvailable() {
        // 得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // 去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            return manager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
