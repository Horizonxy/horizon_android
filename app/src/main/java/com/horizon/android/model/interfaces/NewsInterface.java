package com.horizon.android.model.interfaces;

import com.horizon.android.model.bean.NewsVo;
import com.horizon.android.util.JuheResult;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

public interface NewsInterface {

    public Subscription getNews(String q, Subscriber<JuheResult<List<NewsVo>>> subscriber);
}
