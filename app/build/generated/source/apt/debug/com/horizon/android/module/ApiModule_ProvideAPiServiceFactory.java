package com.horizon.android.module;

import com.horizon.android.api.ApiService;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit.Retrofit;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_ProvideAPiServiceFactory implements Factory<ApiService> {
  private final ApiModule module;
  private final Provider<Retrofit> retrofitProvider;

  public ApiModule_ProvideAPiServiceFactory(ApiModule module, Provider<Retrofit> retrofitProvider) {  
    assert module != null;
    this.module = module;
    assert retrofitProvider != null;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ApiService get() {  
    ApiService provided = module.provideAPiService(retrofitProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ApiService> create(ApiModule module, Provider<Retrofit> retrofitProvider) {  
    return new ApiModule_ProvideAPiServiceFactory(module, retrofitProvider);
  }
}

