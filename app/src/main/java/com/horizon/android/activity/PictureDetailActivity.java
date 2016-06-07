package com.horizon.android.activity;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.simple.SimpleAnimatorListener;
import com.horizon.android.util.DisplayUtils;
import com.horizon.android.util.SmallPicInfo;
import com.horizon.android.util.SystemStatusManager;
import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureDetailActivity extends Activity {

    @Bind(R.id.rl_root)
    AutoRelativeLayout rlRoot;
    @Bind(R.id.image_detail)
    ImageView ivDetail;
    @Bind(R.id.progress_load)
    ProgressBar progressLoad;

    SmallPicInfo smallPicInfo;
    PhotoViewAttacher attacher;

    private static final int DURATION = 250;
    private int screenWidth, screenHeight;

    private static boolean userCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.transparent);
        setContentView(R.layout.activity_picture_detail);
        ButterKnife.bind(this);

        rlRoot.setAlpha(0f);
        rlRoot.animate().alpha(1f).setDuration(DURATION);

        screenWidth = Application.getInstance().SCREENWIDTH;
        screenHeight = Application.getInstance().SCREENHEIGHT;

        smallPicInfo = (SmallPicInfo) getIntent().getSerializableExtra(Constants.BUNDLE_PIC_INFOS);

        attacher = new PhotoViewAttacher(ivDetail);

        DiskCache diskCache = Application.getInstance().getImageLoader().getDiskCache();
        File file = diskCache.get(smallPicInfo.url);
        if (userCache && file != null && file.exists()) {
            userCache = false;
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            loadOnCache(bitmap);
        } else {
            userCache = true;
            loadOnNetwork();
        }
    }

    private void loadOnNetwork(){
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivDetail.getLayoutParams();
        lp.width = smallPicInfo.width;
        lp.height = smallPicInfo.height;
        ivDetail.setLayoutParams(lp);

        Bitmap small = BitmapFactory.decodeByteArray(smallPicInfo.bmp, 0, smallPicInfo.bmp.length);
        ivDetail.setImageBitmap(small);

        int smallDeltaX = smallPicInfo.left - (screenWidth - smallPicInfo.width) / 2;
        int smallDeltaY = smallPicInfo.top - (screenHeight - smallPicInfo.height + DisplayUtils.getStatusBarHeight(PictureDetailActivity.this)) / 2;

        ivDetail.setPivotX(0);
        ivDetail.setPivotY(0);
        ivDetail.setTranslationX(smallDeltaX);
        ivDetail.setTranslationY(smallDeltaY);

        ivDetail.animate().translationX(0).translationY(0).setDuration(DURATION).setListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressLoad.setVisibility(View.VISIBLE);

                DisplayImageOptions options = new DisplayImageOptions.Builder()
                        .cacheOnDisk(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .build();

                Application.getInstance().getImageLoader().displayImage(smallPicInfo.url, ivDetail, options, new SimpleImageLoadingListener() {

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressLoad.setVisibility(View.GONE);
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
                        progressLoad.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        progressLoad.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private void loadOnCache(Bitmap bitmap){
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

        int deltaX = smallPicInfo.left - (screenWidth - lp.width) / 2;
        int deltaY = smallPicInfo.top - (screenHeight - lp.height) / 2;

        ivDetail.setPivotX(0);
        ivDetail.setPivotY(0);
        ivDetail.setScaleX(scaleX);
        ivDetail.setScaleY(scaleY);

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
    }

    private void setImageViewMatch(){
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivDetail.getLayoutParams();
        lp.height = Application.getInstance().SCREENHEIGHT;
        lp.width = Application.getInstance().SCREENWIDTH;
        ivDetail.setLayoutParams(lp);
    }


    @Override
    public void onBackPressed() {
        rlRoot.animate().alpha(0f).setDuration(DURATION);

        float scaleX = smallPicInfo.width * 1f / ivDetail.getWidth();
        float scaleY = smallPicInfo.height * 1f / ivDetail.getHeight();

        int[] location = new int[2];
        ivDetail.getLocationOnScreen(location);
        int deltaX = smallPicInfo.left - location[0];
        int deltaY = smallPicInfo.top - location[1];

        ivDetail.setPivotX(0);
        ivDetail.setPivotY(0);
        ivDetail.setScaleX(1f);
        ivDetail.setScaleY(1f);
        ivDetail.setTranslationX(0);
        ivDetail.setTranslationY(0);

        ivDetail.animate().scaleX(scaleX).scaleY(scaleY).translationX(deltaX).translationY(deltaY).setDuration(DURATION).setListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                PictureDetailActivity.super.onBackPressed();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(0, 0);
    }
}
