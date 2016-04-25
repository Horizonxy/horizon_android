package com.horizon.android.presenter;

import com.horizon.android.model.interfaces.UserInfoInterface;
import com.horizon.android.model.bean.UserVo;
import com.horizon.android.view.UserInfoView;

public class UserInfoPresenter {

	private UserInfoInterface mUserInfo;
	private UserInfoView vUserInfo;
	
	public UserInfoPresenter(UserInfoView vUserInfo, UserInfoInterface mUserInfo) {
		this.mUserInfo = mUserInfo;
		this.vUserInfo = vUserInfo;
	}
	
	public void getUserInfo(){
		UserVo user = mUserInfo.getUserInfo();
		vUserInfo.setResult(user);
	}
	
}
