package com.horizon.android.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.horizon.android.R;
import com.horizon.android.adapter.recyclerview.BaseAdapterHelper;
import com.horizon.android.adapter.recyclerview.DividerGridItemDecoration;
import com.horizon.android.adapter.recyclerview.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class RecyclerViewActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setTitle("RecyclerView");

        data = new ArrayList<String >();
        for (int i = 0; i < 40; i++){
            data.add("recycler "+i);
        }
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setAdapter(new QuickAdapter<String>(this, R.layout.item_message, data) {
            @Override
            public void onBindData(BaseAdapterHelper holder, int position) {
                holder.setText(R.id.tv_message_text, data.get(position));
            }
        });
    }
}
