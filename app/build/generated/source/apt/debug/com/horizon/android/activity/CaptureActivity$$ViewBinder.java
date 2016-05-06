// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CaptureActivity$$ViewBinder<T extends com.horizon.android.activity.CaptureActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558534, "field 'mContainer'");
    target.mContainer = finder.castView(view, 2131558534, "field 'mContainer'");
    view = finder.findRequiredView(source, 2131558537, "field 'mCropLayout'");
    target.mCropLayout = finder.castView(view, 2131558537, "field 'mCropLayout'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.mContainer = null;
    target.mCropLayout = null;
  }
}
