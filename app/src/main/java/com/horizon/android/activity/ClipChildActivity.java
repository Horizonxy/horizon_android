package com.horizon.android.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.horizon.android.R;

import butterknife.Bind;
import butterknife.OnClick;

public class ClipChildActivity extends BaseActivity {

    @Bind(R.id.iv_like)
    ImageView ivLike;
    @Bind(R.id.iv_collection)
    ImageView ivCollection;
    @Bind(R.id.tv_like_num)
    TextView tvLikeNum;
    @Bind(R.id.tv_collection_num)
    TextView tvCollectionNum;
    @Bind(R.id.ll_like)
    LinearLayout llLike;
    @Bind(R.id.ll_collection)
    LinearLayout llCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_child);
        setTitle("收藏点赞");
    }

    @OnClick(R.id.ll_like)
    void likeClick(){
        llLike.setClickable(false);
        ScaleAnimation scale = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(1000);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int num = Integer.parseInt(tvLikeNum.getText().toString());
                tvLikeNum.setText(String.valueOf(num+1));
                llLike.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ivLike.startAnimation(scale);
    }

    @OnClick(R.id.ll_collection)
    void collectionClick(){
        llCollection.setClickable(false);
        ScaleAnimation scale = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(1000);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int num = Integer.parseInt(tvCollectionNum.getText().toString());
                tvCollectionNum.setText(String.valueOf(num+1));
                llCollection.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ivCollection.startAnimation(scale);
    }
}
