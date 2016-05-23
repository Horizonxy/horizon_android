package com.horizon.android.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horizon.android.R;
import com.horizon.android.widget.indicator.RectViewPagerIndicator;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class RectViewPagerIndicatorActivity extends BaseActivity {

    @Bind(R.id.vp_indicator)
    RectViewPagerIndicator vpIndicator;
    @Bind(R.id.vp_pager)
    ViewPager vpPager;

    private List<String> mDatas = Arrays.asList("短信1", "短信2", "短信3", "短信4","短信5", "短信6", "短信7", "短信8", "短信9");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rect_view_pager_indicator);
        setTitle("ViewPager矩形指示器");

        //设置关联的ViewPager
        vpIndicator.setViewPager(vpPager, 0);
        vpIndicator.setTabItemTitles(mDatas);
        vpPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return mDatas.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView tv = new TextView(RectViewPagerIndicatorActivity.this);
                tv.setText("text: "+position);
                container.addView(tv);
                return tv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

    }
}
