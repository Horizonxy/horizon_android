package com.horizon.android.adapter;

import android.content.Context;

import com.horizon.android.R;
import com.horizon.android.model.bean.NewsVo;
import com.horizon.android.quickadapter.BaseAdapterHelper;
import com.horizon.android.quickadapter.QuickAdapter;

import java.util.List;

public class NewsAdapter extends QuickAdapter<NewsVo> {

    public NewsAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, NewsVo item) {
        helper.setText(R.id.item_title, item.getTitle());
    }

}
