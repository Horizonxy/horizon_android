package com.horizon.android.module;

import android.content.Context;

import com.horizon.android.ActivityScope;
import com.horizon.android.adapter.MessageAdapter;
import java.util.List;
import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {
    private Context context;
    private int layoutId;
    private List data;

    public AdapterModule(Context context, int layoutId, List data){
        this.context = context;
        this.layoutId = layoutId;
        this.data = data;
    }

    @ActivityScope
    @Provides
    public MessageAdapter provideMessageAdapter(){
        return new MessageAdapter(context, layoutId, data);
    }
}
