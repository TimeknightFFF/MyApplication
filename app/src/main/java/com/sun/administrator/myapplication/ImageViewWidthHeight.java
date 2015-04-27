package com.sun.administrator.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/4/15.
 */
public class ImageViewWidthHeight extends ImageView {

    public ImageViewWidthHeight(Context context) {
        super(context);
    }

    public ImageViewWidthHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();

        if (drawable == null) {
            //Mode模式
            int wMode = MeasureSpec.getMode(widthMeasureSpec);
            int wSize = MeasureSpec.getSize(widthMeasureSpec);

            int hMode = MeasureSpec.getMode(heightMeasureSpec);
            int hSize = MeasureSpec.getSize(heightMeasureSpec);

            if(wMode==MeasureSpec.EXACTLY&&hMode!=MeasureSpec.EXACTLY&&drawable.getIntrinsicWidth()!=0){
                hSize=drawable.getMinimumHeight()*wSize/drawable.getMinimumWidth();
                heightMeasureSpec=MeasureSpec.makeMeasureSpec(hSize,MeasureSpec.EXACTLY);
            }
//            switch (wMode) {
//                /*对应布局中的Wrap-Contained尽量大的展示**/
//                case MeasureSpec.AT_MOST:
//                    break;
//                /*Match-Parent指定大小**/
//                case MeasureSpec.EXACTLY:
//                    break;
//                /*未知空间**/
//                case MeasureSpec.UNSPECIFIED:
//                    break;
//            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
