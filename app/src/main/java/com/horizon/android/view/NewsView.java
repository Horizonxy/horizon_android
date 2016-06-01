package com.horizon.android.view;

import com.horizon.android.model.bean.HistoryVo;
import com.horizon.android.model.bean.NewsVo;

import java.util.List;

public interface NewsView {
    void success(List<NewsVo> list);
    void failure();
    void empty();
    String getWord();
}
