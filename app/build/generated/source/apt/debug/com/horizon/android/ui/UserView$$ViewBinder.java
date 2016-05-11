// Generated code from Butter Knife. Do not modify!
package com.horizon.android.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UserView$$ViewBinder<T extends com.horizon.android.ui.UserView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558604, "method 'right1Click'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.right1Click();
        }
      });
    view = finder.findRequiredView(source, 2131558550, "method 'leftClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.leftClick();
        }
      });
  }

  @Override public void unbind(T target) {
  }
}
