package com.horizon.android.activity;

import com.horizon.android.presenter.UserLoginPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MVPLoginActivity_MembersInjector implements MembersInjector<MVPLoginActivity> {
  private final MembersInjector<BaseActivity> supertypeInjector;
  private final Provider<UserLoginPresenter> mUserLoginPersenterProvider;

  public MVPLoginActivity_MembersInjector(MembersInjector<BaseActivity> supertypeInjector, Provider<UserLoginPresenter> mUserLoginPersenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mUserLoginPersenterProvider != null;
    this.mUserLoginPersenterProvider = mUserLoginPersenterProvider;
  }

  @Override
  public void injectMembers(MVPLoginActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mUserLoginPersenter = mUserLoginPersenterProvider.get();
  }

  public static MembersInjector<MVPLoginActivity> create(MembersInjector<BaseActivity> supertypeInjector, Provider<UserLoginPresenter> mUserLoginPersenterProvider) {  
      return new MVPLoginActivity_MembersInjector(supertypeInjector, mUserLoginPersenterProvider);
  }
}

