package com.horizon.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.horizon.android.util.log.LogUtils;

import java.lang.ref.WeakReference;

public class XCRoundImageView extends ImageView {

    private static final int CIRCLE = 0;
    private static final int ROUND = 1;
    private static final int OVAL = 2;

    private int mType;
    private Paint mPaint;
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    private Bitmap mBmp;
    private WeakReference<Bitmap> mBuffer;
    private int mRoundRadius;

    public XCRoundImageView(Context context) {
        this(context, null);
    }

    public XCRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mType = CIRCLE;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(mType == CIRCLE){
            int width = Math.min(getMeasuredHeight(), getMeasuredWidth());
            setMeasuredDimension(width, width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bmp = mBuffer == null ? null : mBuffer.get();
        if(bmp == null || bmp.isRecycled()){
            Drawable drawable = getDrawable();
            if(drawable != null) {
                int dWidth = drawable.getIntrinsicWidth();
                int dHeight = drawable.getIntrinsicHeight();

                bmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                Canvas drawCanvas = new Canvas(bmp);

                float scale = 1.0f;
                if (mType == CIRCLE) {
                    scale = getWidth() * 1.0f / Math.min(dWidth, dHeight);
                } else if (mType == ROUND || mType == OVAL) {
                    scale = Math.max(getWidth() * 1.0f / dWidth, getHeight() * 1.0f / dHeight);
                }
                drawable.setBounds(0, 0, (int) (scale * dWidth), (int) (scale * dHeight));
                drawable.draw(drawCanvas);

                if (mBmp == null || mBmp.isRecycled()) {
                    mBmp = getDrawBitmap();
                }
                mPaint.reset();
                mPaint.setFilterBitmap(false);
                mPaint.setXfermode(mXfermode);
                drawCanvas.drawBitmap(mBmp, 0, 0, mPaint);
                mPaint.setXfermode(null);
                canvas.drawBitmap(bmp, 0, 0, null);

                mBuffer = new WeakReference<Bitmap>(bmp);
            }
        } else {
            mPaint.setXfermode(null);
            canvas.drawBitmap(bmp, 0, 0, mPaint);
        }

    }

    private Bitmap getDrawBitmap(){
        Bitmap bmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if(mType == CIRCLE) {
            int radius = getWidth() / 2;
            canvas.drawCircle(radius, radius, radius, paint);
        } else if(mType == ROUND) {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), mRoundRadius, mRoundRadius, paint);
        } else if(mType == OVAL) {
            canvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), paint);
        }

        return bmp;
    }

    @Override
    public void invalidate() {
        mBuffer = null;
        if(mBmp != null){
            mBmp.recycle();
            mBmp = null;
        }

        super.invalidate();
    }
}
