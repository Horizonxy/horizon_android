package com.horizon.android.activity;

import com.horizon.android.adapter.MovieListAdapter;
import com.horizon.android.presenter.MoviePresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MovieListActivity_MembersInjector implements MembersInjector<MovieListActivity> {
  private final MembersInjector<BaseActivity> supertypeInjector;
  private final Provider<MoviePresenter> presenterProvider;
  private final Provider<MovieListAdapter> adapterProvider;

  public MovieListActivity_MembersInjector(MembersInjector<BaseActivity> supertypeInjector, Provider<MoviePresenter> presenterProvider, Provider<MovieListAdapter> adapterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
    assert adapterProvider != null;
    this.adapterProvider = adapterProvider;
  }

  @Override
  public void injectMembers(MovieListActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
    instance.adapter = adapterProvider.get();
  }

  public static MembersInjector<MovieListActivity> create(MembersInjector<BaseActivity> supertypeInjector, Provider<MoviePresenter> presenterProvider, Provider<MovieListAdapter> adapterProvider) {  
      return new MovieListActivity_MembersInjector(supertypeInjector, presenterProvider, adapterProvider);
  }
}

