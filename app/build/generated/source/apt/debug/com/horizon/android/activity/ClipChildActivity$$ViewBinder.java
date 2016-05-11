// Generated code from Butter Knife. Do not modify!
package com.horizon.android.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ClipChildActivity$$ViewBinder<T extends com.horizon.android.activity.ClipChildActivity> extends com.horizon.android.activity.BaseActivity$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131558515, "field 'ivLike'");
    target.ivLike = finder.castView(view, 2131558515, "field 'ivLike'");
    view = finder.findRequiredView(source, 2131558518, "field 'ivCollection'");
    target.ivCollection = finder.castView(view, 2131558518, "field 'ivCollection'");
    view = finder.findRequiredView(source, 2131558516, "field 'tvLikeNum'");
    target.tvLikeNum = finder.castView(view, 2131558516, "field 'tvLikeNum'");
    view = finder.findRequiredView(source, 2131558519, "field 'tvCollectionNum'");
    target.tvCollectionNum = finder.castView(view, 2131558519, "field 'tvCollectionNum'");
    view = finder.findRequiredView(source, 2131558514, "field 'llLike' and method 'likeClick'");
    target.llLike = finder.castView(view, 2131558514, "field 'llLike'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.likeClick();
        }
      });
    view = finder.findRequiredView(source, 2131558517, "field 'llCollection' and method 'collectionClick'");
    target.llCollection = finder.castView(view, 2131558517, "field 'llCollection'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.collectionClick();
        }
      });
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.ivLike = null;
    target.ivCollection = null;
    target.tvLikeNum = null;
    target.tvCollectionNum = null;
    target.llLike = null;
    target.llCollection = null;
  }
}
