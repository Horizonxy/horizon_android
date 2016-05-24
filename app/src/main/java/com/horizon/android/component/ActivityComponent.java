package com.horizon.android.component;

import com.horizon.android.ActivityScope;
import com.horizon.android.activity.MainActivity;
import com.horizon.android.module.ActivityModule;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    MainActivity inject(MainActivity activity);
}
