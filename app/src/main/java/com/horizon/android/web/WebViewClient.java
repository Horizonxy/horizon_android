package com.horizon.android.web;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.util.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebViewClient extends android.webkit.WebViewClient {

    private static String IMG_CACHE = FileUtils.getEnvPath(Application.getInstance(), true, Constants.IMG_CACHE_DIR);
    WebViewView mWebView;

    public WebViewClient(WebViewView mWebView){
        this.mWebView = mWebView;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (isImage(url)) {
            File image = FileUtils.findFile(IMG_CACHE, url.substring(url.lastIndexOf("/") + 1));
            if (image != null) {
                try {
                    return new WebResourceResponse("image/png", "UTF-8", new FileInputStream(image));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                InputStream is = downloadImage2Cache(url);
                if(is != null){
                    return new WebResourceResponse("image/png", "UTF-8", is);
                }
            }
        }

        return super.shouldInterceptRequest(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        if(url.startsWith("http")){
//            view.loadUrl(url);
//        }
//
//        return true;
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mWebView.firstLoadAfter();
    }

    private InputStream downloadImage2Cache(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                final InputStream is = conn.getInputStream();
                return new ImageInputStream(url, is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isImage(String str) {
        String suffix = str.substring(str.lastIndexOf(".") + 1);
        return (null != suffix) && (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("jpeg") ||
                suffix.equalsIgnoreCase("bmp") || suffix.equalsIgnoreCase("gif"));
    }


    class ImageInputStream extends BufferedInputStream {

        private FileOutputStream fos;
        private int len;

        public ImageInputStream(String url, InputStream is) {
            super(is);
            this.len = 0;
            File file = new File(IMG_CACHE, url.substring(url.lastIndexOf("/") + 1));
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public synchronized int read(byte[] buffer, int byteOffset, int byteCount)
                throws IOException {
            len = super.read(buffer, byteOffset, byteCount);

            if (len == -1) {
                fos.flush();
                fos.close();
            } else {
                fos.write(buffer, 0, len);
            }

            return len;
        }

    }
}
