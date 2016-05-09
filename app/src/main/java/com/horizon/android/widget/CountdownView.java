package com.horizon.android.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.horizon.android.util.log.LogUtils;


/**
 * 倒计时view
 */
public class CountdownView extends TextView implements View.OnClickListener {

    private static final String END = "秒";
    private int count = 60;
    private String mInitText;
    private ValueAnimator valueAnimator;

    public CountdownView(Context context) {
        this(context, null);
    }

    public CountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mInitText = "点击开始";
        setText(mInitText);
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        valueAnimator = ValueAnimator.ofInt(count-1, 0);
        valueAnimator.setDuration(count * 1000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                LogUtils.e("value:"+value);
                setText(value + END);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setText(mInitText);
                setEnabled(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(valueAnimator != null){
            valueAnimator.end();
        }
    }
}
