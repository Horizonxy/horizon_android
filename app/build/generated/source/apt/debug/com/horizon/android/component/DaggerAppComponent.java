package com.horizon.android.component;

import android.content.res.Resources;
import com.horizon.android.Application;
import com.horizon.android.api.ApiService;
import com.horizon.android.module.ApiModule;
import com.horizon.android.module.ApiModule_ProvideAPiServiceFactory;
import com.horizon.android.module.ApiModule_ProvideRetrofiFactory;
import com.horizon.android.module.AppModule;
import com.horizon.android.module.AppModule_ProvideApplicationFactory;
import com.horizon.android.module.AppModule_ProvideDisplayImageOptionsFactory;
import com.horizon.android.module.AppModule_ProvideImageLoaderFactory;
import com.horizon.android.module.AppModule_ProvideResourcesFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit.Retrofit;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerAppComponent implements AppComponent {
  private Provider<Application> provideApplicationProvider;
  private Provider<Resources> provideResourcesProvider;
  private Provider<ImageLoader> provideImageLoaderProvider;
  private Provider<Retrofit> provideRetrofiProvider;
  private Provider<ApiService> provideAPiServiceProvider;
  private Provider<DisplayImageOptions> provideDisplayImageOptionsProvider;

  private DaggerAppComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideApplicationProvider = ScopedProvider.create(AppModule_ProvideApplicationFactory.create(builder.appModule));
    this.provideResourcesProvider = ScopedProvider.create(AppModule_ProvideResourcesFactory.create(builder.appModule));
    this.provideImageLoaderProvider = ScopedProvider.create(AppModule_ProvideImageLoaderFactory.create(builder.appModule));
    this.provideRetrofiProvider = ScopedProvider.create(ApiModule_ProvideRetrofiFactory.create(builder.apiModule));
    this.provideAPiServiceProvider = ScopedProvider.create(ApiModule_ProvideAPiServiceFactory.create(builder.apiModule, provideRetrofiProvider));
    this.provideDisplayImageOptionsProvider = ScopedProvider.create(AppModule_ProvideDisplayImageOptionsFactory.create(builder.appModule));
  }

  @Override
  public Application inject(Application application) {  
    MembersInjectors.noOp().injectMembers(application);
    return application;
  }

  @Override
  public Application getApplication() {  
    return provideApplicationProvider.get();
  }

  @Override
  public Resources getResources() {  
    return provideResourcesProvider.get();
  }

  @Override
  public ImageLoader getImageLoader() {  
    return provideImageLoaderProvider.get();
  }

  @Override
  public ApiService getApiService() {  
    return provideAPiServiceProvider.get();
  }

  @Override
  public DisplayImageOptions getOptions() {  
    return provideDisplayImageOptionsProvider.get();
  }

  public static final class Builder {
    private AppModule appModule;
    private ApiModule apiModule;
  
    private Builder() {  
    }
  
    public AppComponent build() {  
      if (appModule == null) {
        throw new IllegalStateException("appModule must be set");
      }
      if (apiModule == null) {
        this.apiModule = new ApiModule();
      }
      return new DaggerAppComponent(this);
    }
  
    public Builder appModule(AppModule appModule) {  
      if (appModule == null) {
        throw new NullPointerException("appModule");
      }
      this.appModule = appModule;
      return this;
    }
  
    public Builder apiModule(ApiModule apiModule) {  
      if (apiModule == null) {
        throw new NullPointerException("apiModule");
      }
      this.apiModule = apiModule;
      return this;
    }
  }
}

