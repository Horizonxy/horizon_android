package com.horizon.android.activity;

import com.horizon.android.presenter.WeatherPersenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class WeatherActivity_MembersInjector implements MembersInjector<WeatherActivity> {
  private final MembersInjector<BaseActivity> supertypeInjector;
  private final Provider<WeatherPersenter> persenterProvider;

  public WeatherActivity_MembersInjector(MembersInjector<BaseActivity> supertypeInjector, Provider<WeatherPersenter> persenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert persenterProvider != null;
    this.persenterProvider = persenterProvider;
  }

  @Override
  public void injectMembers(WeatherActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.persenter = persenterProvider.get();
  }

  public static MembersInjector<WeatherActivity> create(MembersInjector<BaseActivity> supertypeInjector, Provider<WeatherPersenter> persenterProvider) {  
      return new WeatherActivity_MembersInjector(supertypeInjector, persenterProvider);
  }
}

