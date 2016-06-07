package com.horizon.android.presenter;


import com.horizon.android.model.bean.NewsVo;
import com.horizon.android.model.interfaces.NewsInterface;
import com.horizon.android.simple.SimpleSubscriber;
import com.horizon.android.util.JuheResult;
import com.horizon.android.view.NewsView;

import java.util.List;

import rx.Subscription;

public class NewsPresenter {

    NewsInterface mNews;
    NewsView vNews;

    public NewsPresenter(NewsInterface mNews, NewsView vNews){
        this.vNews = vNews;
        this.mNews = mNews;
    }

    public void getNews(){
        Subscription subscription = mNews.getNews(vNews.getWord(), new SimpleSubscriber<JuheResult<List<NewsVo>>>(){
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                vNews.failure();
            }

            @Override
            public void onNext(JuheResult<List<NewsVo>> obj) {
                vNews.success(obj.getResult());
            }
        });
    }
}
