// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MVPLoginActivity$$ViewBinder<T extends com.horizon.android.activity.MVPLoginActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558528, "field 'etName'");
    target.etName = finder.castView(view, 2131558528, "field 'etName'");
    view = finder.findRequiredView(source, 2131558529, "field 'etPwd'");
    target.etPwd = finder.castView(view, 2131558529, "field 'etPwd'");
    view = finder.findRequiredView(source, 2131558530, "field 'btnLogin' and method 'loginClick'");
    target.btnLogin = finder.castView(view, 2131558530, "field 'btnLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.loginClick();
        }
      });
    view = finder.findRequiredView(source, 2131558531, "field 'tvResult'");
    target.tvResult = finder.castView(view, 2131558531, "field 'tvResult'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.etName = null;
    target.etPwd = null;
    target.btnLogin = null;
    target.tvResult = null;
  }
}
