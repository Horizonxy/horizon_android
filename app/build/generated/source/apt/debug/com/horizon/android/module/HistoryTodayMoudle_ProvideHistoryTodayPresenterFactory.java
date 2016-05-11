package com.horizon.android.module;

import com.horizon.android.presenter.HistoryTodayPresenter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class HistoryTodayMoudle_ProvideHistoryTodayPresenterFactory implements Factory<HistoryTodayPresenter> {
  private final HistoryTodayMoudle module;

  public HistoryTodayMoudle_ProvideHistoryTodayPresenterFactory(HistoryTodayMoudle module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public HistoryTodayPresenter get() {  
    HistoryTodayPresenter provided = module.provideHistoryTodayPresenter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<HistoryTodayPresenter> create(HistoryTodayMoudle module) {  
    return new HistoryTodayMoudle_ProvideHistoryTodayPresenterFactory(module);
  }
}

