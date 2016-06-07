package com.horizon.android.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.ui.ImageDetailView;
import com.horizon.android.util.SmallPicInfo;
import com.horizon.android.util.SystemStatusManager;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PicturesDetailActivity extends AutoLayoutActivity {

    @Bind(R.id.vp_pictures)
    ViewPager vpPictures;
    @Bind(R.id.fl_pictures)
    AutoRelativeLayout flPictures;

    private final static long DURATION = 250;
    private int pos;

    private PictureAdapter adapter;

    @Bind(R.id.indicator)
    LinearLayout indicator;
    SparseArray<ImageView> indicatorViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.transparent);
        setContentView(R.layout.activity_pictures_detail);
        ButterKnife.bind(this);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(flPictures, "alpha", 0f, 1.0f);
        alpha.setDuration(DURATION);
        alpha.start();

        final HashMap<Integer, SmallPicInfo> picInfos = (HashMap<Integer, SmallPicInfo>) getIntent().getSerializableExtra(Constants.BUNDLE_PIC_INFOS);
        pos = getIntent().getIntExtra(Constants.BUNDLE_PIC_POS, 0);

        vpPictures.setAdapter(adapter = new PictureAdapter(picInfos));
        if(picInfos.size() == 1){
            indicator.setVisibility(View.GONE);
        } else {
            indicatorViews = new SparseArray<ImageView>();
            AutoLinearLayout.LayoutParams lp = new AutoLinearLayout.LayoutParams(6, 6);
            lp.leftMargin = 5;
            lp.rightMargin = 5;
            for (int i = 0; i < picInfos.size(); i++){
                ImageView item = new ImageView(this);
                item.setBackgroundColor(getResources().getColor(R.color.transparent));
                item.setImageResource(R.mipmap.ic_page_indicator);
                item.setLayoutParams(lp);
                indicatorViews.put(i, item);
                indicator.addView(item);
            }
            vpPictures.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < picInfos.size(); i++){
                        ImageView item = indicatorViews.get(i);
                        if(i == position){
                            item.setImageResource(R.mipmap.ic_page_indicator_focused);
                        } else {
                            item.setImageResource(R.mipmap.ic_page_indicator);
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {}
            });
        }
        vpPictures.setCurrentItem(pos);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(0, 0);
    }

    class PictureAdapter extends PagerAdapter {

        private HashMap<Integer, SmallPicInfo> picInfos;
        private SparseArray<ImageDetailView> itemViews;

        public PictureAdapter(HashMap<Integer, SmallPicInfo> picInfos) {
            this.picInfos = picInfos;
            this.itemViews = new SparseArray<ImageDetailView>();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            ImageDetailView item = itemViews.get(position);
            if(item == null) {
                item = (ImageDetailView) getLayoutInflater().inflate(R.layout.item_picture_detail, null);
                itemViews.put(position, item);
                item.setLayoutParams(new ViewPager.LayoutParams());

                final SmallPicInfo smallPicInfo = picInfos.get(position);
                item.loadImage(smallPicInfo, pos == position);
            }
            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(itemViews.get(position));
        }

        @Override
        public int getCount() {
            return picInfos.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}

