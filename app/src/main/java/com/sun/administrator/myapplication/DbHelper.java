package com.sun.administrator.myapplication;

import android.content.Context;

import com.lidroid.xutils.DbUtils;

/**
 * Created by Administrator on 2015/4/15.
 */
//数据库
public class DbHelper {

    private static DbUtils utils;
    public static void init(Context context){
        utils=DbUtils.create(context,"qiubai");
        //true时  打印SQ语句  便于调试
        utils.configDebug(true);
        //事物 1.打包 beginTransaction endTransaction  2.加速
        utils.configAllowTransaction(true);
    }

    public static DbUtils getUtils() {
        return utils;
    }
}
