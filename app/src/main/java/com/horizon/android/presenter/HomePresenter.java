package com.horizon.android.presenter;

import com.horizon.android.model.interfaces.UserInfoInterface;
import com.horizon.android.view.HomeView;

public class HomePresenter {

    HomeView vHome;
    UserInfoInterface mUserInfo;

    public HomePresenter(HomeView home, UserInfoInterface mUserInfo){
        this.vHome = home;
        this.mUserInfo = mUserInfo;
    }

    public void showName(){
        vHome.showName(mUserInfo.getUserInfo());
    }
}
