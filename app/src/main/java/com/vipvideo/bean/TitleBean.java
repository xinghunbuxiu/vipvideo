package com.vipvideo.bean;

import com.lixh.bean.BaseModel;

public class TitleBean extends BaseModel {

    String type;

    String title;

    public TitleBean(String title) {
        this.title = title;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

