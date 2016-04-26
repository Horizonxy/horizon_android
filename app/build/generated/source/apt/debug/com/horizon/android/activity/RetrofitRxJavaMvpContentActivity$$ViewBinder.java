// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RetrofitRxJavaMvpContentActivity$$ViewBinder<T extends com.horizon.android.activity.RetrofitRxJavaMvpContentActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558534, "field 'btnGetInfo' and method 'getInfoClick'");
    target.btnGetInfo = finder.castView(view, 2131558534, "field 'btnGetInfo'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.getInfoClick();
        }
      });
    view = finder.findRequiredView(source, 2131558522, "field 'tvResult'");
    target.tvResult = finder.castView(view, 2131558522, "field 'tvResult'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.btnGetInfo = null;
    target.tvResult = null;
  }
}
