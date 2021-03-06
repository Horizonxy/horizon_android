package com.horizon.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
* 带1/2倒影ImageView
* @author 蒋先明
* @date 2016/5/30
*/
public class ReflectionImageView extends ImageView {

    private static final int SPACE = 1;
    private WeakReference<Bitmap> mBuffer;

    public ReflectionImageView(Context context) {
        this(context, null);
    }

    public ReflectionImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAdjustViewBounds(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(getDrawable() != null){
            Bitmap bmp = ((BitmapDrawable)getDrawable()).getBitmap();

            if(bmp.getWidth() * 1f / getMeasuredWidth() > 1){
                float scale = getMeasuredWidth() * 1f / bmp.getWidth();
                Matrix matrix = new Matrix();
                matrix.setScale(scale, scale);
                bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
                setImageBitmap(bmp);
                return;
            }

            int height = bmp.getHeight() / 2 * 3 + SPACE;
            if(getMeasuredHeight() < height){
                setMeasuredDimension(bmp.getWidth(), height);
            }
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        mBuffer = null;
        super.setImageBitmap(bm);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BitmapDrawable drawable = (BitmapDrawable)getDrawable();
        if(drawable != null){
            Bitmap out = mBuffer == null ? null : mBuffer.get();
            if(out == null) {
                Bitmap bmp = drawable.getBitmap();

                int width = bmp.getWidth();
                int height = bmp.getHeight();

                out = Bitmap.createBitmap(width, height / 2 * 3 + SPACE, Bitmap.Config.ARGB_8888);

                Canvas reflectionCanvas = new Canvas(out);

                reflectionCanvas.drawBitmap(bmp, 0, 0, null);

                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setColor(Color.TRANSPARENT);
                reflectionCanvas.drawRect(0, height, width, height + SPACE, paint);

                Matrix matrix = new Matrix();
                matrix.preScale(1f, -1f);
                Bitmap reflectionBmp = Bitmap.createBitmap(bmp, 0, height / 2, width, height / 2, matrix, false);
                reflectionCanvas.drawBitmap(reflectionBmp, 0, height + SPACE, null);

                LinearGradient gradient = new LinearGradient(0, height + SPACE, 0, height + SPACE + height / 2, 0xffffffff, 0x00ffffff, Shader.TileMode.CLAMP);
                paint.reset();
                paint.setShader(gradient);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                reflectionCanvas.drawRect(0, height + SPACE, width, height + SPACE + height / 2, paint);

                canvas.drawBitmap(out, 0, 0, null);

                mBuffer = new WeakReference<Bitmap>(out);
            } else {
                canvas.drawBitmap(out, 0, 0, null);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    @Override
    public void invalidate() {
        mBuffer = null;

        super.invalidate();
    }
}
