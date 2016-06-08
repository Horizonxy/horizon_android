package com.horizon.android.adapter.csdn;

import android.content.Context;

import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.quickadapter.BaseAdapterHelper;
import com.horizon.android.quickadapter.MultiItemTypeSupport;
import com.horizon.android.quickadapter.QuickAdapter;
import com.zhy.bean.NewsItem;

import java.util.ArrayList;

public class CsdnListAdapter extends QuickAdapter<NewsItem> {

    public CsdnListAdapter(Context context, ArrayList<NewsItem> data, MultiItemTypeSupport<NewsItem> multiItemSupport) {
        super(context, data, multiItemSupport);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, NewsItem item) {

        switch (helper.layoutId){
            case R.layout.item_csdn_news_list:
                helper.setText(R.id.tv_title, item.getTitle())
                        .setText(R.id.tv_content, item.getContent())
                        .setText(R.id.tv_time, item.getDate());
                helper.setImageBuilder(R.id.iv_banner, item.getImgLink(), Application.getInstance().getDefaultOptions());
                break;
            case R.layout.item_csdn_news_list_no_banner:
                helper.setText(R.id.tv_title_no_banner, item.getTitle())
                        .setText(R.id.tv_content_no_banner, item.getContent())
                        .setText(R.id.tv_time_no_banner, item.getDate());
                break;
        }
    }
}
