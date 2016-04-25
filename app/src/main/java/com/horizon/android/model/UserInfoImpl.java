package com.horizon.android.model;

import com.horizon.android.model.bean.UserVo;
import com.horizon.android.model.interfaces.UserInfoInterface;

public class UserInfoImpl implements UserInfoInterface {

	@Override
	public UserVo getUserInfo() {
		UserVo user = new UserVo();
		user.setName("jiangxianming");
		user.setPwd("123");
		user.setAge(24);
		return user;
	}

}
