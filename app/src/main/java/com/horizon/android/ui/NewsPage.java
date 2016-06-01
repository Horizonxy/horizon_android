package com.horizon.android.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.horizon.android.R;
import com.horizon.android.adapter.NewsAdapter;
import com.horizon.android.component.DaggerNewsComponent;
import com.horizon.android.model.bean.NewsVo;
import com.horizon.android.module.NewsModule;
import com.horizon.android.presenter.NewsPresenter;
import com.horizon.android.view.NewsView;
import com.horizon.android.widget.AutoLoadListView;
import com.horizon.android.widget.InitializeListView;
import com.zhy.autolayout.AutoFrameLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsPage extends AutoFrameLayout implements NewsView {
    private String title;

    private List<NewsVo> data;

    @Bind(R.id.item_news_page)
    InitializeListView listView;
    @Inject
    NewsPresenter presenter;
    @Inject
    NewsAdapter adapter;

    public NewsPage(Context context) {
        this(context ,null);
    }


    public NewsPage(Context context, AttributeSet attrs) {
        super(context, attrs);

        DaggerNewsComponent.builder().newsModule(new NewsModule(getContext(), this, R.layout.item_news_in_list, data = new ArrayList<NewsVo>())).build().inject(this);
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        listView.setAdapter(adapter);
        listView.setAutoLoadListener(new AutoLoadListener());
    }

    public void startLoad(){
        presenter.getNews();
    }

    class AutoLoadListener implements AutoLoadListView.OnAutoLoadListener{

        @Override
        public void onLoading() {
            presenter.getNews();
        }
    }

    @Override
    public void success(List<NewsVo> list) {
        data.addAll(list);
        listView.onComplete();
    }

    @Override
    public void failure() {
        listView.onFailuer();
    }

    @Override
    public void empty() {
        listView.onComplete();
    }

    @Override
    public String getWord() {
        return title;
    }

}
