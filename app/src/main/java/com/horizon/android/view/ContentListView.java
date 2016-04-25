package com.horizon.android.view;

import com.horizon.android.model.bean.ContentVo;
import java.util.List;

public interface ContentListView {

	public void onStartRx();
	
	public void onNext(List<ContentVo> list);
	
	public void onError(Throwable e);
	
	public void onCompleted();
}
