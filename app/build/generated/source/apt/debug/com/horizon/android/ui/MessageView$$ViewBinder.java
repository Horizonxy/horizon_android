// Generated code from Butter Knife. Do not modify!
package com.horizon.android.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MessageView$$ViewBinder<T extends com.horizon.android.ui.MessageView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558598, "field 'lvMessage'");
    target.lvMessage = finder.castView(view, 2131558598, "field 'lvMessage'");
    view = finder.findRequiredView(source, 2131558584, "field 'ptrFrame'");
    target.ptrFrame = finder.castView(view, 2131558584, "field 'ptrFrame'");
  }

  @Override public void unbind(T target) {
    target.lvMessage = null;
    target.ptrFrame = null;
  }
}
