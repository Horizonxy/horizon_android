package com.horizon.android.adapter;

import android.content.Context;

import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.model.bean.MovieVo;
import com.horizon.android.quickadapter.BaseAdapterHelper;
import com.horizon.android.quickadapter.QuickAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

public class MovieListAdapter extends QuickAdapter<MovieVo> {

    DisplayImageOptions options;

    public MovieListAdapter(Context context, int layoutResId,
                          List<MovieVo> data) {
        super(context, layoutResId, data);
        options = Application.getInstance().getDefaultOptions();
    }

    @Override
    protected void convert(BaseAdapterHelper helper, MovieVo item) {
        helper.setImageBuilder(R.id.iv_logo, item.getPoster(), options)
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_release_date, item.getRelease_date())
                .setText(R.id.tv_actors, item.getActors())
                .setText(R.id.tv_language, item.getLanguage())
                .setText(R.id.tv_plot_simple, item.getPlot_simple())
                .setText(R.id.tv_directors, item.getDirectors())
                .setText(R.id.tv_country, item.getCountry());
    }
}
