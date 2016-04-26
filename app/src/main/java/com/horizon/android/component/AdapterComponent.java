package com.horizon.android.component;

import com.horizon.android.ActivityScope;
import com.horizon.android.activity.AutoLoadActivity;
import com.horizon.android.activity.ListViewActivity;
import com.horizon.android.activity.PullToZoomListActivity;
import com.horizon.android.module.AdapterModule;
import com.horizon.android.ui.MessageView;
import dagger.Component;

@ActivityScope
@Component(modules = AdapterModule.class)
public interface AdapterComponent {

    MessageView inject(MessageView messageView);

    PullToZoomListActivity inject(PullToZoomListActivity activity);

    AutoLoadActivity inject(AutoLoadActivity  activity);

    ListViewActivity inject(ListViewActivity  activity);

}
