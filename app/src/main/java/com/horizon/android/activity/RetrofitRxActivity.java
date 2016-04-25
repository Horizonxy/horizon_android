package com.horizon.android.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import com.horizon.android.Application;
import com.horizon.android.R;
import com.horizon.android.model.bean.ContentVo;
import com.horizon.android.util.Page;
import com.horizon.android.util.RestResult;
import com.horizon.android.util.SimpleSubscriber;
import com.horizon.android.util.log.LogUtils;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RetrofitRxActivity extends BaseActivity {

	@Bind(R.id.btn_content_req)
	Button btnReq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrofit_rx);
		setTitle("Retrofit RxJava");
	}

	@OnClick(R.id.btn_content_req)
	void reqClick(){
		doReq();
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			@SuppressWarnings("unchecked")
			RestResult<Page<ContentVo>> result = (RestResult<Page<ContentVo>>) msg.obj;
			if (result.isSucceed()) {
				btnReq.setText(result.getData().getResult().size() + "条数据");
			}
		};
	};
	
	private void doReq() {
		// 同步方法
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Response<RestResult<Page<ContentVo>>> result = Application.getInstance().apiService
//							.getContentByColumnIdSync(2, 100, "yooyo_weekend", "circle.content.list", "5250300")
//							.execute();
//					Message msg = new Message();
//					msg.obj = result.body();
//					mHandler.sendMessage(msg);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();

		//异步方法
		//被观察者
		Observable<List<ContentVo>> observable = Application.getInstance().apiService.getContentByColumnId(2, 100, "yooyo_weekend", "circle.content.list", "5250300")
				.flatMap(new Func1<RestResult<Page<ContentVo>>, Observable<List<ContentVo>>>() {
					@Override
					public Observable<List<ContentVo>> call(RestResult<Page<ContentVo>> result) {
						return Observable.just(result.getData().getResult());
					}
				})
				.subscribeOn(Schedulers.io()/*事件产生线程*/)
				.observeOn(AndroidSchedulers.mainThread()/*事件消费线程*/);
		//观察者
		SubscriberContentList subscriber = new SubscriberContentList();
		//订阅
		Subscription subscription = observable.subscribe(subscriber);
	}
	
	class SubscriberContentList extends SimpleSubscriber<List<ContentVo>> {

		@Override
		public void onStart() {
			super.onStart();
			LogUtils.e("onStart onStart");
			btnReq.setText("onStart");
		}

		@Override
		public void onError(Throwable e) {
			LogUtils.e("Throwable: " + e.getMessage());
			btnReq.setText("失败： " + e.getMessage());
		}

		@Override
		public void onNext(List<ContentVo> list) {
			LogUtils.e("onNext: " + list.size());
			btnReq.setText(list.size() + "条数据");
		}
	}

}
