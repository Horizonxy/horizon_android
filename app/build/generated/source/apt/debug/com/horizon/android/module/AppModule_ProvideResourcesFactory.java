package com.horizon.android.module;

import android.content.res.Resources;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AppModule_ProvideResourcesFactory implements Factory<Resources> {
  private final AppModule module;

  public AppModule_ProvideResourcesFactory(AppModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Resources get() {  
    Resources provided = module.provideResources();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Resources> create(AppModule module) {  
    return new AppModule_ProvideResourcesFactory(module);
  }
}

