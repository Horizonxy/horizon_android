// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.horizon.android.activity.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558516, "field 'vpMain'");
    target.vpMain = finder.castView(view, 2131558516, "field 'vpMain'");
    view = finder.findRequiredView(source, 2131558512, "field 'home' and method 'homeClick'");
    target.home = finder.castView(view, 2131558512, "field 'home'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.homeClick();
        }
      });
    view = finder.findRequiredView(source, 2131558513, "field 'message' and method 'messageClick'");
    target.message = finder.castView(view, 2131558513, "field 'message'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.messageClick();
        }
      });
    view = finder.findRequiredView(source, 2131558514, "field 'shoppingCar' and method 'shoppingCarClick'");
    target.shoppingCar = finder.castView(view, 2131558514, "field 'shoppingCar'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.shoppingCarClick();
        }
      });
    view = finder.findRequiredView(source, 2131558515, "field 'user' and method 'userClick'");
    target.user = finder.castView(view, 2131558515, "field 'user'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.userClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.vpMain = null;
    target.home = null;
    target.message = null;
    target.shoppingCar = null;
    target.user = null;
  }
}
