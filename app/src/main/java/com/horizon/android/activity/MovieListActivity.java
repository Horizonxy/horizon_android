package com.horizon.android.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

import com.horizon.android.R;
import com.horizon.android.adapter.MovieListAdapter;
import com.horizon.android.component.ActivityComponent;
import com.horizon.android.component.AdapterComponent;
import com.horizon.android.component.DaggerActivityComponent;
import com.horizon.android.component.DaggerAdapterComponent;
import com.horizon.android.component.DaggerMovieListActivityComponent;
import com.horizon.android.component.MovieListActivityComponent;
import com.horizon.android.model.bean.MovieVo;
import com.horizon.android.module.ActivityModule;
import com.horizon.android.module.AdapterModule;
import com.horizon.android.module.MovieListModule;
import com.horizon.android.presenter.MoviePresenter;
import com.horizon.android.util.log.LogUtils;
import com.horizon.android.view.MovieListView;
import com.horizon.android.widget.AutoLoadListView;
import com.horizon.android.widget.InitializeListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MovieListActivity extends BaseActivity implements MovieListView {

    @Bind(R.id.lv_movie_list)
    InitializeListView lvMovieList;
    @Inject
    MoviePresenter presenter;
    @Inject
    MovieListAdapter adapter;

    List<MovieVo> data = new ArrayList<MovieVo>();

    final int PAGE_SIZE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list, true);
        DaggerMovieListActivityComponent.builder().movieListModule(new MovieListModule(this, R.layout.item_movie, data)).build().inject(this);
        setTitle("影讯");

        lvMovieList.setAdapter(adapter);
        lvMovieList.setAutoLoadListener(new AutoLoadListener());

        firstLoad();
    }

    private void firstLoad(){
        if(isNetworkAvailable()) {
            presenter.getMovies(PAGE_SIZE, 0);
        } else {
            noNet();
        }
    }

    @Override
    void clickRetry() {
        initBody();
        firstLoad();
    }

    class AutoLoadListener implements AutoLoadListView.OnAutoLoadListener {

        @Override
        public void onLoading() {
            presenter.getMovies(PAGE_SIZE, data.size());
        }
    }

    private void afterLoad(){
        if(data.size() == 0){
            initAfter();
        }
    }

    @Override
    public void movieList(List<MovieVo> movies) {
        afterLoad();

        if(movies != null){
            data.addAll(movies);
            lvMovieList.onComplete();
            if(movies.size() < PAGE_SIZE){
                lvMovieList.onFinish();
            }
        } else {
            lvMovieList.onFailuer();
        }
    }

    @Override
    public void error(String error) {
        afterLoad();

        lvMovieList.onFailuer();
    }

    @Override
    public String getMovieTitle() {
        return "哥斯拉";
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
