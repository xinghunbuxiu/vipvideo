package com.vipvideo.presenter;

import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.vipvideo.api.Api;
import com.vipvideo.api.ApiService;
import com.vipvideo.api.HostType;
import com.vipvideo.bean.AllVideoInfo;
import com.vipvideo.bean.AllVideoInfoBean;
import com.vipvideo.bean.GroupVideoInfo;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.bean.VideoInfoBean;
import com.vipvideo.ui.fragment.VipFragment;
import com.vipvideo.ui.video.AllVideoActivity;
import com.vipvideo.ui.video.VideoPlayerActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.functions.BiFunction;


/**
 * <option value="http://jqaaa.com/jx.php?url=">线路一</option>
 * <option value="http://api.662820.com/xnflv/index.php?url=">线路二</option>
 * <option value="http://api.xfsub.com/index.php?url=">线路三</option>
 * <option value="http://jiexi.92fz.cn/player/vip.php?url=">线路四</option>
 * <option value="http://api.nepian.com/ckparse/?url=">线路五</option>
 * <option value="http://aikan-tv.com/?url=">线路六</option>
 * <option value="http://j.zz22x.com/jx/?url=">线路七</option>
 * <option value="http://www.efunfilm.com/yunparse/index.php?url=">线路八</option>
 * <option value="https://api.flvsp.com/?url=">线路九</option>
 * <option value="http://api.xfsub.com/index.php?url=">线路十</option>
 * <option value="http://api.47ks.com/webcloud/?v=">线路十</option>
 */
public class VideoPresenter extends BasePresenter {
    ApiService apiService = Api.getDefault ( );
    ApiService apiService = Api.getDefault(HostType.BAIDU_URL);


    Map<String, String> noUser = new HashMap<String, String> ( ) {
        {
            //时光
            put ("mtime.com", "http://api.mtime.com/trailer/getvideo.aspx?vid=");
            //百事通
            put ("bestv.com.cn", "http://api.mtime.com/trailer/getvideo.aspx?vid=");
        }
    };

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

    public void getBannerData() {
        VipFragment vipFragment = getFragment ( );
//        rxHelper.createSubscriber (apiService.getAllMovieType ( ), new RxSubscriber<MovieTypeBean> (activity, false) {
//            @Override
//            protected void _onNext(MovieTypeBean bean) {
//                vipFragment.setMovieTypeBean (bean);
//            }
//        });
    }

    public void getMovieByWhere(int page, Map<String, String> map) {
        AllVideoActivity videoActivity = getActivity ( );
        if (page == 0) {
            rxHelper.createZipSubscriber (apiService.getAllMovieType ( ), apiService.getMovieByWhere (map), (BiFunction<MovieTypeBean, AllVideoInfo, AllVideoInfoBean>) (bean, allVideoInfo) -> {
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
        map.put ("version", "8.6.5");
        VideoPlayerActivity activity = getActivity ( );
        rxHelper.createSubscriber (apiService.getMovieByWorkId (map), new RxSubscriber<VideoInfoBean> (activity, false) {
            @Override
            protected void _onNext(VideoInfoBean bean) {
                activity.VideoInfoBean (bean);
            }
        });

    }

    public void getRealPath(String site_domain, String site_url) {
        VideoPlayerActivity activity = getActivity ( );

    }

    /**
     * @param //site_domain
     * @param finalSite_url
     * @return
     * @throws IOException
     */
    public String getLine1(String finalSite_url) throws IOException {
        String url = "https://www.jqaaa.com/jx.php?url=";
        String realUrl = url + finalSite_url;
        Connection conn = Jsoup.connect (realUrl).timeout (5000);
        Document doc = conn.get ( );
        Element player = doc.getElementById ("myVideo");
        String src = player.attr ("src");
        if (finalSite_url.indexOf ("fun.tv") > 0) {
            url = "https://api.flvsp.com/?url=";
        } else if (finalSite_url.indexOf ("wasu.cn") > 0) {
            url = "//5.5252e.com/jx/b.php?url=http://v.qq.com/x/cover/vfx3eugf3h4jiqg.html";
        } else if (finalSite_url.indexOf ("/share/") > 0) {
            url = "http://v.qq.com/x/cover/vfx3eugf3h4jiqg.html";
        } else if (finalSite_url.indexOf (".m3u8") > 0) {
            url = "/m3u8.php?url=http://v.qq.com/x/cover/vfx3eugf3h4jiqg.html";
        } else if (finalSite_url.indexOf (".mp4") > 0) {
            url = "/mp4.php?url=http://v.qq.com/x/cover/vfx3eugf3h4jiqg.html";
        } else if (finalSite_url.indexOf ("duowan.com") > 0) {
            url = "//5.5252e.com/jx/b.php?url=";
        } else if (finalSite_url.indexOf ("meipai.com") > 0) {
            url = "https://www.jqaaa.com/jq1/?url";
        } else if (finalSite_url.indexOf ("iqiyi.com") > 0) {
            url = "//5.5252e.com/jx/b.php?url=";
        } else if (finalSite_url.indexOf ("mgtv.com") > 0) {
            url = "//5.5252e.com/jx/b.php?url=";
        } else {
            url = "/jq1/?url=http://v.qq.com/x/cover/vfx3eugf3h4jiqg.html";
        }
        return url;
    }


    public void getGroupVideoInfo() {
        VipFragment vipFragment = getFragment ( );
        rxHelper.createSubscriber (apiService.getMovieGroup ("8.6.2"), new RxSubscriber<GroupVideoInfo> (activity, false) {
            @Override
            protected void _onNext(GroupVideoInfo bean) {
                vipFragment.setGroupVideoInfo (bean);
            }
        });
    }
}
