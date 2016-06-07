package com.horizon.android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.adapter.recyclerview.BaseAdapterHelper;
import com.horizon.android.adapter.recyclerview.QuickAdapter;
import com.horizon.android.util.SmallPicInfo;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class RecycleViewImageForDetailActivity extends BaseActivity {

    @Bind(R.id.pic_list)
    RecyclerView picList;

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

    private Map<Integer, SmallPicInfo> picInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_image_for_detail);
        setTitle("Recycler Image");

        picInfos = new HashMap<Integer, SmallPicInfo>();

        final ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        colorFilter = new ColorMatrixColorFilter(colorMatrix);

        picList.setLayoutManager(new GridLayoutManager(this, 2));
//		picList.addItemDecoration(new DividerGridItemDecoration(getContext()));
        picList.setAdapter(new QuickAdapter<String>(this, R.layout.item_iamge, data = Arrays.asList(images)) {
            @Override
            public void onBindData(BaseAdapterHelper holder, int position) {
                ImageView image = holder.getView(R.id.item_image);
                image.setColorFilter(colorFilter);
                Application.getInstance().getImageLoader().displayImage(data.get(position), image, Application.getInstance().getDefaultOptions(), new ImageLoadListener(image, position));
                holder.setOnClickListener(R.id.item_image, new OnItemClickListener(position));
                holder.setTag(R.id.item_image, data.get(position));
            }
        });
    }

    class ImageLoadListener extends SimpleImageLoadingListener {

        public ImageLoadListener(ImageView image, int position) {
            this.image = image;
            this.position = position;
        }

        private ImageView image;
        private int position;

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    image.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                    int[] screenLocation = new int[2];
                    image.getLocationOnScreen(screenLocation);

                    image.setDrawingCacheEnabled(true);
                    Bitmap bmp = image.getDrawingCache();
                    SmallPicInfo info = new SmallPicInfo(data.get(position), screenLocation[0], screenLocation[1], image.getWidth(), image.getHeight(), position, Bitmap.createBitmap(bmp));
                    picInfos.put(position, info);
                    image.setDrawingCacheEnabled(false);
                }
            });
        }

    }

    class OnItemClickListener implements View.OnClickListener {

        private int pos;

        public OnItemClickListener(int pos){
            this.pos = pos;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RecycleViewImageForDetailActivity.this, PicturesDetailActivity.class);
            intent.putExtra(Constants.BUNDLE_PIC_INFOS, (Serializable) picInfos);
            intent.putExtra(Constants.BUNDLE_PIC_POS, pos);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }
}
