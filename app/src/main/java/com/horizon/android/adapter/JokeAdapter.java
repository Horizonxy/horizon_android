package com.horizon.android.adapter;

import android.content.Context;

import com.horizon.android.R;
import com.horizon.android.model.bean.JokeVo;
import com.horizon.android.quickadapter.BaseAdapterHelper;
import com.horizon.android.quickadapter.QuickAdapter;

import java.util.List;

public class JokeAdapter extends QuickAdapter<JokeVo.JokeData> {

    public JokeAdapter(Context context, int layoutResId, List<JokeVo.JokeData> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, JokeVo.JokeData item) {
        helper.setText(R.id.tv_time, item.getUpdatetime())
                .setText(R.id.tv_content, item.getContent());
    }
}
