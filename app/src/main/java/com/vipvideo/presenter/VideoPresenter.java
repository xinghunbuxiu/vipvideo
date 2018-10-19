package com.vipvideo.presenter;

import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.vipvideo.api.Api;
import com.vipvideo.api.ApiService;
import com.vipvideo.bean.AllVideoInfo;
import com.vipvideo.bean.AllVideoInfoBean;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.ui.fragment.VipFragment;
import com.vipvideo.ui.video.AllVideoActivity;

import java.util.Map;

import rx.functions.Func2;


public class VideoPresenter extends BasePresenter {
    ApiService apiService = Api.getDefault();

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    public void getAllMovieType() {
        VipFragment vipFragment = getFragment();
        rxHelper.createSubscriber(apiService.getAllMovieType(), new RxSubscriber<MovieTypeBean>(activity,false) {
            @Override
            protected void _onNext(MovieTypeBean bean) {
                vipFragment.setMovieTypeBean(bean);
            }
        });
    }

    public void getMovieByWhere(Map<String, String> map) {
        AllVideoActivity videoActivity = getActivity();
        rxHelper.createZipSubscriber(apiService.getAllMovieType(), apiService.getMovieByWhere(map), new Func2<MovieTypeBean, AllVideoInfo, AllVideoInfoBean>() {

            @Override
            public AllVideoInfoBean call(MovieTypeBean bean, AllVideoInfo allVideoInfo) {
                AllVideoInfoBean bean1 = new AllVideoInfoBean();
                bean1.setAllVideoInfo(allVideoInfo);
                bean1.setMovieTypeBean(bean);
                return bean1;
            }
        }, new RxSubscriber<AllVideoInfoBean>(activity, true) {
            @Override
            protected void _onNext(AllVideoInfoBean videoInfoBean) {
                videoActivity.setVideoInfo(videoInfoBean);
            }
        });
    }
}
