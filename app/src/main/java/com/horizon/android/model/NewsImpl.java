package com.horizon.android.model;

import com.horizon.android.api.ApiManager;
import com.horizon.android.model.bean.NewsVo;
import com.horizon.android.model.interfaces.NewsInterface;
import com.horizon.android.util.JuheResult;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsImpl implements NewsInterface {
    @Override
    public Subscription getNews(String q, Subscriber<JuheResult<List<NewsVo>>> subscriber) {
        return ApiManager.getNews(q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
