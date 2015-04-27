package com.sun.administrator.myapplication;

import android.util.Log;

import com.lidroid.xutils.exception.DbException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/14.
 */
public class Parser {

    public static void getJsonList(String json){

        ArrayList<Bean> beans= new ArrayList<>();
        JSONObject object= null;
        try {
            object = new JSONObject(json);

            JSONArray items = object.getJSONArray("items");
            for(int i=0;i<items.length();i++){
                JSONObject jsonObject1 = items.getJSONObject(i);
                Bean bean = new Bean();
                bean.setContent(jsonObject1.getString("content"));
                if(!jsonObject1.isNull("image")){
                    bean.setImage(jsonObject1.getString("image"));
                }
                if(!jsonObject1.isNull("user")){
                    JSONObject user = jsonObject1.getJSONObject("user");
                    bean.setUserName(user.getString("login"));
                    bean.setUserId(user.getString("id"));
                    if(!user.isNull("icon")){
                        bean.setIcon(user.getString("icon"));
                    }
                }
                beans.add(bean);
            }
            try {
                //储存并更新，id没有重复则插入，有重复则更新
//                DbHelper.getUtils().saveOrUpdateAll();
                DbHelper.getUtils().saveAll(beans);
                Log.v("--DEBUG--","查看执行---------->>");
            } catch (DbException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
