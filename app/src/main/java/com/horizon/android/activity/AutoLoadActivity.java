package com.horizon.android.activity;

import android.os.Bundle;

import com.horizon.android.R;
import com.horizon.android.adapter.MessageAdapter;
import com.horizon.android.component.AdapterComponent;
import com.horizon.android.component.DaggerAdapterComponent;
import com.horizon.android.module.AdapterModule;
import com.horizon.android.widget.AutoLoadListView;
import com.horizon.android.widget.InitializeListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class AutoLoadActivity extends BaseActivity {

    @Bind(R.id.lv_autoload)
    InitializeListView mListView;
    @Inject
    MessageAdapter adapter;
    AdapterComponent component;

    List<String> data = new ArrayList<String>();
    int size = 20;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_autoload_listview);
        component = DaggerAdapterComponent.builder().adapterModule(new AdapterModule(this, R.layout.item_message, data)).build();
        component.inject(this);
        setTitle("autoload listview");

        mListView.setAdapter(adapter);
        mListView.setAutoLoadListener(new AutoLoadListener());

        for (int i = 0; i < size; i++) {
            data.add("message " + i);
        }
        mListView.onComplete();
    }

    class AutoLoadListener implements AutoLoadListView.OnAutoLoadListener {

        int max = 100;
        boolean failure = false;

        @Override
        public void onLoading() {
            if (data.size() == max) {
                mListView.onFinish();
                return;
            }

            if (data.size() == 60 && !failure) {
                mListView.onFailuer();
                failure = true;
                return;
            }

            for (int i = size; i < (size + 20); i++) {
                data.add("message " + i);
            }
            mListView.onComplete();
            size = data.size();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        component = null;
    }
}
