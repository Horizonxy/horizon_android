package com.horizon.android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoLinearLayout;

public class TrianglePopupLayout extends AutoLinearLayout {

    private Paint mBorderPaint;
    private Path mBorderPath;

    private int mBorderColor = Color.BLACK;
    private int mBorderWidth = 2;
    private int mTrangleWidth;
    private int mTrangleHeight;

    public TrianglePopupLayout(Context context) {
        this(context, null);
    }

    public TrianglePopupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();



    }

    void initPaint(){
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setPathEffect(new CornerPathEffect(6));//画的线的连接处，有点圆角
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        mTrangleWidth = (int) (20 * height * 1f / 150);

        mTrangleHeight = (int) Math.sqrt(Math.pow(mTrangleWidth, 2) - Math.pow(mTrangleWidth/2, 2));
        setMeasuredDimension(width, height + mTrangleHeight);

        int trangleStart = (getMeasuredWidth() - mTrangleWidth) / 2;

        mBorderPath = new Path();
        mBorderPath.moveTo(0, mTrangleHeight);
        mBorderPath.lineTo(trangleStart, mTrangleHeight);
        mBorderPath.lineTo(width / 2, 0);
        mBorderPath.lineTo(trangleStart + mTrangleWidth, mTrangleHeight);
        mBorderPath.lineTo(width, mTrangleHeight);
        mBorderPath.lineTo(width, mTrangleHeight + height);
        mBorderPath.lineTo(0, mTrangleHeight + height);
        mBorderPath.close();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawPath(mBorderPath, mBorderPaint);

        canvas.translate(0, mTrangleHeight);
        super.dispatchDraw(canvas);
    }
}
