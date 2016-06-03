package com.horizon.android.activity;

import android.os.Bundle;
import android.os.Handler;

import com.horizon.android.R;
import com.horizon.android.adapter.JokeAdapter;
import com.horizon.android.component.DaggerJokeComponent;
import com.horizon.android.db.CommonDaoImpl;
import com.horizon.android.model.bean.CommonCacheVo;
import com.horizon.android.model.bean.JokeVo;
import com.horizon.android.module.JokeModule;
import com.horizon.android.presenter.JokePresenter;
import com.horizon.android.util.GsonUtils;
import com.horizon.android.util.NetUtils;
import com.horizon.android.view.JokeView;
import com.horizon.android.widget.AutoLoadListView;
import com.horizon.android.widget.InitializeListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Inject
    CommonDaoImpl dbDao;

    private HashMap<String, Object> daoMap = new HashMap<String, Object>();
    private final String ATY = "joke_activity";
    private final String DATA_TYPE = "joke_list";
    private Handler handler = new Handler();

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
                onLoad();
            }
        });

        daoMap.put(CommonCacheVo.ATY, ATY);
        daoMap.put(CommonCacheVo.DATA_TYPE, DATA_TYPE);

        pageNo = 1;
        onLoad();
    }

    private void onLoad(){
        if(pageNo == 1){
            data.clear();
        }
        if(NetUtils.isNetworkConnected(this)){
            presenter.getJokeList();
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //加载缓存
                    daoMap.put(CommonCacheVo.DATA_PAGE_NO, pageNo);
                    List<CommonCacheVo> commonlist = dbDao.findByColumns(daoMap);
                    if(!commonlist.isEmpty()){
                        List<JokeVo.JokeData> list = GsonUtils.getList(commonlist.get(0).getData(), JokeVo.JokeData.class);
                        data.addAll(list);

                        pageNo++;
                    } else if(pageNo > 1) {
                        lvJoke.onFinish();
                        return;
                    }

                    lvJoke.onComplete();
                }
            }, 500);
        }
    }

    @Override
    public void success(List<JokeVo.JokeData> list) {
        data.addAll(list);
        lvJoke.onComplete();

        if(pageNo == 1){
            try {
                dbDao.deleteAll(CommonCacheVo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        CommonCacheVo cacheVo = new CommonCacheVo();
        cacheVo.setAty(ATY);
        cacheVo.setData_page_no(pageNo);
        cacheVo.setData_type(DATA_TYPE);
        cacheVo.setData(GsonUtils.getString(list));
        dbDao.save(cacheVo);

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
