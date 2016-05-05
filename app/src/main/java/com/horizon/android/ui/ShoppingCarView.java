package com.horizon.android.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.horizon.android.R;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingCarView extends AutoLinearLayout {

	public ShoppingCarView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	private void init() {
		
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.bind(this);
	}

	@OnClick(R.id.btn_right1)
	void right1Click(){

	}

	@OnClick(R.id.btn_left)
	void leftClick(){

	}
}
