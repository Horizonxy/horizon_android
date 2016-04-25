package com.horizon.android.ui;

import com.horizon.android.adapter.MessageAdapter;
import com.zhy.autolayout.AutoLinearLayout;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MessageView_MembersInjector implements MembersInjector<MessageView> {
  private final MembersInjector<AutoLinearLayout> supertypeInjector;
  private final Provider<MessageAdapter> adapterProvider;

  public MessageView_MembersInjector(MembersInjector<AutoLinearLayout> supertypeInjector, Provider<MessageAdapter> adapterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert adapterProvider != null;
    this.adapterProvider = adapterProvider;
  }

  @Override
  public void injectMembers(MessageView instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.adapter = adapterProvider.get();
  }

  public static MembersInjector<MessageView> create(MembersInjector<AutoLinearLayout> supertypeInjector, Provider<MessageAdapter> adapterProvider) {  
      return new MessageView_MembersInjector(supertypeInjector, adapterProvider);
  }
}

