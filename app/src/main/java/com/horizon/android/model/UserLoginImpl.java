package com.horizon.android.model;

import com.horizon.android.model.bean.UserVo;
import com.horizon.android.model.interfaces.UserLoginInterface;

public class UserLoginImpl implements UserLoginInterface {

	@Override
	public void login(String name, String pwd, OnUserLoginListener listener) {
		if("jxm".equals(name) && "123".equals(pwd)){
			UserVo user = new UserVo();
			user.setName(name);
			user.setPwd(pwd);
			user.setAge(24);
			listener.success(user);
		} else {
			listener.failuer();
		}
	}

}
