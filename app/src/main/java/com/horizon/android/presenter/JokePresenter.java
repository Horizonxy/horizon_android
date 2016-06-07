package com.horizon.android.presenter;

import com.horizon.android.model.bean.JokeVo;
import com.horizon.android.model.interfaces.JokeInterface;
import com.horizon.android.simple.SimpleSubscriber;
import com.horizon.android.util.JuheResult;
import com.horizon.android.util.LogUtils;
import com.horizon.android.view.JokeView;

import rx.Subscription;

public class JokePresenter {

    JokeInterface mJoke;
    JokeView vJoke;

    public JokePresenter(JokeInterface mJoke, JokeView vJoke) {
        this.mJoke = mJoke;
        this.vJoke = vJoke;
    }

    public void getJokeList(){
        Subscription subscription = mJoke.getJokeList(vJoke.getTime(), vJoke.getPageNo(), new SimpleSubscriber<JuheResult<JokeVo>>(){
            @Override
            public void onError(Throwable e) {
                if(e != null){
                    e.printStackTrace();
                }
                vJoke.failure();
            }

            @Override
            public void onNext(JuheResult<JokeVo> obj) {
                vJoke.success(obj.getResult().getData());
            }

            @Override
            public void onCompleted() {
                LogUtils.e("onCompleted");
            }

            @Override
            public void onStart() {
                LogUtils.e("onStart");
            }
        });
        vJoke.addSubscriberToComposite(subscription);
    }
}
