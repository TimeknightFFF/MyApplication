package com.sun.administrator.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/4/13.
 */
public class BeanAdapter extends BaseAdapter  {

    private Context context;
    private List<Bean> list;
    private LruCache<String, Bitmap> cache;

    public BeanAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;

        cache=new LruCache<String, Bitmap>(20);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = ((ViewHolder) convertView.getTag());
        Bean bean = list.get(position);
        holder.content.setText(bean.getContent());
        //操作之前进行是否为空判断
        if (bean.getImage() == null) {
            holder.imageView.setVisibility(View.GONE);
        } else {
            holder.imageView.setVisibility(View.VISIBLE);
            HttpXutils.getBitmap(getImageUrl(bean.getImage()),holder.imageView,context);
        }
        if (bean.getUserName() == null) {
            holder.userName.setVisibility(View.GONE);
        }else {
            holder.userName.setVisibility(View.VISIBLE);
            holder.userName.setText(bean.getUserName());
        }
        if (bean.getIcon() == null) {
            holder.icon.setVisibility(View.GONE);
        }else {
            holder.icon.setVisibility(View.VISIBLE);
            HttpXutils.getBitmap(getIconUrl(bean.getUserId(),bean.getIcon()),holder.icon,context);
        }


        return convertView;
    }

    /**
     * 获取icon的网络下载地址
     *
     * @param id
     * @param icon
     */
    public String getIconUrl(String id, String icon) {
        Pattern compile = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = compile.matcher(id);
        matcher.find();
        return String.format(UrlUtil.URL_USER_ICON, matcher.group(1), matcher.group(), icon);
    }


    /**
     * 获取可用的Image的URL
     */
    private String getImageUrl(String image) {
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        return String.format(UrlUtil.URL_IMAGE, matcher.group(1), matcher.group(0), "small", image);
    }



    public static class ViewHolder {
        private ImageView imageView;
        private ImageView icon;
        private TextView userName;
        private TextView content;

        public ViewHolder(View view) {
            imageView = ((ImageView) view.findViewById(R.id.image));
            icon = ((ImageView) view.findViewById(R.id.icon));

            userName = ((TextView) view.findViewById(R.id.userName));
            content = ((TextView) view.findViewById(R.id.content));
        }
    }
}
