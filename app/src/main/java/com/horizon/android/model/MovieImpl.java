package com.horizon.android.model;

import com.horizon.android.api.ApiManager;
import com.horizon.android.model.bean.MovieVo;
import com.horizon.android.model.interfaces.MovieInterface;
import com.horizon.android.util.JuheResult;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieImpl implements MovieInterface {

    @Override
    public void getMovies(String title,int pagesize, int offset, Subscriber<JuheResult<List<MovieVo>>> subscriber) {
        ApiManager.getMovies(title, pagesize, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
