package com.horizon.android.module;

import dagger.internal.Factory;
import javax.annotation.Generated;
import retrofit.Retrofit;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ApiModule_ProvideRetrofiFactory implements Factory<Retrofit> {
  private final ApiModule module;

  public ApiModule_ProvideRetrofiFactory(ApiModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Retrofit get() {  
    Retrofit provided = module.provideRetrofi();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Retrofit> create(ApiModule module) {  
    return new ApiModule_ProvideRetrofiFactory(module);
  }
}

