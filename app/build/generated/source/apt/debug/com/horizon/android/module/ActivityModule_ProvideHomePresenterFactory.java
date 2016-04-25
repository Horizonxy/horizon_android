package com.horizon.android.module;

import com.horizon.android.presenter.HomePresenter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ActivityModule_ProvideHomePresenterFactory implements Factory<HomePresenter> {
  private final ActivityModule module;

  public ActivityModule_ProvideHomePresenterFactory(ActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public HomePresenter get() {  
    HomePresenter provided = module.provideHomePresenter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<HomePresenter> create(ActivityModule module) {  
    return new ActivityModule_ProvideHomePresenterFactory(module);
  }
}

