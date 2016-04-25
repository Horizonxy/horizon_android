package com.horizon.android.activity;

import com.horizon.android.presenter.ContentListPresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class RetrofitRxJavaMvpContentActivity_MembersInjector implements MembersInjector<RetrofitRxJavaMvpContentActivity> {
  private final MembersInjector<BaseActivity> supertypeInjector;
  private final Provider<ContentListPresenter> mContentListPersenterProvider;

  public RetrofitRxJavaMvpContentActivity_MembersInjector(MembersInjector<BaseActivity> supertypeInjector, Provider<ContentListPresenter> mContentListPersenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert mContentListPersenterProvider != null;
    this.mContentListPersenterProvider = mContentListPersenterProvider;
  }

  @Override
  public void injectMembers(RetrofitRxJavaMvpContentActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.mContentListPersenter = mContentListPersenterProvider.get();
  }

  public static MembersInjector<RetrofitRxJavaMvpContentActivity> create(MembersInjector<BaseActivity> supertypeInjector, Provider<ContentListPresenter> mContentListPersenterProvider) {  
      return new RetrofitRxJavaMvpContentActivity_MembersInjector(supertypeInjector, mContentListPersenterProvider);
  }
}

