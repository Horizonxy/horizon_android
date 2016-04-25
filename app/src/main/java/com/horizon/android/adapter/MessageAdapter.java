package com.horizon.android.adapter;

import java.util.List;
import android.content.Context;
import com.horizon.android.R;
import com.horizon.android.quickadapter.BaseAdapterHelper;
import com.horizon.android.quickadapter.QuickAdapter;

public class MessageAdapter extends QuickAdapter<String> {

		public MessageAdapter(Context context, int layoutResId,
				List<String> data) {
			super(context, layoutResId, data);
		}

		@Override
		protected void convert(BaseAdapterHelper helper, String item) {
			helper.setText(R.id.tv_message_text, item);
		}
	}