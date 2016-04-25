package com.horizon.android.module;

import com.horizon.android.presenter.WeatherPersenter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ActivityModule_ProvideWeatherPersenterFactory implements Factory<WeatherPersenter> {
  private final ActivityModule module;

  public ActivityModule_ProvideWeatherPersenterFactory(ActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public WeatherPersenter get() {  
    WeatherPersenter provided = module.provideWeatherPersenter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<WeatherPersenter> create(ActivityModule module) {  
    return new ActivityModule_ProvideWeatherPersenterFactory(module);
  }
}

