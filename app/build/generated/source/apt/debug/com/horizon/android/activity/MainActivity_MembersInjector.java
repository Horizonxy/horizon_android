package com.horizon.android.activity;

import com.horizon.android.presenter.HomePresenter;
import com.zhy.autolayout.AutoLayoutActivity;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final MembersInjector<AutoLayoutActivity> supertypeInjector;
  private final Provider<HomePresenter> presenterProvider;

  public MainActivity_MembersInjector(MembersInjector<AutoLayoutActivity> supertypeInjector, Provider<HomePresenter> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(MainActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<MainActivity> create(MembersInjector<AutoLayoutActivity> supertypeInjector, Provider<HomePresenter> presenterProvider) {  
      return new MainActivity_MembersInjector(supertypeInjector, presenterProvider);
  }
}

