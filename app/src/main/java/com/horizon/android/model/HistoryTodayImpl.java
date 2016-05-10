package com.horizon.android.model;

import com.horizon.android.api.ApiManager;
import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.model.interfaces.HistoryTodayInterface;
import com.horizon.android.util.JuheResult;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HistoryTodayImpl implements HistoryTodayInterface {

    @Override
    public Subscription getHistoryToday(int month, int day, Subscriber<JuheResult<List<HistoryVo>>> subscriber) {
        return ApiManager.getHistoryToday(month, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
