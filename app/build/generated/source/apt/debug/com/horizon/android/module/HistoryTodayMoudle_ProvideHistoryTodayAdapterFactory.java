package com.horizon.android.module;

import com.horizon.android.adapter.HistoryTodayAdapter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class HistoryTodayMoudle_ProvideHistoryTodayAdapterFactory implements Factory<HistoryTodayAdapter> {
  private final HistoryTodayMoudle module;

  public HistoryTodayMoudle_ProvideHistoryTodayAdapterFactory(HistoryTodayMoudle module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public HistoryTodayAdapter get() {  
    HistoryTodayAdapter provided = module.provideHistoryTodayAdapter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<HistoryTodayAdapter> create(HistoryTodayMoudle module) {  
    return new HistoryTodayMoudle_ProvideHistoryTodayAdapterFactory(module);
  }
}

