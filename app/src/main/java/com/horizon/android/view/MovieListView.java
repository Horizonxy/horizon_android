package com.horizon.android.view;

import com.horizon.android.model.bean.MovieVo;

import java.util.List;

import rx.Subscription;

public interface MovieListView {

    void movieList(List<MovieVo> movies);

    void error(String error);

    String getMovieTitle();

    void addSubscriptionToComposiote(Subscription subscription);
}
