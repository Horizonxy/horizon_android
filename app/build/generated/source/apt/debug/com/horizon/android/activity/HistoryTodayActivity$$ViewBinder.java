// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class HistoryTodayActivity$$ViewBinder<T extends com.horizon.android.activity.HistoryTodayActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558523, "field 'lvHistory'");
    target.lvHistory = finder.castView(view, 2131558523, "field 'lvHistory'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.lvHistory = null;
  }
}
