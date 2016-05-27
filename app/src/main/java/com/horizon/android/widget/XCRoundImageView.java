package com.horizon.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.horizon.android.R;

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

    private int mBorderWidth;
    private int mBorderColor;

    public XCRoundImageView(Context context) {
        this(context, null);
    }

    public XCRoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScaleType(ScaleType.CENTER_CROP);

//        mBorderColor = Color.BLACK;
//        mBorderWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
//        mRoundRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.XCRoundImageView);
        mType = a.getInt(R.styleable.XCRoundImageView_type, mType);
        mBorderColor = a.getColor(R.styleable.XCRoundImageView_border_color, mBorderColor);
        mBorderWidth = a.getDimensionPixelSize(R.styleable.XCRoundImageView_border_width, mBorderWidth);
        mRoundRadius = a.getDimensionPixelSize(R.styleable.XCRoundImageView_round_radius, mRoundRadius);
        a.recycle();

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

                if(mBorderWidth > 0) {
                    mPaint.setAntiAlias(true);
                    mPaint.setStrokeWidth(mBorderWidth);
                    mPaint.setStyle(Paint.Style.STROKE);
                    mPaint.setColor(mBorderColor);
                    int borderOffset = mBorderWidth/2;
                    if(mType == CIRCLE) {
                        canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 2 - mBorderWidth, mPaint);
                    } else if(mType == ROUND){
                        canvas.drawRoundRect(new RectF(borderOffset,borderOffset, getWidth() - borderOffset, getHeight() - borderOffset), mRoundRadius, mRoundRadius, mPaint);
                    } else if(mType == OVAL){
                        canvas.drawOval(new RectF(borderOffset, borderOffset, getWidth() - borderOffset, getHeight() - borderOffset), mPaint);
                    }
                }

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
        paint.setColor(mBorderColor);

        if(mType == CIRCLE) {
            int radius = getWidth() / 2;
            if(mBorderWidth > 0){
                radius -= 2 * mBorderWidth;
            }
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, radius, paint);
        } else if(mType == ROUND) {
            if(mBorderWidth > 0){
                canvas.drawRoundRect(new RectF(mBorderWidth, mBorderWidth, getWidth() - mBorderWidth, getHeight() - mBorderWidth), mRoundRadius, mRoundRadius, paint);
            } else {
                canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), mRoundRadius, mRoundRadius, paint);
            }
        } else if(mType == OVAL) {
            if(mBorderWidth > 0){
                canvas.drawOval(new RectF(mBorderWidth, mBorderWidth, getWidth() - mBorderWidth, getHeight() - mBorderWidth), paint);
            } else {
                canvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), paint);
            }
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
