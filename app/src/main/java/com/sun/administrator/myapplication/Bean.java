package com.sun.administrator.myapplication;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NotNull;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Unique;

/**
 * Created by Administrator on 2015/4/13.
 */

//创建一个表，名字为Bean 加上name
@Table(name="Bean")
public class Bean {
    @Id(column = "id")
    //主键： 自增  唯一      取消自增的方法
    private int id;
    @Column(column = "icon")
    private String icon;
    @Column(column = "userName")
    private String userName;

    @Column(column = "content")
    //非空属性
    @NotNull
    private String content;

    @Column(column = "image")
    private String image;

    @Column(column = "userId")
    //唯一属性
    @Unique
    private String userId;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "icon='" + icon + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
