package com.vipvideo.bean;

/**
 * Created by LIXH on 2018/10/19.
 * email lixhVip9@163.com
 * des
 */

public class AllVideoInfoBean {
    AllVideoInfo allVideoInfo;
    MovieTypeBean movieTypeBean;

    public AllVideoInfo getAllVideoInfo() {
        return this.allVideoInfo;
    }

    public void setAllVideoInfo(AllVideoInfo allVideoInfo) {
        this.allVideoInfo = allVideoInfo;
    }

    public MovieTypeBean getMovieTypeBean() {
        return this.movieTypeBean;
    }

    public void setMovieTypeBean(MovieTypeBean movieTypeBean) {
        this.movieTypeBean = movieTypeBean;
    }
}
