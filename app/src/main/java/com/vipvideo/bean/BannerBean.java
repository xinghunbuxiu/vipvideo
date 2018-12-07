package com.vipvideo.bean;

import com.lixh.bean.BaseModel;

public class BannerBean extends BaseModel {

    private String pic_url;
    private String tvLabel;
    private String tvDes;
    private String link;

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPic_url() {
        return this.pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getTvLabel() {
        return this.tvLabel;
    }

    public void setTvLabel(String tvLabel) {
        this.tvLabel = tvLabel;
    }

    public String getTvDes() {
        return this.tvDes;
    }

    public void setTvDes(String tvDes) {
        this.tvDes = tvDes;
    }
}

