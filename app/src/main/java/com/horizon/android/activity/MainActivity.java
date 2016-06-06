package com.horizon.android.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.component.ActivityComponent;
import com.horizon.android.component.DaggerActivityComponent;
import com.horizon.android.module.ActivityModule;
import com.horizon.android.util.SystemStatusManager;
import com.horizon.android.util.ToastUtils;
import com.horizon.android.widget.ChangeColorIcon;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AutoLayoutActivity {

    @Bind(R.id.vp_main)
    ViewPager vpMain;
    List<View> vpTab = new ArrayList<View>();
    LayoutInflater mInflater;
    List<ChangeColorIcon> mTabIndicator = new ArrayList<ChangeColorIcon>();

    @Bind(R.id.btn_home)
    ChangeColorIcon home;
    @Bind(R.id.btn_message)
    ChangeColorIcon message;
    @Bind(R.id.btn_shopping_car)
    ChangeColorIcon shoppingCar;
    @Bind(R.id.btn_user)
    ChangeColorIcon user;

    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.main);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Application.getInstance().addAty(this);
        component = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
        component.inject(this);

        if(Build.VERSION.SDK_INT < 21){
            home.setBackgroundColor(getResources().getColor(R.color.transparent));
            message.setBackgroundColor(getResources().getColor(R.color.transparent));
            shoppingCar.setBackgroundColor(getResources().getColor(R.color.transparent));
            user.setBackgroundColor(getResources().getColor(R.color.transparent));
        }

        mTabIndicator.add(home);
        mTabIndicator.add(message);
        mTabIndicator.add(shoppingCar);
        mTabIndicator.add(user);

        mInflater = getLayoutInflater();
        vpTab.add(mInflater.inflate(R.layout.view_home, null));
        vpTab.add(mInflater.inflate(R.layout.view_message, null));
        vpTab.add(mInflater.inflate(R.layout.view_shopping_car, null));
        vpTab.add(mInflater.inflate(R.layout.view_user, null));

        vpMain.setAdapter(new MainVPAdapter(vpTab));
        vpMain.setOnPageChangeListener(new MainVPChangeListener());

        home.performClick();
    }

    @OnClick(R.id.btn_home)
    void homeClick() {
        resetOtherTabs(0);
        mTabIndicator.get(0).setIconAlpha(1.0f);
        vpMain.setCurrentItem(0, false);
    }

    @OnClick(R.id.btn_message)
    void messageClick() {
        resetOtherTabs(1);
        mTabIndicator.get(1).setIconAlpha(1.0f);
        vpMain.setCurrentItem(1, false);
    }

    @OnClick(R.id.btn_shopping_car)
    void shoppingCarClick() {
        resetOtherTabs(2);
        mTabIndicator.get(2).setIconAlpha(1.0f);
        vpMain.setCurrentItem(2, false);
    }

    @OnClick(R.id.btn_user)
    void userClick() {
        resetOtherTabs(3);
        mTabIndicator.get(3).setIconAlpha(1.0f);
        vpMain.setCurrentItem(3, false);
    }

    /**
     * 重置其他的Tab
     */
    private void resetOtherTabs(int pos) {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            if (i != pos) {
                mTabIndicator.get(i).setIconAlpha(0);
            }
        }
    }

    class MainVPChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffset > 0) {
                ChangeColorIcon left = mTabIndicator.get(position);
                ChangeColorIcon right = mTabIndicator.get(position + 1);

                left.setIconAlpha(1 - positionOffset);
                right.setIconAlpha(positionOffset);
            }
        }

        @Override
        public void onPageSelected(int arg0) {
        }

    }

    class MainVPAdapter extends PagerAdapter {

        private List<View> list;

        public MainVPAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));

            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQ_SCAN:
                if (resultCode == RESULT_OK && data != null) {
                    String result = data.getStringExtra(Constants.BUNDLE_SCAN_RESULT);
                    ToastUtils.show(this, "扫描结果：" + result);
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        component = null;
        Application.getInstance().removeAty(this);
    }
}
