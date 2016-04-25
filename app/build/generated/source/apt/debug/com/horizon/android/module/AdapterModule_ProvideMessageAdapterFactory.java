package com.horizon.android.module;

import com.horizon.android.adapter.MessageAdapter;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class AdapterModule_ProvideMessageAdapterFactory implements Factory<MessageAdapter> {
  private final AdapterModule module;

  public AdapterModule_ProvideMessageAdapterFactory(AdapterModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public MessageAdapter get() {  
    MessageAdapter provided = module.provideMessageAdapter();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<MessageAdapter> create(AdapterModule module) {  
    return new AdapterModule_ProvideMessageAdapterFactory(module);
  }
}

