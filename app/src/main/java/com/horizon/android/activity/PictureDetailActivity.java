package com.horizon.android.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
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
import com.horizon.android.util.SystemStatusManager;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PictureDetailActivity extends Activity {

    @Bind(R.id.rl_pic_detail)
    RelativeLayout rlImageDetail;
    @Bind(R.id.image_detail)
    ImageView imageDetail;

    private final static long DURATION = 500;

    private ColorMatrix colorMatrix;
    private ColorMatrixColorFilter colorFilter;

    private int offsetY;
    private float scaleX, scaleY;
    private int left;
    private int top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.transparent);
        setContentView(R.layout.activity_picture_detail);
        ButterKnife.bind(this);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(rlImageDetail, "alpha", 0f, 1.0f);
        alpha.setDuration(DURATION);
        alpha.start();

        Intent extra = getIntent();
        String url = extra.getStringExtra(Constants.BUNDLE_PIC_URL);
        left = extra.getIntExtra(Constants.BUNDLE_PIC_LEFT, 0);
        top = extra.getIntExtra(Constants.BUNDLE_PIC_TOP, 0);
        final int width = extra.getIntExtra(Constants.BUNDLE_PIC_WIDTH, 0);
        final int height = extra.getIntExtra(Constants.BUNDLE_PIC_HEIGHT, 0);

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
                lp.height = (int) (scale * h);
                lp.width = Application.getInstance().SCREENWIDTH;
                imageDetail.setLayoutParams(lp);

                scaleX = width * 1.0f / lp.width;
                scaleY = height * 1.0f / lp.height;

                offsetY = (Application.getInstance().SCREENHEIGHT - lp.height) / 2;

                imageDetail.setPivotX(0);
                imageDetail.setPivotY(0);
                imageDetail.setScaleX(scaleX);
                imageDetail.setScaleY(scaleY);

                imageDetail.setTranslationX(left);
                imageDetail.setTranslationY(top - offsetY);

                imageDetail.setAlpha(0f);

                imageDetail.animate()
                        .alpha(1f)
                        .scaleX(1f).scaleY(1f)
                        .translationX(0).translationY(0)
                        .setDuration(DURATION);

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
        ObjectAnimator alpha = ObjectAnimator.ofFloat(rlImageDetail, "alpha", 1f, 0f);
        alpha.setDuration(DURATION);
        alpha.start();

        ObjectAnimator saturation = ObjectAnimator.ofFloat(PictureDetailActivity.this, "saturation", 1f, 0f);
        saturation.setDuration(DURATION);
        saturation.start();

        imageDetail.setPivotX(0);
        imageDetail.setPivotY(0);
        imageDetail.setScaleX(1);
        imageDetail.setScaleY(1);

        imageDetail.setTranslationX(0);
        imageDetail.setTranslationY(0);

        imageDetail.setAlpha(1f);

        imageDetail.animate()
                .alpha(0f)
                .scaleX(scaleX).scaleY(scaleY)
                .translationX(left).translationY(top - offsetY)
                .setDuration(DURATION).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(0, 0);
    }

}
