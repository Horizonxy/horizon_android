package com.horizon.android.model;

import com.horizon.android.api.ApiManager;
import com.horizon.android.model.bean.JokeVo;
import com.horizon.android.model.interfaces.JokeInterface;
import com.horizon.android.util.JuheResult;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JokeImpl implements JokeInterface {

    @Override
    public Subscription getJokeList(String time, int pageNo, Subscriber<JuheResult<JokeVo>> subscriber) {
        return ApiManager.getJokeList(time, pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
