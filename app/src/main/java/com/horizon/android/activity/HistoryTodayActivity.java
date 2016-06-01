package com.horizon.android.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.horizon.android.R;
import com.horizon.android.adapter.HistoryTodayAdapter;
import com.horizon.android.component.DaggerHistoryTodayComponent;
import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.module.HistoryTodayModule;
import com.horizon.android.presenter.HistoryTodayPresenter;
import com.horizon.android.util.ToastUtils;
import com.horizon.android.view.HistoryTodayView;
import com.horizon.android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Subscription;

public class HistoryTodayActivity extends BaseLoadActivity implements HistoryTodayView {

    @Bind(R.id.lv_history)
    ListView lvHistory;
    @Inject
    HistoryTodayPresenter presenter;
    @Inject
    HistoryTodayAdapter adapter;

    List<HistoryVo> data = new ArrayList<HistoryVo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_today);
        DaggerHistoryTodayComponent.builder().historyTodayModule(new HistoryTodayModule(this, R.layout.item_history_today,data )).build().inject(this);
        setTitle("历史上的今天");

        lvHistory.setAdapter(adapter);

        firstLoad();
    }

    private void firstLoad(){
        if(isNetworkAvailable()) {
            data.clear();
            presenter.getHistoryToday();
        } else {
            noNet();
        }
    }

    @Override
    void clickRetry() {
        initBody();
        firstLoad();
    }

    private void afterLoad(){
        if(data.size() == 0){
            initAfter();
        }
    }

    @Override
    public void success(List<HistoryVo> list) {
        afterLoad();

        data.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void failure() {
        afterLoad();
        ToastUtils.show(this, "加载失败...");
    }

    @Override
    public void empty() {
        afterLoad();
        ToastUtils.show(this, "加载数据是空...");
    }

    @Override
    public int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    @Override
    public int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void addSubscriberToComposite(Subscription subscription) {
        addSubscription(subscription);
    }

    private boolean isNetworkAvailable() {
        // 得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // 去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            return manager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
