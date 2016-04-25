package com.horizon.android.model.interfaces;

import com.horizon.android.model.bean.WeatherVo;
import com.horizon.android.util.JuheResult;

import rx.Subscriber;

public interface WeatherInterface {

    void getWeather(String cityname, Subscriber<JuheResult<WeatherVo>> subscriber);

}
