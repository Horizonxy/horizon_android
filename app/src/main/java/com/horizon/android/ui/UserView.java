package com.horizon.android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.activity.PicturesDetailActivity;
import com.horizon.android.adapter.recyclerview.BaseAdapterHelper;
import com.horizon.android.adapter.recyclerview.QuickAdapter;
import com.horizon.android.util.SmallPicInfo;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserView extends AutoLinearLayout /*implements View.OnClickListener*/ {

	@Bind(R.id.pic_list)
	RecyclerView  picList;

	private List<String> data;
	private static final String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
			"http://img2.3lian.com/2014/f2/37/d/40.jpg",
			"http://d.3987.com/sqmy_131219/001.jpg",
			"http://img2.3lian.com/2014/f2/37/d/39.jpg",
			"http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
			"http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
			"http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
	};

	private ColorMatrixColorFilter colorFilter;

	private List<SmallPicInfo> picInfos;

	public UserView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	private void init() {
		picInfos = new ArrayList<SmallPicInfo>();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.bind(this);

		final ColorMatrix colorMatrix = new ColorMatrix();
		colorMatrix.setSaturation(0);
		colorFilter = new ColorMatrixColorFilter(colorMatrix);

		picList.setLayoutManager(new GridLayoutManager(getContext(), 2));
//		picList.addItemDecoration(new DividerGridItemDecoration(getContext()));
		picList.setAdapter(new QuickAdapter<String>(getContext(), R.layout.item_iamge, data = Arrays.asList(images)) {
			@Override
			public void onBindData(BaseAdapterHelper holder, final int position) {
				final ImageView image = holder.getView(R.id.item_image);
				image.setColorFilter(colorFilter);
				Application.getInstance().getImageLoader().displayImage(data.get(position), image, Application.getInstance().getDefaultOptions());

				holder.setImageBuilder(R.id.item_image, data.get(position), Application.getInstance().getDefaultOptions());
//				holder.setOnClickListener(R.id.item_image, UserView.this);

				holder.setOnClickListener(R.id.item_image, new OnItemClickListener(position));
				image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						image.getViewTreeObserver().removeGlobalOnLayoutListener(this);

						int[] screenLocation = new int[2];
						image.getLocationOnScreen(screenLocation);
						SmallPicInfo info = new SmallPicInfo(data.get(position), screenLocation[0], screenLocation[1], image.getWidth(), image.getHeight(), position, null);
						picInfos.add(info);
					}
				});

				holder.setTag(R.id.item_image, data.get(position));
			}
		});
	}

	@OnClick(R.id.btn_right1)
	void right1Click(){

	}

	@OnClick(R.id.btn_left)
	void leftClick(){

	}

//	@Override
//	public void onClick(View v) {
//		int[] screenLocation = new int[2];
//		v.getLocationOnScreen(screenLocation);
//
//		String url = (String) v.getTag();
//
//		Intent intent = new Intent(getContext(), PictureDetailActivity.class);
//		intent.putExtra(Constants.BUNDLE_PIC_URL, url);
//		intent.putExtra(Constants.BUNDLE_PIC_LEFT, screenLocation[0]);
//		intent.putExtra(Constants.BUNDLE_PIC_TOP, screenLocation[1]);
//		intent.putExtra(Constants.BUNDLE_PIC_WIDTH, v.getWidth());
//		intent.putExtra(Constants.BUNDLE_PIC_HEIGHT, v.getHeight());
//		getContext().startActivity(intent);
//		((Activity)getContext()).overridePendingTransition(0, 0);
//	}

	class OnItemClickListener implements View.OnClickListener {

		private int pos;

		public OnItemClickListener(int pos){
			this.pos = pos;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getContext(), PicturesDetailActivity.class);
			intent.putExtra(Constants.BUNDLE_PIC_INFOS, (Serializable) picInfos);
			intent.putExtra(Constants.BUNDLE_PIC_POS, pos);
			getContext().startActivity(intent);
			((Activity)getContext()).overridePendingTransition(0, 0);
		}
	}
}
