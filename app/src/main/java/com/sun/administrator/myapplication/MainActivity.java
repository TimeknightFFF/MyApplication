package com.sun.administrator.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private ListView listView;
    private List<Bean> beans;
    private BeanAdapter adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                adapter=new BeanAdapter(MainActivity.this,beans);
                listView.setAdapter(adapter);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper.init(this);
        listView= ((ListView) findViewById(R.id.listView));
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.CONNECT.GET,UrlUtil.URL_IMAGE_IMAGES,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json=responseInfo.result;
                 Parser.getJsonList(json);
                try {
//                    beans.clear();
                    beans=DbHelper.getUtils().findAll(Bean.class);
                    Log.d("--DEBUG--","beans数据----->"+beans);
                    if(beans==null){
                        beans=new ArrayList<Bean>();
                    }
                    if(beans.size()>0){
                        handler.sendEmptyMessage(1);
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        try {
            WhereBuilder builder1=WhereBuilder.b("userName","like","%的%");
            Bean bean=new Bean();
            bean.setUserName("FIGHT F F");
            DbHelper.getUtils().update(bean,builder1,"userName");

            Selector selector=Selector.from(Bean.class);
            selector.where("userName","=",null);
//            DbModel dbModel= DbHelper.getUtils().findDbModelFirst();
            beans=DbHelper.getUtils().findAll(Bean.class);

        } catch (DbException e) {
            e.printStackTrace();
        }
        Log.d("--DEBUG--","beans数据----->"+beans);
        if(beans==null){
            beans=new ArrayList<Bean>();
        }
        if(beans.size()>0){
            handler.sendEmptyMessage(1);
        }

    }

    @Override
    protected void onStop() {
        try {
            DbHelper.getUtils().deleteAll(Bean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        super.onStop();
    }
}
