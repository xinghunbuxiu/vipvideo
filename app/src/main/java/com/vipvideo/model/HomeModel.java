package com.vipvideo.model;

import com.alibaba.fastjson.JSON;
import com.lixh.bean.BaseModel;
import com.vipvideo.bean.BannerBean;

import java.util.List;

/**
 * Created by LIXH on 2018/10/19.
 * email lixhVip9@163.com
 * des
 */

public class HomeModel extends BaseModel {
    List<BannerBean> bannerBeans;
    List<HomeModel.HotBean> hotBeans;//话题

    public List<BannerBean> getBannerBeans() {
        return this.bannerBeans;
    }

    public void setBannerBeans(List<BannerBean> bannerBeans) {
        this.bannerBeans = bannerBeans;
    }

    public List<HomeModel.HotBean> getHotBeans() {
        return hotBeans;
    }

    public void setHotBeans(List<HomeModel.HotBean> hotBeans) {
        this.hotBeans = hotBeans;
    }

    public static class HotBean extends BaseModel {
        String typeName;
        int type;
        List<HomeModel.HotTvBean> beans;

        public HotBean(String typeName,int type, List<HotTvBean> hotTipBeans) {
            this.typeName = typeName;
            this.beans = hotTipBeans;
            this.type=type;
        }

        public String getTypeName() {
            return this.typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public List<HomeModel.HotTvBean> getBeans() {
            return beans;
        }

        public void setBeans(List<HomeModel.HotTvBean> beans) {
            this.beans = beans;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class HotTvBean extends BaseModel {
        String hotTv_img;
        String hotTv_name;
        String hotTv_star;
        String hotTv_label;
        private String link;

        public String getHotTv_img() {
            return this.hotTv_img;
        }

        public void setHotTv_img(String hotTv_img) {
            this.hotTv_img = hotTv_img;
        }

        public String getHotTv_name() {
            return this.hotTv_name;
        }

        public void setHotTv_name(String hotTv_name) {
            this.hotTv_name = hotTv_name;
        }

        public String getHotTv_star() {
            return this.hotTv_star;
        }

        public void setHotTv_star(String hotTv_star) {
            this.hotTv_star = hotTv_star;
        }

        public String getHotTv_label() {
            return this.hotTv_label;
        }

        public void setHotTv_label(String hotTv_label) {
            this.hotTv_label = hotTv_label;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
