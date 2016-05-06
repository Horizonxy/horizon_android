package com.horizon.android.activity;

import com.horizon.android.adapter.MessageAdapter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ListViewActivity_MembersInjector implements MembersInjector<ListViewActivity> {
  private final MembersInjector<BaseActivity> supertypeInjector;
  private final Provider<MessageAdapter> adapterProvider;

  public ListViewActivity_MembersInjector(MembersInjector<BaseActivity> supertypeInjector, Provider<MessageAdapter> adapterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert adapterProvider != null;
    this.adapterProvider = adapterProvider;
  }

  @Override
  public void injectMembers(ListViewActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.adapter = adapterProvider.get();
  }

  public static MembersInjector<ListViewActivity> create(MembersInjector<BaseActivity> supertypeInjector, Provider<MessageAdapter> adapterProvider) {  
      return new ListViewActivity_MembersInjector(supertypeInjector, adapterProvider);
  }
}

