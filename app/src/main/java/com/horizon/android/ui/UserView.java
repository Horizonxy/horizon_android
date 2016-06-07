package com.horizon.android.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import com.horizon.android.R;
import com.horizon.android.activity.RecycleViewImageForDetailActivity;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserView extends AutoLinearLayout {

	public UserView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.bind(this);

	}

	@OnClick(R.id.btn_recycler_image)
	void recyclerImageClick(){
		getContext().startActivity(new Intent(getContext(), RecycleViewImageForDetailActivity.class));
	}

	@OnClick(R.id.btn_right1)
	void right1Click(){

	}

	@OnClick(R.id.btn_left)
	void leftClick(){

	}

}
