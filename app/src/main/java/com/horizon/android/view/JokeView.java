package com.horizon.android.view;

import com.horizon.android.model.bean.JokeVo;

import java.util.List;

import rx.Subscription;

public interface JokeView {
    void success(List<JokeVo.JokeData> list);
    void failure();
    String getTime();
    int getPageNo();
    void addSubscriberToComposite(Subscription subscription);
}
