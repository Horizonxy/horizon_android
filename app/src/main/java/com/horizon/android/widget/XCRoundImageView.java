package com.horizon.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class XCRoundImageView extends ImageView {

    private TYPE mType = TYPE.CIRCLE;
    private Paint mPaint;

    private Bitmap mBmp;
    private WeakReference<Bitmap> mBuffer;
    private int mRoundRadius;

    public static enum TYPE {
        CIRCLE("circle"),
        ROUND("round"),
        OVAL("oval");

        private String value;

        private TYPE(String value){
            this.value = value;
        }
    }

    public XCRoundImageView(Context context) {
        this(context, null);
    }

    public XCRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(mType == TYPE.CIRCLE){
            int width = Math.min(getMeasuredHeight(), getMeasuredWidth());
            setMeasuredDimension(width, width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bmp = mBuffer == null ? null : mBuffer.get();
        if(bmp == null || bmp.isRecycled()){
            bmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas drawCanvas = new Canvas(bmp);

            Drawable drawable = getDrawable();
            int dWidth = drawable.getIntrinsicWidth();
            int dHeight = drawable.getIntrinsicHeight();
            float scale = 1f;
            if(mType == TYPE.CIRCLE){
                scale = getWidth() * 1f / Math.min(dWidth, dHeight);
                drawable.setBounds((dWidth - (int)(scale*dWidth))/2, (dHeight - (int)(scale*dHeight))/2, (int)(scale*dWidth), (int)(scale*dHeight));
            } else if(mType == TYPE.ROUND || mType == TYPE.OVAL){
                scale = Math.max(getWidth() * 1f / dWidth, getHeight() * 1f / dHeight);
                drawable.setBounds(0, 0, (int)(scale*dWidth), (int)(scale*dHeight));
            }
            drawable.draw(drawCanvas);

            if(mBmp == null || mBmp.isRecycled()){
                mBmp = getDrawBitmap();
            }
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawBitmap(mBmp, 0, 0, mPaint);

            mBuffer = new WeakReference<Bitmap>(bmp);
        }

        mPaint.setXfermode(null);
        canvas.drawBitmap(bmp, 0, 0, mPaint);

    }

    private Bitmap getDrawBitmap(){
        Bitmap bmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if(mType == TYPE.CIRCLE) {
            int radius = getWidth() / 2;
            canvas.drawCircle(radius, radius, radius, paint);
        } else if(mType == TYPE.ROUND) {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), mRoundRadius, mRoundRadius, paint);
        } else if(mType == TYPE.OVAL) {
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
