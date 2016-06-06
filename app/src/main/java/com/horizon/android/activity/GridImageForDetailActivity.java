package com.horizon.android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.horizon.android.Application;
import com.horizon.android.Constants;
import com.horizon.android.R;
import com.horizon.android.quickadapter.BaseAdapterHelper;
import com.horizon.android.quickadapter.QuickAdapter;
import com.horizon.android.util.SmallPicInfo;

import java.util.Arrays;

import butterknife.Bind;

public class GridImageForDetailActivity extends BaseActivity {

    @Bind(R.id.gv_images)
    GridView gvImages;

    private static final String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_image_for_detail);
        setTitle("Grid Image");

        gvImages.setAdapter(new QuickAdapter<String>(this, R.layout.item_grid_iamge_for_detail, Arrays.asList(images)) {

            @Override
            protected void convert(BaseAdapterHelper helper, final String item) {
                helper.setImageBuilder(R.id.item_image, item, Application.getInstance().getDefaultOptions());
                helper.setOnClickListener(R.id.item_image, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView imageView = (ImageView) v;
                        imageView.setDrawingCacheEnabled(true);
                        Bitmap bitmap = imageView.getDrawingCache();

                        int[] screenLocation = new int[2];
		                v.getLocationOnScreen(screenLocation);

                        SmallPicInfo info = new SmallPicInfo(item, screenLocation[0], screenLocation[1], v.getWidth(), v.getHeight(), 0, Bitmap.createBitmap(bitmap));

                        Intent intent = new Intent(GridImageForDetailActivity.this, PictureDetail2Activity.class);
                        intent.putExtra(Constants.BUNDLE_PIC_INFOS, info);
                        GridImageForDetailActivity.this.startActivity(intent);
                        overridePendingTransition(0, 0);

                        imageView.setDrawingCacheEnabled(false);
                    }
                });
            }
        });

    }
}
