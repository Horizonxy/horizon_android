package com.horizon.android.module;

import com.horizon.android.Constants;
import com.horizon.android.api.ApiService;
import com.horizon.android.util.RetrofitUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofi(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(RetrofitUtil.createOkHttpClient())
                .baseUrl(Constants.END_POIND)
                .addConverterFactory(GsonConverterFactory.create(RetrofitUtil.createGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    public ApiService provideAPiService(Retrofit retrofit){
        return  retrofit.create(ApiService.class);
    }

}
