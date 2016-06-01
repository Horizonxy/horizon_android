package com.horizon.android.component;

import com.horizon.android.ActivityScope;
import com.horizon.android.activity.JokeActivity;
import com.horizon.android.module.JokeModule;

import dagger.Component;

@ActivityScope
@Component(modules = JokeModule.class)
public interface JokeComponent {

    JokeActivity inject(JokeActivity activity);
}
