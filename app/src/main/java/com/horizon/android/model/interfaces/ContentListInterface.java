package com.horizon.android.model.interfaces;

import com.horizon.android.model.bean.ContentVo;
import java.util.List;
import rx.Subscriber;
import rx.Subscription;

public interface ContentListInterface {

	public Subscription getContentList(String columnId, Subscriber<List<ContentVo>> subscriber);
	
}
