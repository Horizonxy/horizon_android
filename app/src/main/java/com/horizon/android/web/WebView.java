package com.horizon.android.web;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;

public class WebView extends android.webkit.WebView {

    public WebView(Context context) {
        this(context, null);
    }

    public WebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initSetting();
    }

    private void initSetting(){
        setBackgroundColor(0);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setUserAgentString("YooYo/1.0 " + getSettings().getUserAgentString());
        // 打开本地缓存提供JS调用
        getSettings().setDomStorageEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new WebViewClient());

        clearHistory();
    }
}
