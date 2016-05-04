package com.horizon.android.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class ScrollView extends android.widget.ScrollView {

    View mView;
    float touchY;
    boolean isMoving;
    int scrollY;
    int eachStep = 0;
    private int mMaxScrollHeight;// 最大滑动距离
    private static final float SCROLL_RATIO = 0.6f;// 阻尼系数,越小阻力就越大
    private int mScreenHeight;

    public ScrollView(Context context) {
        this(context, null);
    }

    public ScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mScreenHeight = displayMetrics.heightPixels;
        mMaxScrollHeight = mScreenHeight / 3;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mView != null) {
            int action = ev.getAction();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    touchY = ev.getY();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (mView.getScrollY() != 0) {
                        isMoving = true;
                        animation();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    float nowY = ev.getY();
                    int deltaY = (int) (touchY - nowY);
                    touchY = nowY;
                    if(isNeedMove()){
                        int offset = mView.getScrollY();
                        if (offset < mMaxScrollHeight && offset > -mMaxScrollHeight) {
                            mView.scrollBy(0, (int) (deltaY * SCROLL_RATIO));
                            isMoving = false;
                        }
                    }
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }


    private void animation() {
        scrollY = mView.getScrollY();
        eachStep = scrollY / 100;
        handler.sendEmptyMessage(0);
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (scrollY != 0 && isMoving) {
                scrollY -= eachStep;
                if ((eachStep < 0 && scrollY > 0) ||  (eachStep > 0 && scrollY < 0)) {
                    scrollY = 0;
                }
                mView.scrollTo(0, scrollY);
                this.sendEmptyMessageDelayed(0, 4);
            }
        };
    };

    private boolean isNeedMove(){
        int viewHight = mView.getMeasuredHeight();
        int srollHight = getHeight();
        int offset = viewHight - srollHight;
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if(getChildCount() > 0){
            mView = getChildAt(0);
        }
    }
}
