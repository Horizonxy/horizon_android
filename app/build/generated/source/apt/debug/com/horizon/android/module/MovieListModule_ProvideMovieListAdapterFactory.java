package com.horizon.android.module;

import com.horizon.android.adapter.MovieListAdapter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MovieListModule_ProvideMovieListAdapterFactory implements Factory<MovieListAdapter> {
  private final MovieListModule module;

  public MovieListModule_ProvideMovieListAdapterFactory(MovieListModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public MovieListAdapter get() {  
    MovieListAdapter provided = module.provideMovieListAdapter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<MovieListAdapter> create(MovieListModule module) {  
    return new MovieListModule_ProvideMovieListAdapterFactory(module);
  }
}

