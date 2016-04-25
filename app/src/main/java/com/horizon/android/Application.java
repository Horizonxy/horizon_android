package com.horizon.android;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.horizon.android.api.ApiService;
import com.horizon.android.component.AppComponent;
import com.horizon.android.component.DaggerAppComponent;
import com.horizon.android.module.AppModule;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class Application extends android.app.Application {

    private static Application application;
    private static ImageLoader imageLoader;
    private List<Activity> atyList;
    private static AppComponent component;
    private static Resources res;

    public static int SCREENWIDTH, SCREENHEIGHT;

    public ApiService apiService;

    private DisplayImageOptions defaultOptions;

    @Override
    public void onCreate() {
        super.onCreate();

        atyList = new ArrayList<Activity>();
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        component.inject(this);

        application = component.getApplication();
        imageLoader = component.getImageLoader();
        res = component.getResources();
        apiService = component.getApiService();
        defaultOptions = component.getOptions();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        SCREENHEIGHT = displayMetrics.heightPixels;
        SCREENWIDTH = displayMetrics.widthPixels;
    }

    public static Application getInstance(){
        if (application == null){
            application = new Application();
        }
        return application;
    }

    public void addAty(Activity aty){
        atyList.add(aty);
    }

    public void removeAty(Activity aty){
        atyList.remove(aty);
    }

    public void exit(){
        for (Activity aty : atyList) {
            aty.finish();
        }
        System.exit(0);
    }

    public DisplayImageOptions getDefaultOptions() {
        return defaultOptions;
    }

    public List<Activity> getAtyList() {
        return atyList;
    }

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }

    public static AppComponent getAppComponent(){
        return component;
    }

    public static Resources getRes(){
        return  res;
    }
}
