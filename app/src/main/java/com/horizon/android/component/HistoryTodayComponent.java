package com.horizon.android.component;


import com.horizon.android.ActivityScope;
import com.horizon.android.activity.HistoryTodayActivity;
import com.horizon.android.module.HistoryTodayModule;

import dagger.Component;

@ActivityScope
@Component(modules = HistoryTodayModule.class)
public interface HistoryTodayComponent {

    HistoryTodayActivity inject(HistoryTodayActivity activity);
}
