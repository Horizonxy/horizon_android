package com.horizon.android.presenter;

import com.horizon.android.model.bean.WeatherVo;
import com.horizon.android.model.interfaces.WeatherInterface;
import com.horizon.android.util.JuheResult;
import com.horizon.android.util.SimpleSubscriber;
import com.horizon.android.view.WeatherView;

public class WeatherPersenter {

    WeatherInterface mWeather;
    WeatherView vWeather;

    public WeatherPersenter(WeatherInterface mWeather, WeatherView vWeather){
        this.mWeather = mWeather;
        this.vWeather = vWeather;
    }

    public void getWeather(){
        mWeather.getWeather(vWeather.getCityName(), new SimpleSubscriber<JuheResult<WeatherVo>>(){

            @Override
            public void onNext(JuheResult<WeatherVo> obj) {
                vWeather.success(obj.getResult());
            }

            @Override
            public void onError(Throwable e) {
                vWeather.failure(e.getMessage());
            }
        });
    }

}
