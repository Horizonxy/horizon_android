package com.horizon.android.fragment.csdn;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.horizon.android.R;
import com.horizon.android.adapter.csdn.CsdnListAdapter;
import com.horizon.android.db.CommonDaoImpl;
import com.horizon.android.model.bean.CommonCacheVo;
import com.horizon.android.quickadapter.MultiItemTypeSupport;
import com.horizon.android.util.GsonUtils;
import com.horizon.android.util.NetUtils;
import com.horizon.android.widget.AutoLoadListView;
import com.horizon.android.widget.InitializeListView;
import com.zhy.bean.CommonException;
import com.zhy.bean.NewsItem;
import com.zhy.biz.NewsItemBiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsdnTypeListFragment extends Fragment {

    private static final String TYPE = "type";
    private int mType;

    private CommonDaoImpl mCommonDao;
    private NewsItemBiz mNewsItemBiz;

    private InitializeListView mListView;
    private int mPageNo;
    private ArrayList<NewsItem> mData;
    private CsdnListAdapter mAdapter;

    private Map<String, Object> mCahceMap = new HashMap<String, Object>();
    private String ATY = "csdn_news_";
    private static final String DATA_TYPE = "csdn_news_list";

    public static CsdnTypeListFragment newInstance(int type) {
        CsdnTypeListFragment fragment = new CsdnTypeListFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getInt(TYPE);
        }
        ATY = ATY + mType;

        mPageNo = 1;
        mCommonDao = new CommonDaoImpl(getContext(), CommonCacheVo.class);
        mNewsItemBiz = new NewsItemBiz();

        mCahceMap.put(CommonCacheVo.ATY, ATY);
        mCahceMap.put(CommonCacheVo.DATA_TYPE, DATA_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_csdn_type_list, container, false);

        mListView = (InitializeListView) view.findViewById(R.id.news_page_list);
        mListView.setAutoLoadListener(new AutoLoadListener());
        mListView.setDivider(getResources().getColor(R.color.background), 1);
        mListView.setAdapter(mAdapter = new CsdnListAdapter(getContext(), mData = new ArrayList<NewsItem>(), new MultiItemTypeSupport<NewsItem>() {
            @Override
            public int getLayoutId(int position, NewsItem newsItem) {
                if(TextUtils.isEmpty(newsItem.getImgLink())){
                    return R.layout.item_csdn_news_list_no_banner;
                } else {
                    return R.layout.item_csdn_news_list;
                }
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int postion, NewsItem newsItem) {
                if(TextUtils.isEmpty(newsItem.getImgLink())){
                    return -1;
                } else {
                    return 1;
                }
            }
        }));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        new LoadDataTask().execute();
    }

    class AutoLoadListener implements AutoLoadListView.OnAutoLoadListener {

        @Override
        public void onLoading() {
            new LoadDataTask().execute();
        }
    }

    class LoadDataTask extends AsyncTask<Void, Void, Integer>{

        @Override
        protected Integer doInBackground(Void... params) {
            getNewsList();
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            mListView.onComplete();
        }
    }

    private void getNewsList() {
        try {
            if(mPageNo == 1){
                mData.clear();
            }

            if(NetUtils.isNetworkConnected(getContext())) {
                List<NewsItem> list = mNewsItemBiz.getNewsItems(mType, mPageNo);
                if (list != null) {
                    mData.addAll(list);
                }

                if (mPageNo == 1) {
                    Map<String, Object> deleteMap = new HashMap<String, Object>();
                    deleteMap.put(CommonCacheVo.ATY, ATY);
                    deleteMap.put(CommonCacheVo.DATA_TYPE, DATA_TYPE);
                    mCommonDao.deleteByColumns(deleteMap);
                }

                CommonCacheVo cacheVo = new CommonCacheVo();
                cacheVo.setAty(ATY);
                cacheVo.setData_page_no(mPageNo);
                cacheVo.setData_type(DATA_TYPE);
                cacheVo.setData(GsonUtils.getString(list));
                mCommonDao.save(cacheVo);
            } else {
                mCahceMap.put(CommonCacheVo.DATA_PAGE_NO, mPageNo);
                List<CommonCacheVo> cacheList = mCommonDao.findByColumns(mCahceMap);
                if(cacheList !=  null && cacheList.size() > 0){
                    CommonCacheVo cache = cacheList.get(0);
                    List<NewsItem> list = GsonUtils.getList(cache.getData(), NewsItem.class);
                    if (list != null) {
                        mData.addAll(list);
                    }
                }
            }
            mPageNo++;
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

}
