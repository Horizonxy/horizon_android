package com.horizon.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.horizon.android.R;
import com.horizon.android.util.SystemStatusManager;

import butterknife.ButterKnife;

public class RadarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemStatusManager.setTranslucentStatus(this, R.color.transparent);
        setContentView(R.layout.activity_radar);
        ButterKnife.bind(this);
    }
}
