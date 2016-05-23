package com.horizon.android.web;

public interface WebViewView {
    void onReceivedTitle(String title);
    void onProgressChanged(int newProgress);
    void firstLoadAfter();
}
