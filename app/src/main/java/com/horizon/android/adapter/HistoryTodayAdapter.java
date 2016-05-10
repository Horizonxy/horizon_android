package com.horizon.android.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.horizon.android.R;
import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.quickadapter.BaseAdapterHelper;
import com.horizon.android.quickadapter.QuickAdapter;

import java.util.List;

public class HistoryTodayAdapter extends QuickAdapter<HistoryVo> {

    public HistoryTodayAdapter(Context context, int layoutResId, List<HistoryVo> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, HistoryVo item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_des, item.getDes());
        if(!TextUtils.isEmpty(item.getPic())) {
            helper.setVisible(R.id.iv_pic, true).setImageUrl(R.id.iv_pic, item.getPic());
        } else {
            helper.setVisible(R.id.iv_pic, false);
        }
    }
}
