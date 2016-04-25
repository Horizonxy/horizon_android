package com.horizon.android.module;

import com.horizon.android.presenter.ContentListPresenter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ActivityModule_ProvideContentListPresenterFactory implements Factory<ContentListPresenter> {
  private final ActivityModule module;

  public ActivityModule_ProvideContentListPresenterFactory(ActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public ContentListPresenter get() {  
    ContentListPresenter provided = module.provideContentListPresenter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ContentListPresenter> create(ActivityModule module) {  
    return new ActivityModule_ProvideContentListPresenterFactory(module);
  }
}

