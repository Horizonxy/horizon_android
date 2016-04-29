package com.horizon.android.presenter;

import com.horizon.android.model.interfaces.ContentListInterface;
import com.horizon.android.model.bean.ContentVo;
import com.horizon.android.util.SimpleSubscriber;
import com.horizon.android.view.ContentListView;
import java.util.List;

import rx.Subscription;

public class ContentListPresenter {

	private ContentListView vContentList;
	private ContentListInterface mContentList;
	
	public ContentListPresenter(ContentListView vContentList, ContentListInterface mContentList) {
		this.vContentList = vContentList;
		this.mContentList = mContentList;
	}
	
	public void getContentList(String columnId) {
		Subscription subscription = mContentList.getContentList(columnId, new SimpleSubscriber<List<ContentVo>>(){
			@Override
			public void onStart() {
				super.onStart();
				vContentList.onStartRx();
			}

			@Override
			public void onNext(List<ContentVo> list) {
				vContentList.onNext(list);
			}

			@Override
			public void onError(Throwable e) {
				vContentList.onError(e);
			}

			@Override
			public void onCompleted() {
				vContentList.onCompleted();
			}
		});
		vContentList.addSubscriberToComposite(subscription);
	}
}
