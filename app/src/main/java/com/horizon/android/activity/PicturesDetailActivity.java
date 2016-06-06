package com.horizon.android.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.util.SimpleAnimatorListener;
import com.horizon.android.util.SmallPicInfo;
import com.horizon.android.util.SystemStatusManager;
import com.horizon.android.util.log.LogUtils;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PicturesDetailActivity extends AutoLayoutActivity {

    @Bind(R.id.vp_pictures)
    ViewPager vpPictures;
    @Bind(R.id.fl_pictures)
    AutoFrameLayout flPictures;

    private final static long DURATION = 1000;
    private int pos;

    private PictureAdapter adapter;

    private float scaleX, scaleY;
    private int initWidth, initHeight;
    private int deltaX, deltaY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.transparent);
        setContentView(R.layout.activity_pictures_detail);
        ButterKnife.bind(this);

        ObjectAnimator alpha = ObjectAnimator.ofFloat(flPictures, "alpha", 0f, 1.0f);
        alpha.setDuration(DURATION);
        alpha.start();

        final List<SmallPicInfo> picInfos = (List<SmallPicInfo>) getIntent().getSerializableExtra(Constants.BUNDLE_PIC_INFOS);
        pos = getIntent().getIntExtra(Constants.BUNDLE_PIC_POS, 0);

        LogUtils.e("size: " + picInfos.size() + " pos:" + pos);

        vpPictures.setAdapter(adapter = new PictureAdapter(picInfos));

        vpPictures.setCurrentItem(pos);
    }

    @Override
    public void onBackPressed() {
        flPictures.setBackgroundColor(getResources().getColor(R.color.transparent));

        ImageView imageView = adapter.getViewAtPos(vpPictures.getCurrentItem());

        ViewPager.LayoutParams lp = (ViewPager.LayoutParams) imageView.getLayoutParams();
        lp.width = initWidth;
        lp.height = initHeight;
        imageView.setLayoutParams(lp);

        imageView.setPivotX(0);
        imageView.setPivotY(0);
        imageView.setScaleX(1);
        imageView.setScaleY(1);

        imageView.animate()
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

    class PictureAdapter extends PagerAdapter {

        private List<SmallPicInfo> picInfos;
        private SparseArray<ImageView> itemViews;

        public PictureAdapter(List<SmallPicInfo> picInfos) {
            this.picInfos = picInfos;
            this.itemViews = new SparseArray<ImageView>();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            LogUtils.e("instantiateItem : " + position);

            ImageView item = itemViews.get(position);
            if(item == null) {
                item = new ImageView(PicturesDetailActivity.this);
                itemViews.put(position, item);

                item.setLayoutParams(new ViewPager.LayoutParams());

                final PhotoViewAttacher attacher = new PhotoViewAttacher(item);

                final SmallPicInfo smallPicInfo = picInfos.get(position);

                Application.getInstance().getImageLoader().displayImage(smallPicInfo.url, (ImageView) item, Application.getInstance().getDefaultOptions(), new SimpleImageLoadingListener(){
                    @Override
                    public void onLoadingComplete(String imageUri, View view, final Bitmap loadedImage) {
                        flPictures.setBackgroundColor(getResources().getColor(R.color.black));

                        if(position == pos) {
                            final ImageView imageView = itemViews.get(position);
                            imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                @Override
                                public void onGlobalLayout() {
                                    imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                                    int w = loadedImage.getWidth();
                                    int h = loadedImage.getHeight();
                                    float scale = Application.getInstance().SCREENWIDTH * 1.0f / w;

                                    ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
                                    lp.height = initHeight = (int) (scale * h);
                                    lp.width = initWidth = Application.getInstance().SCREENWIDTH;
                                    imageView.setLayoutParams(lp);

                                    SmallPicInfo smallPicInfo1 = picInfos.get(position);

                                    scaleX = smallPicInfo1.width * 1.0f / initWidth;
                                    scaleY = smallPicInfo1.height * 1.0f / initHeight;

                                    int[] location = new int[2];
                                    imageView.getLocationOnScreen(location);
                                    deltaX = smallPicInfo.left - location[0];
                                    deltaY = smallPicInfo.top - location[1] - lp.height / 2;

                                    imageView.setPivotX(0);
                                    imageView.setPivotY(0);
                                    imageView.setScaleX(scaleX);
                                    imageView.setScaleY(scaleY);

                                    imageView.setTranslationX(deltaX);
                                    imageView.setTranslationY(deltaY);

                                    imageView.animate()
                                            .scaleX(1f).scaleY(1f)
                                            .translationX(0).translationY(0)
                                            .setDuration(DURATION).setListener(new SimpleAnimatorListener(){
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            ViewPager.LayoutParams lp = (ViewPager.LayoutParams) imageView.getLayoutParams();
                                            lp.height = Application.getInstance().SCREENHEIGHT;
                                            lp.width = Application.getInstance().SCREENWIDTH;
                                            imageView.setLayoutParams(lp);
                                        }
                                    });
                                }
                            });
                        }

                        attacher.update();
                    }
                });

            }
            container.addView(item);
            return item;
        }

        public ImageView getViewAtPos(int pos){
            return itemViews.get(pos);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(itemViews.get(position));
        }

        @Override
        public int getCount() {
            return picInfos.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}

