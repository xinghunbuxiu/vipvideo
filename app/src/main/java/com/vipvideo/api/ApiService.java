package com.vipvideo.api;


import com.lixh.base.BaseResPose;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.bean.UserInfoBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
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

    @GET("/login/login/veifys.html")
    Observable<BaseResPose<UserInfoBean>> login(@Query("username") String username, @Query("passwd") String passwd, @Query("imei") String imei);

    @GET("/login/login/create.html")
    Observable<BaseResPose<String>> register(@Query("username") String username, @Query("passwd") String passwd);

    @GET("/login/login/repass.html")
    Observable<BaseResPose<String>> forgetPwd(@QueryMap Map<String, String> where);

    @GET("/sms/send?tpl_id=107511")
    Observable<String> sendMobileCode(@QueryMap Map<String, String> where);

    @GET("/login/login/yzcode.html")
    Observable<BaseResPose<String>> validateCode(@QueryMap Map<String, String> where);

}
