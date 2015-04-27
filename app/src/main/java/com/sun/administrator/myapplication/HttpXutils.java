package com.sun.administrator.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

/**
 * Created by Administrator on 2015/4/14.
 */
public class HttpXutils {

    /**
     * 创建下载图片的方法，并用接口回调给调用者传值
     * @param url
     */
    public static void getBitmap(String url,ImageView imageView,Context context){

        BitmapUtils bitmapUtils=new BitmapUtils(context,Environment.getExternalStorageDirectory().getAbsolutePath(),1/8.0f,1024*1024);

        bitmapUtils.display(imageView,url,new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                   imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
    }
}
