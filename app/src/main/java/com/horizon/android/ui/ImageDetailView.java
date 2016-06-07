package com.horizon.android.ui;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.simple.SimpleAnimatorListener;
import com.horizon.android.util.DisplayUtils;
import com.horizon.android.util.SmallPicInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageDetailView extends AutoRelativeLayout {

    @Bind(R.id.image_detail)
    ImageView ivDetail;
    @Bind(R.id.progress_load)
    ProgressBar progressLoad;

    private DisplayImageOptions options;
    private PhotoViewAttacher attacher;
    private int screenWidth, screenHeight;
    private static final int DURATION = 10000;

    public ImageDetailView(Context context) {
        this(context, null);
    }

    public ImageDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);

        screenWidth = Application.getInstance().SCREENWIDTH;
        screenHeight = Application.getInstance().SCREENHEIGHT;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        attacher = new PhotoViewAttacher(ivDetail);
    }

    public void loadImage(SmallPicInfo smallPicInfo, boolean trans) {
//        DiskCache diskCache = Application.getInstance().getImageLoader().getDiskCache();
//        File file = diskCache.get(smallPicInfo.url);
//        if (file != null && file.exists()) {
//            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//            loadOnCache(smallPicInfo, bitmap, trans);
//        } else {
            loadOnNetwork(smallPicInfo, trans);
//        }
    }

    private void loadOnNetwork(final SmallPicInfo smallPicInfo, boolean trans){
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivDetail.getLayoutParams();
        lp.width = smallPicInfo.width;
        lp.height = smallPicInfo.height;
        ivDetail.setLayoutParams(lp);

        Bitmap small = BitmapFactory.decodeByteArray(smallPicInfo.bmp, 0, smallPicInfo.bmp.length);
        ivDetail.setImageBitmap(small);

        if(trans) {
            int smallDeltaX = smallPicInfo.left - (screenWidth - smallPicInfo.width) / 2;
            int smallDeltaY = smallPicInfo.top - (screenHeight - smallPicInfo.height + DisplayUtils.getStatusBarHeight(this.getContext())) / 2;

            ivDetail.setPivotX(0);
            ivDetail.setPivotY(0);
            ivDetail.setTranslationX(smallDeltaX);
            ivDetail.setTranslationY(smallDeltaY);

            ivDetail.animate().translationX(0).translationY(0).setDuration(DURATION).setListener(new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressLoad.setVisibility(View.VISIBLE);
                    if (options == null) {
                        options = new DisplayImageOptions.Builder()
                                .cacheOnDisk(true)
                                .bitmapConfig(Bitmap.Config.RGB_565)
                                .build();
                    }
                    Application.getInstance().getImageLoader().displayImage(smallPicInfo.url, ivDetail, options, new DetailImageLoadListener(smallPicInfo));
                }
            });
        } else {
            progressLoad.setVisibility(View.VISIBLE);
            if (options == null) {
                options = new DisplayImageOptions.Builder()
                        .cacheOnDisk(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .build();
            }
            Application.getInstance().getImageLoader().displayImage(smallPicInfo.url, ivDetail, options, new DetailImageLoadListener(smallPicInfo));
        }
    }

    class DetailImageLoadListener extends SimpleImageLoadingListener {

        private SmallPicInfo smallPicInfo;

        public DetailImageLoadListener(SmallPicInfo smallPicInfo) {
            this.smallPicInfo = smallPicInfo;
        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            hideProgess();
            attacher.update();

            float scaleX = screenWidth * 1f / smallPicInfo.width;
            float scaleY = screenHeight * 1f / smallPicInfo.height;
            float scale = Math.min(scaleX, scaleY);

            ivDetail.setPivotX(smallPicInfo.width / 2);
            ivDetail.setPivotY(smallPicInfo.height / 2);

            ivDetail.animate().scaleX(scale).scaleY(scale).setDuration(DURATION).setListener(new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    ivDetail.setScaleX(1f);
                    ivDetail.setScaleY(1f);
                    setImageViewMatch();
                }
            });
        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            hideProgess();
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
            hideProgess();
        }
    }

    private void loadOnCache(SmallPicInfo smallPicInfo, Bitmap bitmap, boolean trans){
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float scale = screenWidth * 1.0f / w;

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivDetail.getLayoutParams();
        lp.height = (int) (scale * h);
        lp.width = screenWidth;
        ivDetail.setLayoutParams(lp);
        ivDetail.setImageBitmap(bitmap);
        attacher.update();

        float scaleX = smallPicInfo.width * 1.0f / lp.width;
        float scaleY = smallPicInfo.height * 1.0f / lp.height;

        ivDetail.setScaleX(scaleX);
        ivDetail.setScaleY(scaleY);

        if(trans) {
            ivDetail.setPivotX(0);
            ivDetail.setPivotY(0);

            int deltaX = smallPicInfo.left - (screenWidth - lp.width) / 2;
            int deltaY = smallPicInfo.top - (screenHeight - lp.height) / 2;

            ivDetail.setTranslationX(deltaX);
            ivDetail.setTranslationY(deltaY);

            ivDetail.animate()
                    .scaleX(1f).scaleY(1f)
                    .translationX(0).translationY(0)
                    .setDuration(DURATION).setListener(new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    setImageViewMatch();
                }
            });
        } else {
            ivDetail.animate()
                    .scaleX(1f).scaleY(1f)
                    .setDuration(DURATION).setListener(new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    setImageViewMatch();
                }
            });
        }
    }

    private void setImageViewMatch(){
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivDetail.getLayoutParams();
        lp.height = Application.getInstance().SCREENHEIGHT;
        lp.width = Application.getInstance().SCREENWIDTH;
        ivDetail.setLayoutParams(lp);
    }

    private void showProgress(){
        progressLoad.setVisibility(View.VISIBLE);
    }

    private void hideProgess(){
        progressLoad.setVisibility(View.GONE);
    }

    public ImageView getIvDetail() {
        return ivDetail;
    }
}
