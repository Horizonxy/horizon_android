package com.horizon.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.horizon.android.R;
import com.horizon.android.adapter.MessageAdapter;
import com.horizon.android.component.DaggerAdapterComponent;
import com.horizon.android.module.AdapterModule;
import com.horizon.android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class ListViewActivity extends BaseActivity {

    @Bind(R.id.lv_listview)
    ListView lvListView;
    @Inject
    MessageAdapter adapter;

    List<String> data = new ArrayList<String>();
    int size = 20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        DaggerAdapterComponent.builder().adapterModule(new AdapterModule(this, R.layout.item_message, data)).build().inject(this);

        setTitle("MyListView");

        lvListView.setAdapter(adapter);

        for (int i = 0; i < size; i++) {
            data.add("message " + i);
        }
        adapter.notifyDataSetChanged();
    }
}
