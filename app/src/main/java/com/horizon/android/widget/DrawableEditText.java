package com.horizon.android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

public class DrawableEditText extends EditText {

    public DrawableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableEditText(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        resetCompoundDrawable(0);
        resetCompoundDrawable(2);
    }

    private void resetCompoundDrawable(int site) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawable = drawables[site];
        if (drawable != null) {
            int viewH = getMeasuredHeight();
            int wh = viewH/2;
            drawable.setBounds(0, 0, wh, wh);
            setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
        }
    }
}
