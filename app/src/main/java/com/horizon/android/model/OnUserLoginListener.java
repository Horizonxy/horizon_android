package com.horizon.android.model;

import com.horizon.android.model.bean.UserVo;

public interface OnUserLoginListener {

	public void success(UserVo user);
	
	public void failuer();
	
}
