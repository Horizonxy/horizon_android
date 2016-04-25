package com.horizon.android.module;

import com.horizon.android.presenter.UserInfoPresenter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ActivityModule_ProvideUserInfoPresenterFactory implements Factory<UserInfoPresenter> {
  private final ActivityModule module;

  public ActivityModule_ProvideUserInfoPresenterFactory(ActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public UserInfoPresenter get() {  
    UserInfoPresenter provided = module.provideUserInfoPresenter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<UserInfoPresenter> create(ActivityModule module) {  
    return new ActivityModule_ProvideUserInfoPresenterFactory(module);
  }
}

