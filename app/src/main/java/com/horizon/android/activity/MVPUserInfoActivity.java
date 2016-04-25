package com.horizon.android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.horizon.android.R;
import com.horizon.android.component.ActivityComponent;
import com.horizon.android.component.DaggerActivityComponent;
import com.horizon.android.model.bean.UserVo;
import com.horizon.android.module.ActivityModule;
import com.horizon.android.presenter.UserInfoPresenter;
import com.horizon.android.view.UserInfoView;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.OnClick;

public class MVPUserInfoActivity extends BaseActivity implements UserInfoView {

	@Bind(R.id.btn_get_info)
	Button btnGetInfo;
	@Bind(R.id.tv_result)
	TextView tvResult;
	@Inject
	UserInfoPresenter mUserInfoPersenter;

	ActivityComponent component;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_user_info_mvp);
		setTitle("mvp get info");
		component = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
		component.inject(this);
	}

	@OnClick(R.id.btn_get_info)
	void getInfoClick(){
		mUserInfoPersenter.getUserInfo();
	}

	@Override
	public void setResult(UserVo user) {
		if(user != null){
			tvResult.setText(user.getName()+"\n"+user.getAge());
		} else {
			tvResult.setText("获取失败，请重试！！！");
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		component = null;
	}
}
