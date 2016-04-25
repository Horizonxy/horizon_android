package com.horizon.android.component;

import com.horizon.android.ActivityScope;
import com.horizon.android.activity.MovieListActivity;
import com.horizon.android.module.MovieListModule;
import dagger.Component;

@ActivityScope
@Component(modules = MovieListModule.class)
public interface MovieListActivityComponent {

    MovieListActivity inject(MovieListActivity activity);
}
