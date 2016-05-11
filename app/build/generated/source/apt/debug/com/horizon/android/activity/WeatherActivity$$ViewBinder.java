// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class WeatherActivity$$ViewBinder<T extends com.horizon.android.activity.WeatherActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558553, "field 'etCityName'");
    target.etCityName = finder.castView(view, 2131558553, "field 'etCityName'");
    view = finder.findRequiredView(source, 2131558536, "field 'tvResult'");
    target.tvResult = finder.castView(view, 2131558536, "field 'tvResult'");
    view = finder.findRequiredView(source, 2131558554, "method 'weacherClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.weacherClick();
        }
      });
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.etCityName = null;
    target.tvResult = null;
  }
}
