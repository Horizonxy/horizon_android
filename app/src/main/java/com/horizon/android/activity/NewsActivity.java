package com.horizon.android.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.horizon.android.R;
import com.horizon.android.ui.NewsPage;
import com.horizon.android.widget.indicator.RectViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

public class NewsActivity extends BaseActivity {

    @Bind(R.id.pager_indicator)
    RectViewPagerIndicator indicator;
    @Bind(R.id.new_pagers)
    ViewPager pagers;

    private List<NewsPage> pageViews;

    private static final String[] titles = {"娱乐", "热点", "体育", "科技", "财经"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setTitle("新闻");

        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<NewsPage>();
        for (String title : titles){
            NewsPage page = (NewsPage) inflater.inflate(R.layout.item_news_page, null);
            page.setTitle(title);
            page.startLoad();
            pageViews.add(page);
        }

        pagers.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                NewsPage view = pageViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView((View) object);
            }
        });
        indicator.setTabItemTitles(Arrays.asList(titles));
        indicator.setViewPager(pagers, 0);
    }
}
