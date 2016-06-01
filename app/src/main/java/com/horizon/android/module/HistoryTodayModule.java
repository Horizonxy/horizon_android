package com.horizon.android.module;

import android.content.Context;

import com.horizon.android.ActivityScope;
import com.horizon.android.adapter.HistoryTodayAdapter;
import com.horizon.android.model.HistoryTodayImpl;
import com.horizon.android.presenter.HistoryTodayPresenter;
import com.horizon.android.view.HistoryTodayView;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class HistoryTodayModule {

    private Context context;
    private int layoutId;
    private List data;

    public HistoryTodayModule(Context context, int layoutId, List data) {
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    @ActivityScope
    @Provides
    public HistoryTodayAdapter provideHistoryTodayAdapter() {
        return new HistoryTodayAdapter(context, layoutId, data);
    }

    @ActivityScope
    @Provides
    public HistoryTodayPresenter provideHistoryTodayPresenter() {
        return new HistoryTodayPresenter(new HistoryTodayImpl(), (HistoryTodayView) context);
    }
}
