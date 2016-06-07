package com.horizon.android.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* 本地图片加载器
* @author 蒋先明
* @date 2016/5/30
*/
public class NativeImageLoader {

    private static NativeImageLoader instance;
    private LruCache<String, Bitmap> mCache;
    private ExecutorService mImageLoadThreads = Executors.newCachedThreadPool();

    private NativeImageLoader(){
        long maxMemory = Runtime.getRuntime().maxMemory();
        mCache = new LruCache<String, Bitmap>((int) (maxMemory / 8)){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // API 19
                    return bitmap.getAllocationByteCount();
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {// API 12
                    return bitmap.getByteCount();
                } else {
                    return bitmap.getRowBytes() * bitmap.getHeight(); // earlier  version
                }
            }
        };
    }

    public static NativeImageLoader getInstance(){
        if(instance == null) {
            synchronized (NativeImageLoader.class) {
                if(instance == null) {
                    instance = new NativeImageLoader();
                }
            }
        }
        return instance;
    }

    public Bitmap loadNativeImage(String path, NativeImageLoadListener listener){
        return loadNativeImage(path, 0, 0, listener);
    }

    public Bitmap loadNativeImage(final String path, final int width, final int height, final NativeImageLoadListener listener){
        Bitmap bmp = getBitmapFromCache(path);
        if(bmp != null && listener != null){
            listener.onImageLoad(path, bmp);
        }

        if(bmp == null){
            final Handler mHander = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if(listener != null) {
                        listener.onImageLoad(path, (Bitmap) msg.obj);
                    }
                }
            };
            mImageLoadThreads.execute(new Runnable() {
                @Override
                public void run() {
                    Bitmap mBitmap = decodeThumbBitmapForFile(path, width, height);
                    Message msg = mHander.obtainMessage();
                    msg.obj = mBitmap;
                    mHander.sendMessage(msg);

                    //将图片加入到内存缓存
                    addBitmapToCache(path, mBitmap);
                }
            });
        }

        return bmp;
    }

    /**
     * 根据View(主要是ImageView)的宽和高来获取图片的缩略图
     * @param path
     * @param viewWidth
     * @param viewHeight
     * @return
     */
    private Bitmap decodeThumbBitmapForFile(String path, int viewWidth, int viewHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置为true,表示解析Bitmap对象，该对象不占内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        //设置缩放比例
        options.inSampleSize = computeScale(options, viewWidth, viewHeight);

        //设置为false,解析Bitmap对象加入到内存中
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 根据View(主要是ImageView)的宽和高来计算Bitmap缩放比例。默认不缩放
     * @param options
     * @param viewWidth
     * @param viewHeight
     */
    private int computeScale(BitmapFactory.Options options, int viewWidth, int viewHeight){
        int inSampleSize = 1;
        if(viewWidth == 0 || viewWidth == 0){
            return inSampleSize;
        }
        int bitmapWidth = options.outWidth;
        int bitmapHeight = options.outHeight;

        //假如Bitmap的宽度或高度大于我们设定图片的View的宽高，则计算缩放比例
        if(bitmapWidth > viewWidth || bitmapHeight > viewWidth){
            int widthScale = Math.round((float) bitmapWidth / (float) viewWidth);
            int heightScale = Math.round((float) bitmapHeight / (float) viewHeight);

            //为了保证图片不缩放变形，我们取宽高比例最小的那个
            inSampleSize = widthScale < heightScale ? widthScale : heightScale;
        }
        return inSampleSize;
    }


    /**
     * 往内存缓存中添加Bitmap
     *
     * @param key
     * @param bitmap
     */
    private void addBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null && bitmap != null) {
            mCache.put(key, bitmap);
        }
    }


    private Bitmap getBitmapFromCache(String path){
        return mCache.get(path);
    }

    public interface NativeImageLoadListener{
        public void onImageLoad(String path, Bitmap bitmap);
    }
}
