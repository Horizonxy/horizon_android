package com.horizon.android.activity;

import android.os.Bundle;

import com.horizon.android.R;
import com.horizon.android.adapter.JokeAdapter;
import com.horizon.android.component.DaggerJokeComponent;
import com.horizon.android.model.bean.JokeVo;
import com.horizon.android.module.JokeModule;
import com.horizon.android.presenter.JokePresenter;
import com.horizon.android.view.JokeView;
import com.horizon.android.widget.AutoLoadListView;
import com.horizon.android.widget.InitializeListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Subscription;

public class JokeActivity extends BaseActivity implements JokeView {

    @Bind(R.id.lv_joke)
    InitializeListView lvJoke;
    @Inject
    JokeAdapter adapter;
    @Inject
    JokePresenter presenter;

    private List<JokeVo.JokeData> data;
    private int pageNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        setTitle("笑一笑");
        DaggerJokeComponent.builder().jokeModule(new JokeModule(this, R.layout.item_joke, data = new ArrayList<JokeVo.JokeData>())).build().inject(this);

        lvJoke.setDivider(getRes().getColor(R.color.background), 2);
        lvJoke.setAdapter(adapter);
        lvJoke.setAutoLoadListener(new AutoLoadListView.OnAutoLoadListener() {

            @Override
            public void onLoading() {
                presenter.getJokeList();
            }
        });

        pageNo = 1;
        presenter.getJokeList();
    }

    @Override
    public void success(List<JokeVo.JokeData> list) {
        data.addAll(list);

        lvJoke.onComplete();

        pageNo++;
    }


    @Override
    public void failure() {
        lvJoke.onFailuer();
    }

    @Override
    public String getTime() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    @Override
    public int getPageNo() {
        return pageNo;
    }

    @Override
    public void addSubscriberToComposite(Subscription subscription) {
        super.addSubscription(subscription);
    }
}
