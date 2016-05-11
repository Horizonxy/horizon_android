// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AnimationFrameActivity$$ViewBinder<T extends com.horizon.android.activity.AnimationFrameActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558511, "field 'aflLayout'");
    target.aflLayout = finder.castView(view, 2131558511, "field 'aflLayout'");
    view = finder.findRequiredView(source, 2131558512, "method 'functionClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.functionClick();
        }
      });
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.aflLayout = null;
  }
}
