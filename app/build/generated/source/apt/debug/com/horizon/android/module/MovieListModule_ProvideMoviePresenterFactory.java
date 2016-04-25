package com.horizon.android.module;

import com.horizon.android.presenter.MoviePresenter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MovieListModule_ProvideMoviePresenterFactory implements Factory<MoviePresenter> {
  private final MovieListModule module;

  public MovieListModule_ProvideMoviePresenterFactory(MovieListModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public MoviePresenter get() {  
    MoviePresenter provided = module.provideMoviePresenter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<MoviePresenter> create(MovieListModule module) {  
    return new MovieListModule_ProvideMoviePresenterFactory(module);
  }
}

