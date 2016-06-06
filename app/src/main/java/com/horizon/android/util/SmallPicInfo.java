package com.horizon.android.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class SmallPicInfo implements Serializable {
    public String url;
    public int left;
    public int top;
    public int width;
    public int height;
    public int position;
    public byte[] bmp;

    public SmallPicInfo(String url, int left, int top, int width, int height, int position, Bitmap bmp) {
        this.url = url;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
        this.position = position;
        if(bmp != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            this.bmp = baos.toByteArray();
        }
    }


}