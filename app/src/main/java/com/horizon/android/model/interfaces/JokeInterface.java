package com.horizon.android.model.interfaces;

import com.horizon.android.model.bean.JokeVo;
import com.horizon.android.util.JuheResult;

import rx.Subscriber;
import rx.Subscription;

public interface JokeInterface {

    Subscription getJokeList(String time, int pageNo, Subscriber<JuheResult<JokeVo>> subscriber);

}
