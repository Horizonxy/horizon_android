// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WelcomeActivity$$ViewBinder<T extends com.horizon.android.activity.WelcomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558537, "field 'vWelcome'");
    target.vWelcome = finder.castView(view, 2131558537, "field 'vWelcome'");
  }

  @Override public void unbind(T target) {
    target.vWelcome = null;
  }
}
