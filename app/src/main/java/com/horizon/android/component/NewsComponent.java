package com.horizon.android.component;

import com.horizon.android.ActivityScope;
import com.horizon.android.module.NewsModule;
import com.horizon.android.ui.NewsPage;

import dagger.Component;

@ActivityScope
@Component(modules = NewsModule.class)
public interface NewsComponent {
    NewsPage inject(NewsPage page);
}
