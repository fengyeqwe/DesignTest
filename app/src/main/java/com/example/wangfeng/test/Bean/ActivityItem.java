package com.example.wangfeng.test.Bean;

/**
 * Created by fengye on 2017/8/8.
 * email 1040441325@qq.com
 *  主页面列表的单项
 */

public class ActivityItem {
    private String title;
    private Class url;

    public ActivityItem(String title, Class url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getUrl() {
        return url;
    }

    public void setUrl(Class url) {
        this.url = url;
    }
}
