package com.horizon.android.module;

import android.content.Context;

import com.horizon.android.ActivityScope;
import com.horizon.android.adapter.MovieListAdapter;
import com.horizon.android.model.MovieImpl;
import com.horizon.android.presenter.MoviePresenter;
import com.horizon.android.view.MovieListView;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {

    private Context context;
    private int layoutId;
    private List data;

    public MovieListModule(Context context, int layoutId, List data){
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    @ActivityScope
    @Provides
    public MovieListAdapter provideMovieListAdapter(){
        return new MovieListAdapter(context, layoutId, data);
    }

    @ActivityScope
    @Provides
    public MoviePresenter provideMoviePresenter(){
        return new MoviePresenter((MovieListView) context, new MovieImpl());
    }

}
