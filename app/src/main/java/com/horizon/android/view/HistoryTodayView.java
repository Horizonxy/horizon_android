package com.horizon.android.view;

import com.horizon.android.model.bean.HistoryVo;

import java.util.List;

import rx.Subscription;

public interface HistoryTodayView {

    void success(List<HistoryVo> list);
    void failure();
    void empty();
    int getMonth();
    int getDay();

    void addSubscriberToComposite(Subscription subscription);
}
