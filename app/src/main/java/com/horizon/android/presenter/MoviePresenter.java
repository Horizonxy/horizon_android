package com.horizon.android.presenter;

import com.horizon.android.model.bean.MovieVo;
import com.horizon.android.model.interfaces.MovieInterface;
import com.horizon.android.util.JuheResult;
import com.horizon.android.util.SimpleSubscriber;
import com.horizon.android.view.MovieListView;

import java.util.List;

import rx.Subscription;

public class MoviePresenter {

    MovieInterface mMovie;
    MovieListView vMovie;

    public MoviePresenter(MovieListView vMovie, MovieInterface mMovie){
        this.mMovie = mMovie;
        this.vMovie = vMovie;
    }

    public void getMovies(int pagesize, int offset){
        Subscription subscription = mMovie.getMovies(vMovie.getMovieTitle() ,pagesize, offset, new SimpleSubscriber<JuheResult<List<MovieVo>>>(){
            @Override
            public void onNext(JuheResult<List<MovieVo>> result) {
               vMovie.movieList(result.getResult());
            }

            @Override
            public void onError(Throwable e) {
                vMovie.error(e.getMessage());
            }
        });
        vMovie.addSubscriptionToComposiote(subscription);
    }
}
