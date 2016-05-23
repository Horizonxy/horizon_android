package com.horizon.android.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.widget.pulltozoom.PullToZoomScrollViewEx;
import butterknife.Bind;

public class PullToZoomScrollActivity extends BaseActivity {

    @Bind(R.id.scroll_view)
    PullToZoomScrollViewEx scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_scroll_view);
        setTitle("ZoomScroll");
        
        loadViewForCode();
        scrollView.getPullRootView().findViewById(R.id.tv_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zhuwenwu", "onClick -->");
            }
        });

        scrollView.getPullRootView().findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zhuwenwu", "onClick -->");
            }
        });

        scrollView.getPullRootView().findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zhuwenwu", "onClick -->");
            }
        });

        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(Application.getInstance().SCREENWIDTH,
                (int) (9.0F * (Application.getInstance().SCREENWIDTH / 16.0F)));
        scrollView.setHeaderLayoutParams(localObject);
    }

    private void loadViewForCode() {
        LayoutInflater inflater = getLayoutInflater();
        View headView = inflater.inflate(R.layout.profile_head_view, null, false);
        View zoomView = inflater.inflate(R.layout.profile_zoom_view, null, false);
        View contentView = inflater.inflate(R.layout.profile_content_view, null, false);
        scrollView.setHeaderView(headView);
        scrollView.setZoomView(zoomView);
        scrollView.setScrollContentView(contentView);
    }
}
