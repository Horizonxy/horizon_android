package com.horizon.android.module;

import com.nostra13.universalimageloader.core.ImageLoader;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AppModule_ProvideImageLoaderFactory implements Factory<ImageLoader> {
  private final AppModule module;

  public AppModule_ProvideImageLoaderFactory(AppModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public ImageLoader get() {  
    ImageLoader provided = module.provideImageLoader();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ImageLoader> create(AppModule module) {  
    return new AppModule_ProvideImageLoaderFactory(module);
  }
}

