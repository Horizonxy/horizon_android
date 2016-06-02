package com.horizon.android.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.util.SimpleAnimatorListener;
import com.horizon.android.util.SystemStatusManager;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhy.autolayout.AutoLayoutActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureDetailActivity extends AutoLayoutActivity {

    @Bind(R.id.rl_pic_detail)
    RelativeLayout rlImageDetail;
    @Bind(R.id.image_detail)
    ImageView imageDetail;

    PhotoViewAttacher attacher;

    private final static long DURATION = 1500;

    private ColorMatrix colorMatrix;
    private ColorMatrixColorFilter colorFilter;

    private float scaleX, scaleY;
    private int initWidth, initHeight;
    private int deltaX, deltaY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.transparent);
        setContentView(R.layout.activity_picture_detail);
        ButterKnife.bind(this);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(rlImageDetail, "alpha", 0.4f, 1.0f);
        alpha.setDuration(DURATION);
        alpha.start();

        Intent extra = getIntent();
        String url = extra.getStringExtra(Constants.BUNDLE_PIC_URL);
        final int left = extra.getIntExtra(Constants.BUNDLE_PIC_LEFT, 0);
        final int top = extra.getIntExtra(Constants.BUNDLE_PIC_TOP, 0);
        final int width = extra.getIntExtra(Constants.BUNDLE_PIC_WIDTH, 0);
        final int height = extra.getIntExtra(Constants.BUNDLE_PIC_HEIGHT, 0);

        attacher = new PhotoViewAttacher(imageDetail);

        colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        colorFilter = new ColorMatrixColorFilter(colorMatrix);
        imageDetail.setColorFilter(colorFilter);

        Application.getInstance().getImageLoader().displayImage(url, imageDetail, Application.getInstance().getDefaultOptions(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                int w = loadedImage.getWidth();
                int h = loadedImage.getHeight();
                float scale = Application.getInstance().SCREENWIDTH * 1.0f / w;

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) imageDetail.getLayoutParams();
                lp.height = initHeight = (int) (scale * h);
                lp.width = initWidth = Application.getInstance().SCREENWIDTH;
                imageDetail.setLayoutParams(lp);

                scaleX = width * 1.0f / initWidth;
                scaleY = height * 1.0f / initHeight;

                int[] location = new int[2];
                imageDetail.getLocationOnScreen(location);
                deltaX = left - location[0];
                deltaY = top - location[1] + (initHeight) / 2;

                imageDetail.setPivotX(0);
                imageDetail.setPivotY(0);
                imageDetail.setScaleX(scaleX);
                imageDetail.setScaleY(scaleY);

                imageDetail.setTranslationX(deltaX);
                imageDetail.setTranslationY(deltaY);

                imageDetail.setAlpha(0.6f);

                imageDetail.animate()
                        .alpha(1f)
                        .scaleX(1f).scaleY(1f)
                        .translationX(0).translationY(0)
                        .setDuration(DURATION).setListener(new SimpleAnimatorListener(){
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) imageDetail.getLayoutParams();
                        lp.height = Application.getInstance().SCREENHEIGHT;
                        lp.width = Application.getInstance().SCREENWIDTH;
                        imageDetail.setLayoutParams(lp);

                        attacher.update();
                    }
                });

                ObjectAnimator saturation = ObjectAnimator.ofFloat(PictureDetailActivity.this, "saturation", 0f, 1f);
                saturation.setDuration(DURATION);
                saturation.start();
            }
        });

    }

    public void setSaturation(float sat) {
        colorMatrix.setSaturation(sat);
        colorFilter = new ColorMatrixColorFilter(colorMatrix);
        imageDetail.setColorFilter(colorFilter);
    }

    @Override
    public void onBackPressed() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) imageDetail.getLayoutParams();
        lp.width = initWidth;
        lp.height = initHeight;
        imageDetail.setLayoutParams(lp);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(rlImageDetail, "alpha", 1f, 0.4f);
        alpha.setDuration(DURATION);
        alpha.start();

        ObjectAnimator saturation = ObjectAnimator.ofFloat(PictureDetailActivity.this, "saturation", 1f, 0f);
        saturation.setDuration(DURATION);
        saturation.start();

        imageDetail.setPivotX(0);
        imageDetail.setPivotY(0);
        imageDetail.setScaleX(1);
        imageDetail.setScaleY(1);

        imageDetail.setAlpha(1f);

        imageDetail.animate()
                .alpha(0.6f)
                .scaleX(scaleX).scaleY(scaleY)
                .translationX(deltaX).translationY(deltaY)
                .setDuration(DURATION).setListener(new SimpleAnimatorListener(){
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }
        });

    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(0, 0);
    }

}
