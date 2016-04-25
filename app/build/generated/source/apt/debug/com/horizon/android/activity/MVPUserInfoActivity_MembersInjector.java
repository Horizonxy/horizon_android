package com.horizon.android.activity;

import com.horizon.android.presenter.UserInfoPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MVPUserInfoActivity_MembersInjector implements MembersInjector<MVPUserInfoActivity> {
  private final MembersInjector<BaseActivity> supertypeInjector;
  private final Provider<UserInfoPresenter> mUserInfoPersenterProvider;

  public MVPUserInfoActivity_MembersInjector(MembersInjector<BaseActivity> supertypeInjector, Provider<UserInfoPresenter> mUserInfoPersenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mUserInfoPersenterProvider != null;
    this.mUserInfoPersenterProvider = mUserInfoPersenterProvider;
  }

  @Override
  public void injectMembers(MVPUserInfoActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mUserInfoPersenter = mUserInfoPersenterProvider.get();
  }

  public static MembersInjector<MVPUserInfoActivity> create(MembersInjector<BaseActivity> supertypeInjector, Provider<UserInfoPresenter> mUserInfoPersenterProvider) {  
      return new MVPUserInfoActivity_MembersInjector(supertypeInjector, mUserInfoPersenterProvider);
  }
}

