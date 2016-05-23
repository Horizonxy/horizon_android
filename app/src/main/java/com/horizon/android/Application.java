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
    private ImageLoader imageLoader;
    private List<Activity> atyList;
    private AppComponent component;
    private Resources res;

    public int SCREENWIDTH, SCREENHEIGHT;

    public ApiService apiService;

    private DisplayImageOptions defaultOptions;

    private List<String> resultOfRefresh;

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

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public AppComponent getAppComponent(){
        return component;
    }

    public Resources getRes(){
        return  res;
    }

    public void addRefresh(String action){
        if(resultOfRefresh == null){
            resultOfRefresh = new ArrayList<String>();
        }
        resultOfRefresh.add(action);
    }

    public void removeRefresh(String action){
        if(resultOfRefresh == null){
            return;
        } else {
            resultOfRefresh.remove(action);
        }
        if(resultOfRefresh.size() == 0){
            resultOfRefresh = null;
        }
    }
}
