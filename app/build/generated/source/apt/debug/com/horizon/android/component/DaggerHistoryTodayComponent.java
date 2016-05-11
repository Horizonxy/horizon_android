package com.horizon.android.component;

import com.horizon.android.activity.HistoryTodayActivity;
import com.horizon.android.activity.HistoryTodayActivity_MembersInjector;
import com.horizon.android.adapter.HistoryTodayAdapter;
import com.horizon.android.module.HistoryTodayMoudle;
import com.horizon.android.module.HistoryTodayMoudle_ProvideHistoryTodayAdapterFactory;
import com.horizon.android.module.HistoryTodayMoudle_ProvideHistoryTodayPresenterFactory;
import com.horizon.android.presenter.HistoryTodayPresenter;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerHistoryTodayComponent implements HistoryTodayComponent {
  private Provider<HistoryTodayPresenter> provideHistoryTodayPresenterProvider;
  private Provider<HistoryTodayAdapter> provideHistoryTodayAdapterProvider;
  private MembersInjector<HistoryTodayActivity> historyTodayActivityMembersInjector;

  private DaggerHistoryTodayComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideHistoryTodayPresenterProvider = ScopedProvider.create(HistoryTodayMoudle_ProvideHistoryTodayPresenterFactory.create(builder.historyTodayMoudle));
    this.provideHistoryTodayAdapterProvider = ScopedProvider.create(HistoryTodayMoudle_ProvideHistoryTodayAdapterFactory.create(builder.historyTodayMoudle));
    this.historyTodayActivityMembersInjector = HistoryTodayActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideHistoryTodayPresenterProvider, provideHistoryTodayAdapterProvider);
  }

  @Override
  public HistoryTodayActivity inject(HistoryTodayActivity activity) {  
    historyTodayActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  public static final class Builder {
    private HistoryTodayMoudle historyTodayMoudle;
  
    private Builder() {  
    }
  
    public HistoryTodayComponent build() {  
      if (historyTodayMoudle == null) {
        throw new IllegalStateException("historyTodayMoudle must be set");
      }
      return new DaggerHistoryTodayComponent(this);
    }
  
    public Builder historyTodayMoudle(HistoryTodayMoudle historyTodayMoudle) {  
      if (historyTodayMoudle == null) {
        throw new NullPointerException("historyTodayMoudle");
      }
      this.historyTodayMoudle = historyTodayMoudle;
      return this;
    }
  }
}

