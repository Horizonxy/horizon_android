package com.horizon.android.presenter;

import com.horizon.android.model.OnUserLoginListener;
import com.horizon.android.model.interfaces.UserLoginInterface;
import com.horizon.android.model.bean.UserVo;
import com.horizon.android.view.UserLoginView;

public class UserLoginPresenter {

	private UserLoginInterface mUserLogin;
	private UserLoginView vUserLogin;
	
	public UserLoginPresenter(UserLoginView vUserLogin, UserLoginInterface mUserLogin) {
		this.mUserLogin = mUserLogin;
		this.vUserLogin = vUserLogin;
	}
	
	public void login(){
		mUserLogin.login(vUserLogin.getUserNamer(), vUserLogin.getUserPwd(), new OnUserLoginListener() {
			
			@Override
			public void success(UserVo user) {
				vUserLogin.setResult(user);
			}
			
			@Override
			public void failuer() {
				vUserLogin.setResult(null);				
			}
		});
	}
	
}
