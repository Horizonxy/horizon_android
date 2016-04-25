package com.horizon.android.module;

import com.horizon.android.presenter.UserLoginPresenter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ActivityModule_ProvideUserLoginPresenterFactory implements Factory<UserLoginPresenter> {
  private final ActivityModule module;

  public ActivityModule_ProvideUserLoginPresenterFactory(ActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public UserLoginPresenter get() {  
    UserLoginPresenter provided = module.provideUserLoginPresenter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<UserLoginPresenter> create(ActivityModule module) {  
    return new ActivityModule_ProvideUserLoginPresenterFactory(module);
  }
}

