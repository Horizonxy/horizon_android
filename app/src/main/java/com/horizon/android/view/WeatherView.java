package com.horizon.android.view;

import com.horizon.android.model.bean.WeatherVo;

import rx.Subscription;

public interface WeatherView {

    String getCityName();

    void success(WeatherVo weather);

    void failure(String error);

    void addSubscriberToComposite(Subscription subscription);
}
