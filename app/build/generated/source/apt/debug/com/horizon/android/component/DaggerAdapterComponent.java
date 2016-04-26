package com.horizon.android.component;

import com.horizon.android.activity.AutoLoadActivity;
import com.horizon.android.activity.AutoLoadActivity_MembersInjector;
import com.horizon.android.activity.ListViewActivity;
import com.horizon.android.activity.ListViewActivity_MembersInjector;
import com.horizon.android.activity.PullToZoomListActivity;
import com.horizon.android.activity.PullToZoomListActivity_MembersInjector;
import com.horizon.android.adapter.MessageAdapter;
import com.horizon.android.module.AdapterModule;
import com.horizon.android.module.AdapterModule_ProvideMessageAdapterFactory;
import com.horizon.android.ui.MessageView;
import com.horizon.android.ui.MessageView_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerAdapterComponent implements AdapterComponent {
  private Provider<MessageAdapter> provideMessageAdapterProvider;
  private MembersInjector<MessageView> messageViewMembersInjector;
  private MembersInjector<PullToZoomListActivity> pullToZoomListActivityMembersInjector;
  private MembersInjector<AutoLoadActivity> autoLoadActivityMembersInjector;
  private MembersInjector<ListViewActivity> listViewActivityMembersInjector;

  private DaggerAdapterComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideMessageAdapterProvider = ScopedProvider.create(AdapterModule_ProvideMessageAdapterFactory.create(builder.adapterModule));
    this.messageViewMembersInjector = MessageView_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideMessageAdapterProvider);
    this.pullToZoomListActivityMembersInjector = PullToZoomListActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideMessageAdapterProvider);
    this.autoLoadActivityMembersInjector = AutoLoadActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideMessageAdapterProvider);
    this.listViewActivityMembersInjector = ListViewActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideMessageAdapterProvider);
  }

  @Override
  public MessageView inject(MessageView messageView) {  
    messageViewMembersInjector.injectMembers(messageView);
    return messageView;
  }

  @Override
  public PullToZoomListActivity inject(PullToZoomListActivity activity) {  
    pullToZoomListActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  @Override
  public AutoLoadActivity inject(AutoLoadActivity activity) {  
    autoLoadActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  @Override
  public ListViewActivity inject(ListViewActivity activity) {  
    listViewActivityMembersInjector.injectMembers(activity);
    return activity;
  }

  public static final class Builder {
    private AdapterModule adapterModule;
  
    private Builder() {  
    }
  
    public AdapterComponent build() {  
      if (adapterModule == null) {
        throw new IllegalStateException("adapterModule must be set");
      }
      return new DaggerAdapterComponent(this);
    }
  
    public Builder adapterModule(AdapterModule adapterModule) {  
      if (adapterModule == null) {
        throw new NullPointerException("adapterModule");
      }
      this.adapterModule = adapterModule;
      return this;
    }
  }
}

