package com.horizon.android.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.activity.ClickableSpanActivity;
import com.horizon.android.activity.RecyclerViewActivity;
import com.horizon.android.activity.WebViewActivity;
import com.horizon.android.activity.XCRoundImageViewActivity;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingCarView extends AutoLinearLayout {

	public ShoppingCarView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.bind(this);
	}

	@OnClick(R.id.btn_xcround_imageview)
	void xcroundImageviewClick(){
		getContext().startActivity(new Intent(getContext(), XCRoundImageViewActivity.class));
	}

	@OnClick(R.id.btn_recycler_view)
	void recyclerviewClick(){
		getContext().startActivity(new Intent(getContext(), RecyclerViewActivity.class));
	}

	@OnClick(R.id.btn_clickablespan)
	void clickableClick(){
		getContext().startActivity(new Intent(getContext(), ClickableSpanActivity.class));
	}

	@OnClick(R.id.btn_webview)
	void webViewClick(){
		Intent intent = new Intent(getContext(), WebViewActivity.class);
		intent.putExtra(Constants.BUNDLE_WEBVIEW_URL, "m.baidu.com");
		getContext().startActivity(intent);
	}

	@OnClick(R.id.btn_right1)
	void right1Click(){

	}

	@OnClick(R.id.btn_left)
	void leftClick(){

	}
}
