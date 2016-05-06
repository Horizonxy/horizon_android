// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BaseActivity$$ViewBinder<T extends com.horizon.android.activity.BaseActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558546, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131558546, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131558545, "field 'btnLeft' and method 'leftClick'");
    target.btnLeft = finder.castView(view, 2131558545, "field 'btnLeft'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.leftClick();
        }
      });
    view = finder.findRequiredView(source, 2131558594, "field 'btnRight1'");
    target.btnRight1 = finder.castView(view, 2131558594, "field 'btnRight1'");
    view = finder.findRequiredView(source, 2131558595, "field 'btnRight2'");
    target.btnRight2 = finder.castView(view, 2131558595, "field 'btnRight2'");
    view = finder.findRequiredView(source, 2131558591, "field 'mInitLoad'");
    target.mInitLoad = finder.castView(view, 2131558591, "field 'mInitLoad'");
    view = finder.findRequiredView(source, 2131558592, "field 'llNoNet'");
    target.llNoNet = finder.castView(view, 2131558592, "field 'llNoNet'");
    view = finder.findRequiredView(source, 2131558593, "field 'btnRetry' and method 'clickRetry'");
    target.btnRetry = finder.castView(view, 2131558593, "field 'btnRetry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clickRetry();
        }
      });
  }

  @Override public void unbind(T target) {
    target.tvTitle = null;
    target.btnLeft = null;
    target.btnRight1 = null;
    target.btnRight2 = null;
    target.mInitLoad = null;
    target.llNoNet = null;
    target.btnRetry = null;
  }
}
