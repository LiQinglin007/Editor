package com.example.lql.editor.widget.viewpager;

/**
 * Created by dongJia on 2015/8/24
 * 项目名称：
 * 类描述：
 * 备注：
 */
public class Advert {
    public String url;
    public String id;

    public Advert(String url, String id) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}