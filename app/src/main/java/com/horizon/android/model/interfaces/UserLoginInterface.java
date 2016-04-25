package com.horizon.android.model.interfaces;

import com.horizon.android.model.OnUserLoginListener;

public interface UserLoginInterface {

	public void login(String name, String pwd, OnUserLoginListener listener);
	
}
