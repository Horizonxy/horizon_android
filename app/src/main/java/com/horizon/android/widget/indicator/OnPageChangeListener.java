package com.horizon.android.widget.indicator;

public interface OnPageChangeListener {
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    public void onPageSelected(int position);
    public void onPageScrollStateChanged(int state);
}
