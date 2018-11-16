package com.vipvideo.api;


import com.lixh.base.BaseResPose;
import com.vipvideo.bean.AllVideoInfo;
import com.vipvideo.bean.GroupVideoInfo;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.bean.VideoInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by helin on 2016/10/9 17:09.
 */

public interface ApiService {

    @GET("/conds/?worktype=adnativemovie")
    Observable<BaseResPose<MovieTypeBean>> getAllMovieType();

    @GET("/adnativemovie/")
    Observable<BaseResPose<AllVideoInfo>> getMovieByWhere(@QueryMap Map<String, String> where);

    @GET("/wiseselected/?worktype=adnativemovie")
    Observable<BaseResPose<GroupVideoInfo>> getMovieGroup(@Query("version") String version);

    @GET("/xqinfo/")
    Observable<BaseResPose<VideoInfoBean>> getMovieByWorkId(@QueryMap Map<String, String> where);

}
