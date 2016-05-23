package com.horizon.android.activity;

import java.util.Arrays;

import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.adapter.MessageAdapter;
import com.horizon.android.component.AdapterComponent;
import com.horizon.android.component.DaggerAdapterComponent;
import com.horizon.android.module.AdapterModule;
import com.horizon.android.widget.pulltozoom.PullToZoomListViewEx;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import javax.inject.Inject;
import butterknife.Bind;

public class PullToZoomListActivity extends BaseActivity {

    @Bind(R.id.listview)
    PullToZoomListViewEx listView;
    @Inject
    MessageAdapter adapter;

    AdapterComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_list_view);
        setTitle("ZoomList");

        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};

        component = DaggerAdapterComponent.builder().adapterModule(new AdapterModule(this,R.layout.item_message, Arrays.asList(adapterData))).build();
        component.inject(this);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("zhuwenwu", "position = " + position);
            }
        });

        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(Application.getInstance().SCREENWIDTH,
                (int) (9.0F * (Application.getInstance().SCREENWIDTH / 16.0F)));
        listView.setHeaderLayoutParams(localObject);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        component = null;
    }
}
