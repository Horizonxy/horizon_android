package com.horizon.android.module;

import android.app.Activity;

import com.horizon.android.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity =  activity;
    }

    @ActivityScope
    @Provides
    public Activity provideActivity(){
        return activity;
    }

}
