// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class StatusLayoutActivity$$ViewBinder<T extends com.horizon.android.activity.StatusLayoutActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558550, "field 'btnBack' and method 'backClick'");
    target.btnBack = finder.castView(view, 2131558550, "field 'btnBack'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.backClick();
        }
      });
    view = finder.findRequiredView(source, 2131558551, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131558551, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131558549, "field 'rlTop'");
    target.rlTop = finder.castView(view, 2131558549, "field 'rlTop'");
    view = finder.findRequiredView(source, 2131558548, "field 'ivBanner'");
    target.ivBanner = finder.castView(view, 2131558548, "field 'ivBanner'");
  }

  @Override public void unbind(T target) {
    target.btnBack = null;
    target.tvTitle = null;
    target.rlTop = null;
    target.ivBanner = null;
  }
}
