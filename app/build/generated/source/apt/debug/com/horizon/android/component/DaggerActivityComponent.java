package com.horizon.android.component;

import com.horizon.android.activity.MVPLoginActivity;
import com.horizon.android.activity.MVPLoginActivity_MembersInjector;
import com.horizon.android.activity.MVPUserInfoActivity;
import com.horizon.android.activity.MVPUserInfoActivity_MembersInjector;
import com.horizon.android.activity.MainActivity;
import com.horizon.android.activity.MainActivity_MembersInjector;
import com.horizon.android.activity.RetrofitRxJavaMvpContentActivity;
import com.horizon.android.activity.RetrofitRxJavaMvpContentActivity_MembersInjector;
import com.horizon.android.activity.WeatherActivity;
import com.horizon.android.activity.WeatherActivity_MembersInjector;
import com.horizon.android.module.ActivityModule;
import com.horizon.android.module.ActivityModule_ProvideContentListPresenterFactory;
import com.horizon.android.module.ActivityModule_ProvideHomePresenterFactory;
import com.horizon.android.module.ActivityModule_ProvideUserInfoPresenterFactory;
import com.horizon.android.module.ActivityModule_ProvideUserLoginPresenterFactory;
import com.horizon.android.module.ActivityModule_ProvideWeatherPersenterFactory;
import com.horizon.android.presenter.ContentListPresenter;
import com.horizon.android.presenter.HomePresenter;
import com.horizon.android.presenter.UserInfoPresenter;
import com.horizon.android.presenter.UserLoginPresenter;
import com.horizon.android.presenter.WeatherPersenter;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerActivityComponent implements ActivityComponent {
  private Provider<HomePresenter> provideHomePresenterProvider;
  private MembersInjector<MainActivity> mainActivityMembersInjector;
  private Provider<UserLoginPresenter> provideUserLoginPresenterProvider;
  private MembersInjector<MVPLoginActivity> mVPLoginActivityMembersInjector;
  private Provider<UserInfoPresenter> provideUserInfoPresenterProvider;
  private MembersInjector<MVPUserInfoActivity> mVPUserInfoActivityMembersInjector;
  private Provider<ContentListPresenter> provideContentListPresenterProvider;
  private MembersInjector<RetrofitRxJavaMvpContentActivity> retrofitRxJavaMvpContentActivityMembersInjector;
  private Provider<WeatherPersenter> provideWeatherPersenterProvider;
  private MembersInjector<WeatherActivity> weatherActivityMembersInjector;

  private DaggerActivityComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideHomePresenterProvider = ScopedProvider.create(ActivityModule_ProvideHomePresenterFactory.create(builder.activityModule));
    this.mainActivityMembersInjector = MainActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideHomePresenterProvider);
    this.provideUserLoginPresenterProvider = ScopedProvider.create(ActivityModule_ProvideUserLoginPresenterFactory.create(builder.activityModule));
    this.mVPLoginActivityMembersInjector = MVPLoginActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideUserLoginPresenterProvider);
    this.provideUserInfoPresenterProvider = ScopedProvider.create(ActivityModule_ProvideUserInfoPresenterFactory.create(builder.activityModule));
    this.mVPUserInfoActivityMembersInjector = MVPUserInfoActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideUserInfoPresenterProvider);
    this.provideContentListPresenterProvider = ScopedProvider.create(ActivityModule_ProvideContentListPresenterFactory.create(builder.activityModule));
    this.retrofitRxJavaMvpContentActivityMembersInjector = RetrofitRxJavaMvpContentActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideContentListPresenterProvider);
    this.provideWeatherPersenterProvider = ScopedProvider.create(ActivityModule_ProvideWeatherPersenterFactory.create(builder.activityModule));
    this.weatherActivityMembersInjector = WeatherActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideWeatherPersenterProvider);
  }

  @Override
  public MainActivity inject(MainActivity activity) {  
    mainActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  @Override
  public MVPLoginActivity inject(MVPLoginActivity activity) {  
    mVPLoginActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  @Override
  public MVPUserInfoActivity inject(MVPUserInfoActivity activity) {  
    mVPUserInfoActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  @Override
  public RetrofitRxJavaMvpContentActivity inject(RetrofitRxJavaMvpContentActivity activity) {  
    retrofitRxJavaMvpContentActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  @Override
  public WeatherActivity inject(WeatherActivity activity) {  
    weatherActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  public static final class Builder {
    private ActivityModule activityModule;
  
    private Builder() {  
    }
  
    public ActivityComponent build() {  
      if (activityModule == null) {
        throw new IllegalStateException("activityModule must be set");
      }
      return new DaggerActivityComponent(this);
    }
  
    public Builder activityModule(ActivityModule activityModule) {  
      if (activityModule == null) {
        throw new NullPointerException("activityModule");
      }
      this.activityModule = activityModule;
      return this;
    }
  }
}

