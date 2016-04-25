package com.horizon.android.ui;

import java.util.ArrayList;
import java.util.List;
import com.horizon.android.R;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import com.horizon.android.adapter.MessageAdapter;
import com.horizon.android.component.DaggerAdapterComponent;
import com.horizon.android.module.AdapterModule;
import com.horizon.android.util.ToastUtils;
import com.zhy.autolayout.AutoLinearLayout;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class MessageView extends AutoLinearLayout {

	@Bind(R.id.lv_message)
	ListView lvMessage;
	@Bind(R.id.ptr_frame)
	PtrClassicFrameLayout ptrFrame;
	private List<String> data = new ArrayList<String>();
	@Inject
	MessageAdapter adapter;

	public MessageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.bind(this);

		DaggerAdapterComponent.builder().adapterModule(new AdapterModule(getContext(), R.layout.item_message, data)).build().inject(this);
		lvMessage.setAdapter(adapter);

		for (int i = 0; i < 20; i++) {
			data.add("message " + i);
		}
		adapter.notifyDataSetChanged();

		ptrFrame.setLastUpdateTimeRelateObject(this);
		ptrFrame.setPtrHandler(new MessagePtrHandler());
	}

	class MessagePtrHandler implements PtrHandler {

		@Override
		public void onRefreshBegin(PtrFrameLayout frame) {
			data.clear();
			for (int i = 0; i < 20; i++) {
				data.add("message " + i);
			}
			adapter.notifyDataSetChanged();
			ToastUtils.show((Activity)getContext(), "刷新完成");
			frame.refreshComplete();
		}

		@Override
		public boolean checkCanDoRefresh(PtrFrameLayout frame, View content,
										 View header) {
			return PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
		}
	}

}
