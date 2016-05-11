package com.horizon.android.activity;

import com.horizon.android.adapter.HistoryTodayAdapter;
import com.horizon.android.presenter.HistoryTodayPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class HistoryTodayActivity_MembersInjector implements MembersInjector<HistoryTodayActivity> {
  private final MembersInjector<BaseActivity> supertypeInjector;
  private final Provider<HistoryTodayPresenter> presenterProvider;
  private final Provider<HistoryTodayAdapter> adapterProvider;

  public HistoryTodayActivity_MembersInjector(MembersInjector<BaseActivity> supertypeInjector, Provider<HistoryTodayPresenter> presenterProvider, Provider<HistoryTodayAdapter> adapterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
    assert adapterProvider != null;
    this.adapterProvider = adapterProvider;
  }

  @Override
  public void injectMembers(HistoryTodayActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
    instance.adapter = adapterProvider.get();
  }

  public static MembersInjector<HistoryTodayActivity> create(MembersInjector<BaseActivity> supertypeInjector, Provider<HistoryTodayPresenter> presenterProvider, Provider<HistoryTodayAdapter> adapterProvider) {  
      return new HistoryTodayActivity_MembersInjector(supertypeInjector, presenterProvider, adapterProvider);
  }
}

