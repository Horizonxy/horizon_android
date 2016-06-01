package com.horizon.android.module;


import android.content.Context;

import com.horizon.android.ActivityScope;
import com.horizon.android.adapter.JokeAdapter;
import com.horizon.android.model.JokeImpl;
import com.horizon.android.presenter.JokePresenter;
import com.horizon.android.view.JokeView;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class JokeModule {

    private Context context;
    private int layoutId;
    private List data;

    public JokeModule(Context context, int layoutId, List data) {
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    @ActivityScope
    @Provides
    public JokeAdapter provideJokeAdapter(){
        return new JokeAdapter(context, layoutId, data);
    }

    @ActivityScope
    @Provides
    public JokePresenter provideJokePresenter(){
        return  new JokePresenter(new JokeImpl(), (JokeView) context);
    }
}
