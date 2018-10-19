package com.vipvideo.api;


import com.lixh.base.BaseResPose;
import com.vipvideo.bean.MovieTypeBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by helin on 2016/10/9 17:09.
 */

public interface ApiService {

    @GET("/conds/?worktype=adnativemovie")
    Observable<BaseResPose<MovieTypeBean>> getAllMovieType();

    @GET("/adnativemovie/")
    Observable<BaseResPose<MovieTypeBean>> getMovieByWhere(@QueryMap Map<String, String> where);

}
