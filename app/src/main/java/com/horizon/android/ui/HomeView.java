package com.horizon.android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.activity.AnimationFrameActivity;
import com.horizon.android.activity.AutoLoadActivity;
import com.horizon.android.activity.CaptureActivity;
import com.horizon.android.activity.ClipChildActivity;
import com.horizon.android.activity.ConvenientBannerActivity;
import com.horizon.android.activity.EditViewActivity;
import com.horizon.android.activity.HistoryTodayActivity;
import com.horizon.android.activity.ListViewActivity;
import com.horizon.android.activity.MVPLoginActivity;
import com.horizon.android.activity.MVPUserInfoActivity;
import com.horizon.android.activity.MovieListActivity;
import com.horizon.android.activity.PullToZoomListActivity;
import com.horizon.android.activity.PullToZoomScrollActivity;
import com.horizon.android.activity.RetrofitRxActivity;
import com.horizon.android.activity.RetrofitRxJavaMvpContentActivity;
import com.horizon.android.activity.ScrollViewActivity;
import com.horizon.android.activity.StatusLayoutActivity;
import com.horizon.android.activity.WeatherActivity;
import com.zhy.autolayout.AutoLinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

public class HomeView extends AutoLinearLayout {

	private Context mCxt;
	@Bind(R.id.ptr_frame)
	PtrFrameLayout ptrFrame;

	public HomeView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);
	}

	private void init(Context context) {
		mCxt = context;
	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		ButterKnife.bind(this);

		ptrFrame.setPinContent(true);
		final MaterialHeader header = new MaterialHeader(getContext());
		header.setLayoutParams(new PtrFrameLayout.LayoutParams(PtrFrameLayout.LayoutParams.MATCH_PARENT, PtrFrameLayout.LayoutParams.WRAP_CONTENT));
		header.setPadding(0, 10, 0, 10);
		header.setPtrFrameLayout(ptrFrame);
		ptrFrame.setHeaderView(header);
		ptrFrame.addPtrUIHandler(header);
		ptrFrame.setPtrHandler(new HomePtrHandler());
	}

	class HomePtrHandler implements PtrHandler {

		@Override
		public boolean checkCanDoRefresh(PtrFrameLayout frame, View content,
				View header) {
			return PtrDefaultHandler.checkContentCanBePulledDown(frame,
					content, header);
		}

		@Override
		public void onRefreshBegin(final PtrFrameLayout frame) {
			frame.postDelayed(new Runnable() {
				@Override
				public void run() {
					frame.refreshComplete();
				}
			}, 5000);
		}
	}

	@OnClick(R.id.btn_convenient)
	void convenientClick(){
		mCxt.startActivity(new Intent(mCxt, ConvenientBannerActivity.class));
	}

	@OnClick(R.id.btn_history_today)
	void historyTodayClick(){
		mCxt.startActivity(new Intent(mCxt, HistoryTodayActivity.class));
	}

	@OnClick(R.id.btn_editview)
	void editClick(){
		mCxt.startActivity(new Intent(mCxt, EditViewActivity.class));
	}

	@OnClick(R.id.btn_collection_lise)
	void clipClick(){
		mCxt.startActivity(new Intent(mCxt, ClipChildActivity.class));
	}

	@OnClick(R.id.btn_scroll_view)
	void scrollviewClick(){
		mCxt.startActivity(new Intent(mCxt, ScrollViewActivity.class));
	}

	@OnClick(R.id.btn_layout_status)
	void layoutStatusClick(){
		mCxt.startActivity(new Intent(mCxt, StatusLayoutActivity.class));
	}

	@OnClick(R.id.btn_animation_frame)
	void frameClick(){
		mCxt.startActivity(new Intent(mCxt, AnimationFrameActivity.class));
	}

	@OnClick(R.id.btn_listview)
	void listviewClick(){
		mCxt.startActivity(new Intent(mCxt, ListViewActivity.class));
	}

	@OnClick(R.id.btn_weather)
	void weatherClick(){
		mCxt.startActivity(new Intent(mCxt, WeatherActivity.class));
	}

	@OnClick(R.id.btn_movie_list)
	void movieListClick(){
		mCxt.startActivity(new Intent(mCxt, MovieListActivity.class));
	}

	@OnClick(R.id.btn_mvp_retrofit_rxjava)
	void retrofitRxjavaMvpClick(){
		mCxt.startActivity(new Intent(mCxt, RetrofitRxJavaMvpContentActivity.class));
	}

	@OnClick(R.id.btn_mvp_get_info)
	void mvpUserInfoClick(){
		mCxt.startActivity(new Intent(mCxt, MVPUserInfoActivity.class));
	}

	@OnClick(R.id.btn_mvp_login)
	void mvpLoginnClick(){
		mCxt.startActivity(new Intent(mCxt, MVPLoginActivity.class));
	}

	@OnClick(R.id.btn_retrofit_rx)
	void retrofitRxjavaClick(){
		mCxt.startActivity(new Intent(mCxt, RetrofitRxActivity.class));
	}

	@OnClick(R.id.btn_autoload_list)
	void autoLoadClick(){
		mCxt.startActivity(new Intent(mCxt, AutoLoadActivity.class));
	}

	@OnClick(R.id.btn_zoom_list)
	void zoomListClick(){
		mCxt.startActivity(new Intent(mCxt, PullToZoomListActivity.class));
	}

	@OnClick(R.id.btn_zoom_scroll)
	void zoomScrollClick(){
		mCxt.startActivity(new Intent(mCxt, PullToZoomScrollActivity.class));
	}

	@OnClick(R.id.btn_left)
	void leftClick(){
		//Application.getInstance().exit();
	}


	@OnClick(R.id.btn_right1)
	void right1Click(){

	}

	@OnClick(R.id.btn_right2)
	void right2Click(){

	}

	@OnClick(R.id.btn_caputer)
	void caputerClick(){
		((Activity) mCxt).startActivityForResult(new Intent(mCxt, CaptureActivity.class), Constants.REQ_SCAN);
	}

}
