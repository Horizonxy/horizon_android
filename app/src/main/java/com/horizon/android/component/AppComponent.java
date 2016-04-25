package com.horizon.android.component;

import android.content.res.Resources;

import com.horizon.android.Application;
import com.horizon.android.api.ApiService;
import com.horizon.android.module.ApiModule;
import com.horizon.android.module.AppModule;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class
})
public interface AppComponent {

    Application inject(Application application);

    Application getApplication();

    Resources getResources();

    ImageLoader getImageLoader();

    ApiService getApiService();

    DisplayImageOptions getOptions();
}
