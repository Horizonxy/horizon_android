// Generated code from Butter Knife. Do not modify!
package com.horizon.android.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeView$$ViewBinder<T extends com.horizon.android.ui.HomeView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558596, "field 'ptrFrame'");
    target.ptrFrame = finder.castView(view, 2131558596, "field 'ptrFrame'");
    view = finder.findRequiredView(source, 2131558612, "method 'editClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.editClick();
        }
      });
    view = finder.findRequiredView(source, 2131558611, "method 'clipClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clipClick();
        }
      });
    view = finder.findRequiredView(source, 2131558608, "method 'scrollviewClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.scrollviewClick();
        }
      });
    view = finder.findRequiredView(source, 2131558610, "method 'layoutStatusClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.layoutStatusClick();
        }
      });
    view = finder.findRequiredView(source, 2131558609, "method 'frameClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.frameClick();
        }
      });
    view = finder.findRequiredView(source, 2131558607, "method 'listviewClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.listviewClick();
        }
      });
    view = finder.findRequiredView(source, 2131558606, "method 'weatherClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.weatherClick();
        }
      });
    view = finder.findRequiredView(source, 2131558605, "method 'movieListClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.movieListClick();
        }
      });
    view = finder.findRequiredView(source, 2131558604, "method 'retrofitRxjavaMvpClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.retrofitRxjavaMvpClick();
        }
      });
    view = finder.findRequiredView(source, 2131558603, "method 'mvpUserInfoClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.mvpUserInfoClick();
        }
      });
    view = finder.findRequiredView(source, 2131558602, "method 'mvpLoginnClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.mvpLoginnClick();
        }
      });
    view = finder.findRequiredView(source, 2131558601, "method 'retrofitRxjavaClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.retrofitRxjavaClick();
        }
      });
    view = finder.findRequiredView(source, 2131558600, "method 'autoLoadClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.autoLoadClick();
        }
      });
    view = finder.findRequiredView(source, 2131558599, "method 'zoomListClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.zoomListClick();
        }
      });
    view = finder.findRequiredView(source, 2131558598, "method 'zoomScrollClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.zoomScrollClick();
        }
      });
    view = finder.findRequiredView(source, 2131558545, "method 'leftClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.leftClick();
        }
      });
    view = finder.findRequiredView(source, 2131558594, "method 'right1Click'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.right1Click();
        }
      });
    view = finder.findRequiredView(source, 2131558595, "method 'right2Click'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.right2Click();
        }
      });
    view = finder.findRequiredView(source, 2131558597, "method 'caputerClick'");
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
