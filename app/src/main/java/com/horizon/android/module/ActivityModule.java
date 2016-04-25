package com.horizon.android.module;

import android.app.Activity;
import com.horizon.android.ActivityScope;
import com.horizon.android.model.ContentListImpl;
import com.horizon.android.model.UserInfoImpl;
import com.horizon.android.model.UserLoginImpl;
import com.horizon.android.model.WeatherImpl;
import com.horizon.android.presenter.ContentListPresenter;
import com.horizon.android.presenter.HomePresenter;
import com.horizon.android.presenter.UserInfoPresenter;
import com.horizon.android.presenter.UserLoginPresenter;
import com.horizon.android.presenter.WeatherPersenter;
import com.horizon.android.view.ContentListView;
import com.horizon.android.view.HomeView;
import com.horizon.android.view.UserInfoView;
import com.horizon.android.view.UserLoginView;
import com.horizon.android.view.WeatherView;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity =  activity;
    }

    @ActivityScope
    @Provides
    public Activity provideActivity(){
        return activity;
    }

    @ActivityScope
    @Provides
    public HomePresenter provideHomePresenter(){
        return new HomePresenter((HomeView) activity, new UserInfoImpl());
    }

    @ActivityScope
    @Provides
    public UserLoginPresenter provideUserLoginPresenter(){
        return new UserLoginPresenter((UserLoginView) activity, new UserLoginImpl());
    }

    @ActivityScope
    @Provides
    public UserInfoPresenter provideUserInfoPresenter(){
        return new UserInfoPresenter((UserInfoView) activity, new UserInfoImpl());
    }

    @ActivityScope
    @Provides
    public ContentListPresenter provideContentListPresenter(){
        return new ContentListPresenter((ContentListView) activity, new ContentListImpl());
    }

    @ActivityScope
    @Provides
    public WeatherPersenter provideWeatherPersenter(){
        return new WeatherPersenter(new WeatherImpl(), (WeatherView) activity);
    }
}
