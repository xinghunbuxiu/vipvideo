package com.vipvideo.presenter;

import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.vipvideo.api.Api;
import com.vipvideo.api.ApiService;
import com.vipvideo.bean.AllVideoInfo;
import com.vipvideo.bean.AllVideoInfoBean;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.bean.VideoInfoBean;
import com.vipvideo.ui.fragment.VipFragment;
import com.vipvideo.ui.video.AllVideoActivity;
import com.vipvideo.ui.video.VideoPlayerActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;


public class VideoPresenter extends BasePresenter {
    ApiService apiService = Api.getDefault ( );

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    public void getAllMovieType() {
        VipFragment vipFragment = getFragment ( );
        rxHelper.createSubscriber (apiService.getAllMovieType ( ), new RxSubscriber<MovieTypeBean> (activity, false) {
            @Override
            protected void _onNext(MovieTypeBean bean) {
                vipFragment.setMovieTypeBean (bean);
            }
        });
    }

    public void getMovieByWhere(int page, Map<String, String> map) {
        AllVideoActivity videoActivity = getActivity ( );
        if (page == 0) {
            rxHelper.createZipSubscriber (apiService.getAllMovieType ( ), apiService.getMovieByWhere (map), (Func2<MovieTypeBean, AllVideoInfo, AllVideoInfoBean>) (bean, allVideoInfo) -> {
                AllVideoInfoBean bean1 = new AllVideoInfoBean ( );
                bean1.setAllVideoInfo (allVideoInfo);
                bean1.setMovieTypeBean (bean);
                return bean1;
            }, new RxSubscriber<AllVideoInfoBean> (activity, true) {
                @Override
                protected void _onNext(AllVideoInfoBean videoInfoBean) {
                    videoActivity.setVideoInfo (page, videoInfoBean);
                }
            });
        } else {
            rxHelper.createSubscriber (apiService.getMovieByWhere (map), new RxSubscriber<AllVideoInfo> (activity, false) {
                @Override
                protected void _onNext(AllVideoInfo allVideoInfo) {
                    AllVideoInfoBean bean1 = new AllVideoInfoBean ( );
                    bean1.setAllVideoInfo (allVideoInfo);
                    videoActivity.setVideoInfo (page, bean1);
                }
            });
        }

    }


    public void getMovieByWorkId(String workId) {
        Map<String, String> map = new LinkedHashMap<> ( );
        map.put ("worktype", "adnativemovie");
        map.put ("id", workId);
        VideoPlayerActivity activity = getActivity ( );
        rxHelper.createSubscriber (apiService.getMovieByWorkId (map), new RxSubscriber<VideoInfoBean> (activity, false) {
            @Override
            protected void _onNext(VideoInfoBean bean) {
                activity.VideoInfoBean (bean);
            }
        });

    }

    public void getRealPath(String site_url) {
        VideoPlayerActivity activity = getActivity ( );
        Observable.create ((Observable.OnSubscribe<Document>) subscriber -> {
            try {
                String realUrl = "https://jx.618g.com/?url=" + site_url;
                Connection conn = Jsoup.connect (realUrl);
                Document doc = conn.followRedirects (true).execute ( ).parse ( );
                subscriber.onNext (doc);
            } catch (IOException e) {
                e.printStackTrace ( );
                subscriber.onError (e);

            } finally {
                subscriber.onCompleted ( );
            }

        }).subscribeOn (Schedulers.newThread ( ))
                .observeOn (AndroidSchedulers.mainThread ( )).subscribe (new RxSubscriber<Document> (activity, true) {
            @Override
            protected void _onNext(Document s) {
                activity.setRealPath (s);
            }
        });

    }
}
