package com.horizon.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.horizon.android.R;
import com.horizon.android.util.ToastUtils;

import butterknife.Bind;

public class TranglePopupActivity extends BaseActivity {

    @Bind(R.id.btn_open)
    Button btnOpen;

    private static final String TEXT1 = "内容1";
    private static final String TEXT2 = "内容2";

    ClickListener clickListener = new ClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangle_popup);

        btnOpen.setOnClickListener(clickListener);

    }

    class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_demo1:
                    ToastUtils.show(TranglePopupActivity.this, TEXT1);
                    break;
                case R.id.tv_demo2:
                    ToastUtils.show(TranglePopupActivity.this, TEXT2);
                    break;
                case R.id.btn_open:
                    PopupWindow popupWindow = new PopupWindow(TranglePopupActivity.this, R.layout.view_popup_demo);
                    //popupWindow.showAsDropDown(v, 0, 2, Gravity.CENTER);
                    popupWindow.showAsDropDownCenter(v);
                    break;
            }
        }
    }

    class PopupWindow extends com.horizon.android.widget.PopupWindow {

        public PopupWindow(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindView(View root) {
            TextView tv1 = (TextView) root.findViewById(R.id.tv_demo1);
            TextView tv2 = (TextView) root.findViewById(R.id.tv_demo2);

            tv1.setText(TEXT1);
            tv2.setText(TEXT2);

            tv1.setOnClickListener(clickListener);
            tv2.setOnClickListener(clickListener);
        }
    }
}
