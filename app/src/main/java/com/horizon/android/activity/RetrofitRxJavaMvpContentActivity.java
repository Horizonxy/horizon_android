package com.horizon.android.activity;

import com.horizon.android.R;
import com.horizon.android.component.ActivityComponent;
import com.horizon.android.component.DaggerActivityComponent;
import com.horizon.android.model.bean.ContentVo;
import com.horizon.android.module.ActivityModule;
import com.horizon.android.presenter.ContentListPresenter;
import com.horizon.android.util.log.LogUtils;
import com.horizon.android.view.ContentListView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;

public class RetrofitRxJavaMvpContentActivity extends BaseActivity implements ContentListView {

	@Bind(R.id.btn_get_info)
	Button btnGetInfo;
	@Bind(R.id.tv_result)
	TextView tvResult;
	@Inject
	ContentListPresenter mContentListPersenter;

	ActivityComponent component;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_user_info_mvp);
		setTitle("rxjava retrofit mvp");
		component = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
		component.inject(this);
	}

	@OnClick(R.id.btn_get_info)
	void getInfoClick(){
		mContentListPersenter.getContentList("5250300");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		component = null;
	}

	@Override
	public void onStartRx() {
		LogUtils.e("onStart onStart");
		btnGetInfo.setText("onStart");
	}

	@Override
	public void onNext(List<ContentVo> list) {
		LogUtils.e("onNext: " + list.size());
		btnGetInfo.setText(list.size() + "条数据");
	}

	@Override
	public void onError(Throwable e) {
		LogUtils.e("Throwable: " + e.getMessage());
		btnGetInfo.setText("失败： " + e.getMessage());
	}

	@Override
	public void onCompleted() {}

	@Override
	public void addSubscriberToComposite(Subscription subscription) {
		addSubscription(subscription);
	}
}
