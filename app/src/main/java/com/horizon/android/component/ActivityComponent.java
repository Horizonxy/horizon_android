package com.horizon.android.component;

import com.horizon.android.ActivityScope;
import com.horizon.android.activity.MVPLoginActivity;
import com.horizon.android.activity.MVPUserInfoActivity;
import com.horizon.android.activity.MainActivity;
import com.horizon.android.activity.RetrofitRxJavaMvpContentActivity;
import com.horizon.android.activity.WeatherActivity;
import com.horizon.android.module.ActivityModule;
import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    MainActivity inject(MainActivity activity);

    MVPLoginActivity inject(MVPLoginActivity activity);

    MVPUserInfoActivity inject(MVPUserInfoActivity activity);

    RetrofitRxJavaMvpContentActivity inject(RetrofitRxJavaMvpContentActivity activity);

    WeatherActivity inject(WeatherActivity activity);
}
