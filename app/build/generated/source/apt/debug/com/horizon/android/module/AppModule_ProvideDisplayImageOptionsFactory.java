package com.horizon.android.module;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AppModule_ProvideDisplayImageOptionsFactory implements Factory<DisplayImageOptions> {
  private final AppModule module;

  public AppModule_ProvideDisplayImageOptionsFactory(AppModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public DisplayImageOptions get() {  
    DisplayImageOptions provided = module.provideDisplayImageOptions();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<DisplayImageOptions> create(AppModule module) {  
    return new AppModule_ProvideDisplayImageOptionsFactory(module);
  }
}

