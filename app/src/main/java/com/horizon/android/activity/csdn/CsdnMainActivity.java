package com.horizon.android.activity.csdn;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.horizon.android.R;
import com.horizon.android.activity.BaseActivity;
import com.horizon.android.adapter.csdn.CsdnTabAdapter;
import com.horizon.android.widget.indicator.RectViewPagerIndicator;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class CsdnMainActivity extends BaseActivity {

    @Bind(R.id.csdn_news)
    ViewPager mNewsPager;
    @Bind(R.id.pager_indicator)
    RectViewPagerIndicator mPagerIndicator;

    private CsdnTabAdapter mAdapter;
    private static final List<String> TITLES = Arrays.asList(new String[]{ "业界", "移动", "研发", "程序员杂志", "云计算" });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdn_main);
        setTitle("SCDN资讯");

        mNewsPager.setAdapter(mAdapter = new CsdnTabAdapter(TITLES, getSupportFragmentManager()));
        mPagerIndicator.setTabItemTitles(TITLES);
        mPagerIndicator.setViewPager(mNewsPager, 0);
    }
}
