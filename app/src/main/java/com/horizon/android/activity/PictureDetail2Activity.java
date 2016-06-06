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
import com.horizon.android.util.DisplayUtils;
import com.horizon.android.util.SimpleAnimatorListener;
import com.horizon.android.util.SmallPicInfo;
import com.horizon.android.util.SystemStatusManager;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureDetail2Activity extends Activity {

    @Bind(R.id.rl_root)
    AutoRelativeLayout rlRoot;
    @Bind(R.id.image_detail)
    ImageView ivDetail;
    @Bind(R.id.progress_load)
    ProgressBar progressLoad;

    SmallPicInfo smallPicInfo;

    private static final int DURATION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.transparent);
        setContentView(R.layout.activity_picture_detail2);
        ButterKnife.bind(this);

        rlRoot.setAlpha(0f);
        rlRoot.animate().alpha(1f).setDuration(DURATION);

        smallPicInfo = (SmallPicInfo) getIntent().getSerializableExtra(Constants.BUNDLE_PIC_INFOS);

        final PhotoViewAttacher attacher = new PhotoViewAttacher(ivDetail);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivDetail.getLayoutParams();
        lp.width = smallPicInfo.width;
        lp.height = smallPicInfo.height;
        ivDetail.setLayoutParams(lp);

        Bitmap small = BitmapFactory.decodeByteArray(smallPicInfo.bmp, 0, smallPicInfo.bmp.length);
        ivDetail.setImageBitmap(small);

        int smallDeltaX = smallPicInfo.left - (Application.getInstance().SCREENWIDTH - smallPicInfo.width) / 2 ;
        int smallDeltaY = smallPicInfo.top - (Application.getInstance().SCREENHEIGHT - smallPicInfo.height + DisplayUtils.getStatusBarHeight(PictureDetail2Activity.this)) / 2;

        ivDetail.setPivotX(0);
        ivDetail.setPivotY(0);
        ivDetail.setTranslationX(smallDeltaX);
        ivDetail.setTranslationY(smallDeltaY);

        ivDetail.animate().translationX(0).translationY(0).setDuration(DURATION).setListener(new SimpleAnimatorListener(){
            @Override
            public void onAnimationEnd(Animator animation) {
                progressLoad.setVisibility(View.VISIBLE);

                Application.getInstance().getImageLoader().displayImage(smallPicInfo.url, ivDetail, new SimpleImageLoadingListener(){

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressLoad.setVisibility(View.GONE);
                        attacher.update();

                        float scaleX = Application.getInstance().SCREENWIDTH * 1f  / smallPicInfo.width;
                        float scaleY = Application.getInstance().SCREENHEIGHT * 1f / smallPicInfo.height;
                        float scale = Math.min(scaleX, scaleY);

                        ivDetail.setPivotX(smallPicInfo.width / 2);
                        ivDetail.setPivotY(smallPicInfo.height / 2);

                        ivDetail.animate().scaleX(scale).scaleY(scale).setDuration(DURATION).setListener(new SimpleAnimatorListener(){
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                ivDetail.setScaleX(1f);
                                ivDetail.setScaleY(1f);
                                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ivDetail.getLayoutParams();
                                lp.height = Application.getInstance().SCREENHEIGHT;
                                lp.width = Application.getInstance().SCREENWIDTH;
                                ivDetail.setLayoutParams(lp);
                            }
                        });
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        progressLoad.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        rlRoot.animate().alpha(0.4f).setDuration(DURATION);


        float scaleX = smallPicInfo.width * 1f / ivDetail.getWidth();
        float scaleY = smallPicInfo.height * 1f / ivDetail.getHeight();
        float scale = Math.max(scaleX, scaleY);


        int deltaX = -smallPicInfo.left;
        int deltaY = smallPicInfo.top;

        ivDetail.setPivotX(0);
        ivDetail.setPivotY(0);
        ivDetail.setScaleX(1f);
        ivDetail.setScaleY(1f);

        ivDetail.animate().scaleX(scale).scaleY(scale).translationY(deltaX).translationY(deltaY).setDuration(DURATION).setListener(new SimpleAnimatorListener(){
            @Override
            public void onAnimationEnd(Animator animation) {
                PictureDetail2Activity.super.onBackPressed();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(0, 0);
    }
}
