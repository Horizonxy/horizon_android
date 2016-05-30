package com.horizon.android.activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.horizon.android.R;
import com.horizon.android.adapter.recyclerview.BaseAdapterHelper;
import com.horizon.android.adapter.recyclerview.DividerGridItemDecoration;
import com.horizon.android.adapter.recyclerview.QuickAdapter;
import com.horizon.android.util.NativeImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class NativeImageActivity extends BaseActivity {

    @Bind(R.id.rv_native_image)
    RecyclerView rvNativeImage;

    private ProgressDialog mProgressDialog;
    private List<String> imagePath;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mProgressDialog.dismiss();
            rvNativeImage.getAdapter().notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_image);
        setTitle("本地图片");

//        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//            ToastUtils.show(this, "外部存储不可用");
//        }

        imagePath = new ArrayList<String>();

        rvNativeImage.setLayoutManager(new GridLayoutManager(this, 2));
        rvNativeImage.addItemDecoration(new DividerGridItemDecoration(this));
        rvNativeImage.setAdapter(new QuickAdapter<String>(this, R.layout.item_native_image, imagePath) {
            @Override
            public void onBindData(BaseAdapterHelper holder, int position) {
                final ImageView imageView = holder.getView(R.id.iv_item_native_image);
                Bitmap bitmap = NativeImageLoader.getInstance().loadNativeImage(imagePath.get(position), imageView.getWidth(), imageView.getHeight(), new NativeImageLoader.NativeImageLoadListener() {
                    @Override
                    public void onImageLoad(String path, Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
                imageView.setImageBitmap(bitmap);
            }
        });

        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Cursor cursor = getContentResolver().query(imageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);
                while (cursor.moveToNext()){
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                    if(!imagePath.contains(path)){
                        imagePath.add(path);
                    }
                }
                cursor.close();

                handler.sendEmptyMessage(0);
            }
        }).start();

    }
}
