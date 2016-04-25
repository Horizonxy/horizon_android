package com.horizon.android.component;

import com.horizon.android.activity.MovieListActivity;
import com.horizon.android.activity.MovieListActivity_MembersInjector;
import com.horizon.android.adapter.MovieListAdapter;
import com.horizon.android.module.MovieListModule;
import com.horizon.android.module.MovieListModule_ProvideMovieListAdapterFactory;
import com.horizon.android.module.MovieListModule_ProvideMoviePresenterFactory;
import com.horizon.android.presenter.MoviePresenter;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerMovieListActivityComponent implements MovieListActivityComponent {
  private Provider<MoviePresenter> provideMoviePresenterProvider;
  private Provider<MovieListAdapter> provideMovieListAdapterProvider;
  private MembersInjector<MovieListActivity> movieListActivityMembersInjector;

  private DaggerMovieListActivityComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideMoviePresenterProvider = ScopedProvider.create(MovieListModule_ProvideMoviePresenterFactory.create(builder.movieListModule));
    this.provideMovieListAdapterProvider = ScopedProvider.create(MovieListModule_ProvideMovieListAdapterFactory.create(builder.movieListModule));
    this.movieListActivityMembersInjector = MovieListActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideMoviePresenterProvider, provideMovieListAdapterProvider);
  }

  @Override
  public MovieListActivity inject(MovieListActivity activity) {  
    movieListActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  public static final class Builder {
    private MovieListModule movieListModule;
  
    private Builder() {  
    }
  
    public MovieListActivityComponent build() {  
      if (movieListModule == null) {
        throw new IllegalStateException("movieListModule must be set");
      }
      return new DaggerMovieListActivityComponent(this);
    }
  
    public Builder movieListModule(MovieListModule movieListModule) {  
      if (movieListModule == null) {
        throw new NullPointerException("movieListModule");
      }
      this.movieListModule = movieListModule;
      return this;
    }
  }
}

