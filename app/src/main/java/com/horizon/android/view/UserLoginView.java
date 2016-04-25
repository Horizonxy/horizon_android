package com.horizon.android.view;


import com.horizon.android.model.bean.UserVo;

public interface UserLoginView {

	public String getUserNamer();
	
	public String getUserPwd();
	
	public void setResult(UserVo user);
	
}
