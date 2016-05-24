package com.horizon.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.horizon.android.Constants;
import com.horizon.android.R;

import butterknife.Bind;

public class ClickableSpanActivity extends BaseActivity {

    @Bind(R.id.tv_clickspan)
    TextView tvClickSpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickable_span);
        setTitle("Clickable Span");

        String des = "跳转";
        String link = "百度";

        SpannableString spanLink = new SpannableString(link);
        spanLink.setSpan(new Clickable2Baidu(), 0, link.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvClickSpan.setText(des);
        tvClickSpan.append(spanLink);

        tvClickSpan.setMovementMethod(LinkMovementMethod.getInstance());
    }

    class Clickable2Baidu extends ClickableSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(getRes().getColor(R.color.red));
        }

        @Override
        public void onClick(View widget) {
            Intent intent = new Intent(ClickableSpanActivity.this, WebViewActivity.class);
            intent.putExtra(Constants.BUNDLE_WEBVIEW_URL, "http://m.baidu.com");
            ClickableSpanActivity.this.startActivity(intent);
        }
    }
}
