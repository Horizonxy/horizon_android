package com.horizon.android.module;

import android.content.Context;

import com.horizon.android.ActivityScope;
import com.horizon.android.adapter.NewsAdapter;
import com.horizon.android.model.NewsImpl;
import com.horizon.android.presenter.NewsPresenter;
import com.horizon.android.view.NewsView;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    private Context context;
    private NewsView view;
    private int layoutId;
    private List data;

    public NewsModule(Context context, NewsView view, int layoutId, List data) {
        this.context= context;
        this.view = view;
        this.layoutId = layoutId;
        this.data = data;
    }

    @ActivityScope
    @Provides
    public NewsPresenter provideNewsPresenter(){
        return new NewsPresenter(new NewsImpl(), view);
    }

    @ActivityScope
    @Provides
    public NewsAdapter provideNewsAdapter(){
        return new NewsAdapter(context, layoutId, data);
    }

}
