package com.horizon.android.model.interfaces;

import com.horizon.android.model.bean.ContentVo;
import java.util.List;
import rx.Subscriber;

public interface ContentListInterface {

	public void getContentList(String columnId, Subscriber<List<ContentVo>> subscriber);
	
}
