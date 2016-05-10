package com.horizon.android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.horizon.android.R;
import com.horizon.android.adapter.HistoryTodayAdapter;
import com.horizon.android.component.DaggerActivityComponent;
import com.horizon.android.component.DaggerHistoryTodayComponent;
import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.module.ActivityModule;
import com.horizon.android.module.HistoryTodayMoudle;
import com.horizon.android.presenter.HistoryTodayPresenter;
import com.horizon.android.util.ToastUtils;
import com.horizon.android.view.HistoryTodayView;
import com.horizon.android.widget.ListView;
import com.horizon.android.widget.MonIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Subscription;

public class HistoryTodayActivity extends BaseActivity implements HistoryTodayView {

    @Bind(R.id.lv_history)
    ListView lvHistory;
    @Inject
    HistoryTodayPresenter presenter;
    @Inject
    HistoryTodayAdapter adapter;
    @Bind(R.id.mi_init)
    MonIndicator monIndicator;

    List<HistoryVo> data = new ArrayList<HistoryVo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_today);
        DaggerHistoryTodayComponent.builder().historyTodayMoudle(new HistoryTodayMoudle(this, R.layout.item_history_today,data )).build().inject(this);
        setTitle("历史上的今天");

        lvHistory.setAdapter(adapter);

        presenter.getHistoryToday();
    }

    @Override
    public void success(List<HistoryVo> list) {
        if(monIndicator.isShown()){
            lvHistory.setVisibility(View.VISIBLE);
            monIndicator.setVisibility(View.GONE);
        }
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void failure() {
        ToastUtils.show(this, "加载失败...");
    }

    @Override
    public void empty() {
        ToastUtils.show(this, "加载数据是空...");
    }

    @Override
    public int getMonth() {
        return 5;
    }

    @Override
    public int getDay() {
        return 10;
    }

    @Override
    public void addSubscriberToComposite(Subscription subscription) {
        addSubscription(subscription);
    }
}
