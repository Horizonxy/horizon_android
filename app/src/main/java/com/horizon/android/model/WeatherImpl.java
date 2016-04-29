package com.horizon.android.model;

import com.horizon.android.api.ApiManager;
import com.horizon.android.model.bean.WeatherVo;
import com.horizon.android.model.interfaces.WeatherInterface;
import com.horizon.android.util.JuheResult;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherImpl implements WeatherInterface {

    @Override
    public Subscription getWeather(String cityname, Subscriber<JuheResult<WeatherVo>> subscriber) {
        return ApiManager.getWeather(cityname)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
