package com.vipvideo.bean;

import com.lixh.bean.BaseModel;

import java.util.ArrayList;

public class BannerBean extends BaseModel {

    private ArrayList<String> pic_url;

    public BannerBean(ArrayList<String> pic_url) {
        this.pic_url = pic_url;
    }

    public ArrayList<String> getPic_url() {
        return pic_url;
    }

    public void setPic_url(ArrayList<String> pic_url) {
        this.pic_url = pic_url;
    }
}

