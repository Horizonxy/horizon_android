package com.horizon.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.horizon.android.Application;
import com.horizon.android.R;

import java.util.List;

/**
 * 带三角形的viewpager指示器
 */
public class TriangleViewPagerIndicator extends LinearLayout {

    /** 三角画笔 */
    private Paint mTrianglePaint;
    /** 构成三角的线条 */
    private Path mTrianglePath;
    /** 三角指示器宽 */
    private int mTriangleWidth;
    /** 三角指示器高 */
    private int mTriangleHeight;
    /** 三角形的宽度为单个Tab的1/6 */
    private static final float RADIO_TRIANGEL = 1.0f / 8;
    /**  三角形的最大宽度  */
    private final int DIMENSION_TRIANGEL_WIDTH = (int) (Application.SCREENWIDTH / 3 * RADIO_TRIANGEL);
    /** 初始时，三角形指示器的偏移量 */
    private int mInitTranslationX;
    /** 手指滑动时的偏移量 */
    private float mTranslationX;
    /** TAB数量 */
    private static final int DEFAULT_TAB_COUNT = 4;
    private int mTabVisibleCount;
    /** TAB上的内容 */
    private List<String> mTabTitles;

    public ViewPager mViewPager;

    /** 标题正常颜色 */
    private int mNormalColor = 0x77FFFFFF;
    /** 标题高亮颜色 */
    private int mHighLightCoiolor = 0xFFFFFFFF;

    public TriangleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public TriangleViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TriangleViewPagerIndicator);
        mTabVisibleCount = a.getInt(R.styleable.TriangleViewPagerIndicator_visible_tab_count, DEFAULT_TAB_COUNT);
        if(mTabVisibleCount < 0){
            mTabVisibleCount = DEFAULT_TAB_COUNT;
        }
        a.recycle();

        mTrianglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTrianglePaint.setColor(Color.WHITE);
        mTrianglePaint.setStyle(Paint.Style.FILL);
        mTrianglePaint.setPathEffect(new CornerPathEffect(3));//画的线的连接处，有点圆角
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int count = getChildCount();
        if(count == 0){
            return;
        }

        for (int i = 0; i < count; i++){
            View child = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) child.getLayoutParams();
            lp.weight = 0;
            lp.width = Application.SCREENWIDTH / mTabVisibleCount;
            child.setLayoutParams(lp);

            if(mViewPager != null) {
                final int j = i;
                child.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(j, true);
                    }
                });
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGEL);
        mTriangleWidth = Math.min(mTriangleWidth, DIMENSION_TRIANGEL_WIDTH);
        mTriangleHeight = (int) (Math.sqrt(2) * (mTriangleWidth / 2));

        initTriangle();

        mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mTriangleWidth / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight()+mTriangleHeight);
    }

    private void initTriangle(){
        mTrianglePath = new Path();
        mTrianglePath.moveTo(0, 0);
        mTrianglePath.lineTo(mTriangleWidth, 0);
        mTrianglePath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mTrianglePath.close();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();

        canvas.translate(mInitTranslationX + mTranslationX, getHeight()+1);
        canvas.drawPath(mTrianglePath, mTrianglePaint);

        canvas.restore();

        super.dispatchDraw(canvas);
    }

    private OnPageChangeListener mPageChangeListener;

    public void setViewPager(ViewPager mViewPager, int pos){
        this.mViewPager = mViewPager;

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position, positionOffset);

                if(mPageChangeListener != null){
                    mPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                resetTextViewColor();
                highLightTextView(position);

                if(mPageChangeListener != null){
                    mPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if(mPageChangeListener != null){
                    mPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
    }

    private void scroll(int position, float offset) {
        // 不断改变偏移量，invalidate
        mTranslationX = getWidth() / mTabVisibleCount * (position + offset);
        int tabWidth = Application.SCREENWIDTH / mTabVisibleCount;
        // 容器滚动，当移动到倒数最后一个的时候，开始滚动
        if (offset > 0 && position >= (mTabVisibleCount - 2) && getChildCount() > mTabVisibleCount && position < (getChildCount() - 2)) {
            if (mTabVisibleCount != 1) {
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth + (int) (tabWidth * offset), 0);
            } else {// 为count为1时 的特殊处理
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }
        }

        invalidate();
    }

    public void setVisibleTabCount(int count) {
        this.mTabVisibleCount = count;
    }

    public void setTabItemTitles(List<String> datas)
    {
        // 如果传入的list有值，则移除布局文件中设置的view
        if (datas != null && datas.size() > 0)
        {
            this.removeAllViews();
            this.mTabTitles = datas;

            for (int i = 0; i < mTabTitles.size(); i++)
            {
                // 添加view
                TextView child = new TextView(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-1);
                lp.weight = 0;
                lp.width = Application.SCREENWIDTH / mTabVisibleCount;
                child.setLayoutParams(lp);
                child.setGravity(Gravity.CENTER);
                child.setText(mTabTitles.get(i));
                child.setTextColor(mNormalColor);

                addView(child);

                if(mViewPager != null) {
                    final int j = i;
                    child.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(j, true);
                        }
                    });
                }
            }
        }

    }
    public interface OnPageChangeListener {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        public void onPageSelected(int position);
        public void onPageScrollStateChanged(int state);
    }

    public void setOnPageChangeListener(OnPageChangeListener pageChangeListener){
        this.mPageChangeListener = pageChangeListener;
    }

    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(mNormalColor);
            }
        }
    }

    protected void highLightTextView(int position) {
        View view = getChildAt(position);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(mHighLightCoiolor);
        }
    }
}
