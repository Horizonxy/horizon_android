package com.horizon.android.model;

import com.horizon.android.Application;
import com.horizon.android.model.bean.ContentVo;
import com.horizon.android.model.interfaces.ContentListInterface;
import com.horizon.android.util.Page;
import com.horizon.android.util.RestResult;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ContentListImpl implements ContentListInterface {

	@Override
	public void getContentList(String columnId, Subscriber<List<ContentVo>> subscriber) {
		Application.getInstance().apiService.getContentByColumnId(2, 100, "yooyo_weekend", "circle.content.list",
				columnId)
		.flatMap(new Func1<RestResult<Page<ContentVo>>, Observable<List<ContentVo>>>() {
			@Override
			public Observable<List<ContentVo>> call(RestResult<Page<ContentVo>> result) {
				return Observable.just(result.getData().getResult());
			}
		})
		.subscribeOn(Schedulers.io()/*事件产生线程*/)
		.observeOn(AndroidSchedulers.mainThread()/*事件消费线程*/)
		.subscribe(subscriber);
	}

}
