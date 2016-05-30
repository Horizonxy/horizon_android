package com.horizon.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import com.horizon.android.R;

public class RadarView extends View {

    private int mHalfWidth;
    private int mHalfHeight;
    private int mBmpResId = R.mipmap.image;
    private Bitmap mBmp;
    private int mCircleColor = Color.GRAY;
    private Paint mCirclePaint;
    private int mSweepColor = Color.GRAY;
    private Paint mSweepPaint;

    private Matrix mSweepMatrix;
    private int degree;
    private int speed = 10;

    private Handler mHandler = new Handler();

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.parseColor("#AA000000"));
        mHalfWidth = context.getResources().getDisplayMetrics().widthPixels / 2;
        mHalfHeight = context.getResources().getDisplayMetrics().heightPixels / 2;

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStrokeWidth(2);
        mSweepPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSweepPaint.setAntiAlias(true);
        mSweepPaint.setStyle(Paint.Style.FILL);
        mSweepPaint.setStrokeWidth(2);
        Shader sweep = new SweepGradient(mHalfWidth, mHalfHeight, Color.TRANSPARENT, mSweepColor);
        mSweepPaint.setShader(sweep);
        mSweepMatrix = new Matrix();

        mBmp = ((BitmapDrawable) context.getResources().getDrawable(mBmpResId)).getBitmap();
        float scale;
        float max = mHalfHeight * 2f / 5;
        if (max > mBmp.getWidth() || max > mBmp.getHeight()) {
            scale = Math.max(max / mBmp.getHeight(), max / getWidth());
        } else {
            scale = Math.min(max / mBmp.getHeight(), max / getWidth());
        }
        int wh = Math.min(mBmp.getWidth(), mBmp.getHeight());
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scale, scale);
        int x = (mBmp.getWidth() - wh) / 2;
        int y = (mBmp.getHeight() - wh) / 2;
        mBmp = Bitmap.createBitmap(mBmp, x, y, wh, wh, scaleMatrix, false);

        mHandler.postDelayed(new SweepRun(), 800);
    }

    class SweepRun implements Runnable {
        @Override
        public void run() {
            if (degree == 360) {
                degree = 0;
            }
            mSweepMatrix.postRotate(++degree, mHalfWidth, mHalfHeight);

            postDelayed(this, speed);
            invalidate();
        }
    }

    ;

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBmp != null && !mBmp.isRecycled()) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            Bitmap bitmap = Bitmap.createBitmap(mBmp.getWidth(), mBmp.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);
            bitmapCanvas.drawBitmap(mBmp, 0, 0, paint);

            Bitmap circleBmp = Bitmap.createBitmap(mBmp.getWidth(), mBmp.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas circleCanvas = new Canvas(circleBmp);
            circleCanvas.drawCircle(mBmp.getWidth() / 2, mBmp.getWidth() / 2, mBmp.getWidth() / 2, paint);

            paint.setFilterBitmap(false);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            bitmapCanvas.drawBitmap(circleBmp, 0, 0, paint);

            canvas.drawBitmap(bitmap, mHalfWidth - bitmap.getWidth() / 2, mHalfHeight - bitmap.getHeight() / 2, null);

            Paint border = new Paint(Paint.ANTI_ALIAS_FLAG);
            border.setColor(Color.parseColor("#ff1196db"));
            border.setStyle(Paint.Style.STROKE);
            border.setStrokeWidth(2);
            canvas.drawCircle(mHalfWidth, mHalfHeight, bitmap.getWidth()/2+2, border);
        }

        canvas.drawCircle(mHalfWidth, mHalfHeight, mHalfHeight * 5 / 20, mCirclePaint);
        canvas.drawCircle(mHalfWidth, mHalfHeight, mHalfHeight * 9 / 20, mCirclePaint);
        canvas.drawCircle(mHalfWidth, mHalfHeight, mHalfHeight * 13 / 20, mCirclePaint);
        canvas.drawCircle(mHalfWidth, mHalfHeight, mHalfHeight * 17 / 20, mCirclePaint);

        for (int i = 0; i < mWaves.size(); i++) {
            Wave wave = mWaves.valueAt(i);
            canvas.drawCircle(wave.x, wave.y, wave.currentRadius, wave.paint);
        }

        canvas.concat(mSweepMatrix);
        canvas.drawCircle(mHalfWidth, mHalfHeight, mHalfHeight * 17 / 20, mSweepPaint);
        mSweepMatrix.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            launchSingleDown((int) System.currentTimeMillis(), (int) event.getX(), (int) event.getY());
        }

        return super.onTouchEvent(event);
    }

    private SparseArray<Wave> mWaves = new SparseArray<Wave>();

    private void launchSingleDown(final int key, final int x, final int y) {
        final Runnable waveRun = new Runnable() {
            @Override
            public void run() {
                Wave wave = mWaves.get(key);
                if (wave == null) {
                    wave = new Wave(x, y, mHalfHeight / 3, 0, Color.parseColor("#AAFFFFFF"));
                    mWaves.put(key, wave);
                }

                if (wave.currentRadius <= wave.maxRadius) {
                    wave.currentRadius += 5;
                    int alpha = 255 - 255 * wave.currentRadius / wave.maxRadius;
                    wave.paint.setAlpha(alpha < 0 ? 0 : alpha);
                    postDelayed(this, 2);
                } else {
                    mWaves.remove(key);
                }
            }
        };
        waveRun.run();
    }

    class Wave {
        public int x;
        public int y;
        public int maxRadius;
        public int currentRadius;
        public int color;
        public Paint paint;

        public Wave(int x, int y, int maxRadius, int currentRadius, int color) {
            this.x = x;
            this.y = y;
            this.maxRadius = maxRadius;
            this.currentRadius = currentRadius;
            this.color = color;
            this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            this.paint.setColor(color);
            this.paint.setStyle(Paint.Style.FILL);
        }
    }
}
