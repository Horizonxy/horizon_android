package com.horizon.android.activity;

import android.os.Bundle;
import android.view.View;

import com.horizon.android.R;
import com.horizon.android.widget.AnimationFrameLayout;

import butterknife.Bind;
import butterknife.OnClick;

public class AnimationFrameActivity extends BaseActivity {

    @Bind(R.id.afl_layout)
    AnimationFrameLayout aflLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_framelayout);
        setTitle("Animation Frame");

    }

    @OnClick(R.id.btn_function)
    void functionClick() {
        if (aflLayout.getVisibility() != View.GONE) {
            aflLayout.setAnimationVisibility(View.GONE);
        } else {
            aflLayout.setAnimationVisibility(View.VISIBLE);
        }
    }

}
