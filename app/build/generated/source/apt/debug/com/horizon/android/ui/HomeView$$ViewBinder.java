// Generated code from Butter Knife. Do not modify!
package com.horizon.android.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeView$$ViewBinder<T extends com.horizon.android.ui.HomeView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558584, "field 'ptrFrame'");
    target.ptrFrame = finder.castView(view, 2131558584, "field 'ptrFrame'");
    view = finder.findRequiredView(source, 2131558594, "method 'weatherClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.weatherClick();
        }
      });
    view = finder.findRequiredView(source, 2131558593, "method 'movieListClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.movieListClick();
        }
      });
    view = finder.findRequiredView(source, 2131558592, "method 'retrofitRxjavaMvpClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.retrofitRxjavaMvpClick();
        }
      });
    view = finder.findRequiredView(source, 2131558591, "method 'mvpUserInfoClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.mvpUserInfoClick();
        }
      });
    view = finder.findRequiredView(source, 2131558590, "method 'mvpLoginnClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.mvpLoginnClick();
        }
      });
    view = finder.findRequiredView(source, 2131558589, "method 'retrofitRxjavaClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.retrofitRxjavaClick();
        }
      });
    view = finder.findRequiredView(source, 2131558588, "method 'autoLoadClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.autoLoadClick();
        }
      });
    view = finder.findRequiredView(source, 2131558587, "method 'zoomListClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.zoomListClick();
        }
      });
    view = finder.findRequiredView(source, 2131558586, "method 'zoomScrollClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.zoomScrollClick();
        }
      });
    view = finder.findRequiredView(source, 2131558581, "method 'leftClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.leftClick();
        }
      });
    view = finder.findRequiredView(source, 2131558585, "method 'caputerClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.caputerClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.ptrFrame = null;
  }
}
