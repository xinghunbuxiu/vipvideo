package com.vipvideo.bean;

import com.lixh.bean.BaseModel;

/**
 * author: rsw
 * created on: 2018/10/21 下午5:50
 * description:
 */

public class UserInfoBean extends BaseModel {


    /**
     * time : 1547183884
     * id : 158
     * power : 2
     * share : 5
     * url : 点击充值后请等待自动跳转获取卡密（非本APP内可退出）
     * url1 : http://www.wdfgm.com/pay/?name=一个月&fee=6
     * url2 : http://www.wdfgm.com/pay/?name=三个月&fee=16
     * url3 : http://www.wdfgm.com/pay/?name=一年&fee=50
     * url4 : http://www.wdfgm.com/pay/?name=永久&fee=199
     * url5 : http://www.wdfgm.com/pay/?name=七天&fee=2
     * url6 : http://www.wdfgm.com/pay/?name=六个月&fee=28
     * advert : 新注册会员赠送15天使用时间哦！
     * code : MTUzOTkzNjI1OQ==
     * weichat : 不等待自动跳转未获得卡密不负责
     */

    private int time;
    private int id;
    private String power;
    private int share;
    private String url;
    private String url1;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    private String url6;
    private String advert;
    private String code;
    private String weichat;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url5) {
        this.url5 = url5;
    }

    public String getUrl6() {
        return url6;
    }

    public void setUrl6(String url6) {
        this.url6 = url6;
    }

    public String getAdvert() {
        return advert;
    }

    public void setAdvert(String advert) {
        this.advert = advert;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWeichat() {
        return weichat;
    }

    public void setWeichat(String weichat) {
        this.weichat = weichat;
    }
}
