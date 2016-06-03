package com.horizon.android.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class PopupWindow extends android.widget.PopupWindow {

    public PopupWindow(Context context, int layoutId){
        super(context);

        View root = LayoutInflater.from(context).inflate(layoutId, null);

        onBindView(root);

        setContentView(root);

        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setTouchable(true);
        setFocusable(true);

        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        setBackgroundDrawable(dw);

        setOutsideTouchable(true);
    }

    public void showAsDropDownCenter(View v){
        getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        showAsDropDown(v, (v.getRight() - getContentView().getMeasuredWidth()) / 2, 2);
    }

    public abstract void onBindView(View root);

}
