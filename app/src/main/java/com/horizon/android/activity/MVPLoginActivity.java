package com.horizon.android.activity;

import com.horizon.android.R;
import com.horizon.android.component.ActivityComponent;
import com.horizon.android.component.DaggerActivityComponent;
import com.horizon.android.model.bean.UserVo;
import com.horizon.android.module.ActivityModule;
import com.horizon.android.presenter.UserLoginPresenter;
import com.horizon.android.view.UserLoginView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.OnClick;

public class MVPLoginActivity extends BaseActivity implements UserLoginView {

	@Bind(R.id.et_name)
	EditText etName;
	@Bind(R.id.et_pwd)
	EditText etPwd;
	@Bind(R.id.btn_login)
	Button btnLogin;
	@Bind(R.id.tv_result)
	TextView tvResult;
	@Inject
	UserLoginPresenter mUserLoginPersenter;

	ActivityComponent component;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_mvp_login);
		setTitle("mvp login");

		component = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
		component.inject(this);
	}

	@OnClick(R.id.btn_login)
	void loginClick(){
		mUserLoginPersenter.login();
	}

	@Override
	public String getUserNamer() {
		return etName.getText().toString();
	}

	@Override
	public String getUserPwd() {
		return etPwd.getText().toString();
	}

	@Override
	public void setResult(UserVo user) {
		if(user != null){
			tvResult.setText(user.getName()+"\n"+user.getAge());
		} else {
			tvResult.setText("登录失败，请重试！！！");
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		component = null;
	}
}
