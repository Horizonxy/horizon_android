// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class PullToZoomScrollActivity$$ViewBinder<T extends com.horizon.android.activity.PullToZoomScrollActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558538, "field 'scrollView'");
    target.scrollView = finder.castView(view, 2131558538, "field 'scrollView'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.scrollView = null;
  }
}
