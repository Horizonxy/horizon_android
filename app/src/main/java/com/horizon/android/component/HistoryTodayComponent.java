package com.horizon.android.component;


import com.horizon.android.ActivityScope;
import com.horizon.android.activity.HistoryTodayActivity;
import com.horizon.android.module.HistoryTodayMoudle;

import dagger.Component;

@ActivityScope
@Component(modules = HistoryTodayMoudle.class)
public interface HistoryTodayComponent {

    HistoryTodayActivity inject(HistoryTodayActivity activity);
}
