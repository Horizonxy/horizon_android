// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RetrofitRxActivity$$ViewBinder<T extends com.horizon.android.activity.RetrofitRxActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558547, "field 'btnReq' and method 'reqClick'");
    target.btnReq = finder.castView(view, 2131558547, "field 'btnReq'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.reqClick();
        }
      });
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.btnReq = null;
  }
}
