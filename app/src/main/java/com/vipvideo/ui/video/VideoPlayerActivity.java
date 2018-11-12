package com.vipvideo.ui.video;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lixh.base.BaseActivity;
import com.lixh.base.adapter.ItemListener;
import com.lixh.base.adapter.VBaseAdapter;
import com.lixh.utils.Alert;
import com.lixh.view.UToolBar;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import com.vipvideo.R;
import com.vipvideo.bean.VideoInfoBean;
import com.vipvideo.presenter.VideoPresenter;
import com.vipvideo.ui.adpter.VideoTopDesHolder;
import com.vipvideo.ui.adpter.VideoTopShareHolder;
import com.vipvideo.util.AdFilterTool;
import com.vipvideo.util.ParseWebView;
import com.vipvideo.view.LandLayoutVideo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by LIXH on 2018/10/17.
 * email lixhVip9@163.com
 * des
 */

public class VideoPlayerActivity extends BaseActivity<VideoPresenter> {
    @Bind(R.id.video_player)
    LandLayoutVideo detailPlayer;
    @Bind(R.id.rv_video)
    RecyclerView rvVideo;
    @Bind(R.id.my_webView)
    ParseWebView webView;
    //网络视频地址
    String videoUrl = "";
    DelegateAdapter delegateAdapter;
    VideoInfoBean bean;
    VideoPresenter presenter;
    String workId;
    private VBaseAdapter shareAdapter, topDesAdapter;
    private OrientationUtils orientationUtils;

    private GSYVideoOptionBuilder gsyVideoOptionBuilder;
    private boolean isPlay;
    private boolean isPause;
    private String sit_host;


    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setVisibility (View.GONE);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init (savedInstanceState);
        workId = intent.getString ("workId");
        initVLayout ( );
        initVideoPlayer ( );
        initAllType ( );
        presenter = getPresenter ( );
        presenter.getMovieByWorkId (workId);
    }

    private void initVLayout() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager (this);
        rvVideo.setLayoutManager (virtualLayoutManager);
        //设置缓存view个数(当视图中view的个数很多时，设置合理的缓存大小，防止来回滚动时重新创建 View)
        final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool ( );
        rvVideo.setRecycledViewPool (viewPool);
        viewPool.setMaxRecycledViews (0, 1);
        viewPool.setMaxRecycledViews (0, 1);
        delegateAdapter = new DelegateAdapter (virtualLayoutManager, false);
    }

    private void initAllType() {

        shareAdapter = new VBaseAdapter (this)//
                .setData (new ArrayList<VideoInfoBean> ( ))//
                .setLayout (R.layout.layout_play_video_rv_top_share)//
                .setLayoutHelper (new LinearLayoutHelper ( ))//
                .setHolder (VideoTopShareHolder.class)//
                .setListener ((ItemListener<VideoInfoBean>) (view, position, mData) -> {
                            List<VideoInfoBean.SitesBean> sitesBeans = mData.getSites ( );
                            String sites[] = new String[sitesBeans.size ( )];
                            for (int i = 0; i < sitesBeans.size ( ); i++) {
                                sites[i] = sitesBeans.get (i).getSite_name ( );
                            }
                            Alert.displayAlertSingledDialog (this, sites, (parent, v, position1, id) -> {
                                VideoInfoBean.SitesBean sitesBean = sitesBeans.get (position1);
                                ((TextView) view).setText (sitesBean.getSite_name ( ));
                                switchLine (sitesBean.getSite_url ( ));
                            });

                        }
                );

        topDesAdapter = new VBaseAdapter (this)//
                .setData (new ArrayList<VideoInfoBean> ( ))//
                .setLayout (R.layout.layout_play_video_rv_top_des)//
                .setLayoutHelper (new LinearLayoutHelper ( ))//
                .setHolder (VideoTopDesHolder.class)//
                .setListener ((view, position, mData) -> {

                });
        delegateAdapter.addAdapter (topDesAdapter);
        delegateAdapter.addAdapter (shareAdapter);
        rvVideo.setAdapter (delegateAdapter);
    }

    private void initVideoPlayer() {

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils (this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable (false);
        gsyVideoOptionBuilder = new GSYVideoOptionBuilder ( )
                .setIsTouchWiget (true)
                .setRotateViewAuto (false)
                .setLockLand (false)
                .setShowFullAnimation (false)
                .setNeedLockFull (true)
                .setSeekRatio (1)
                .setCacheWithPlay (true)
                .setVideoTitle ("测试视频")
                .setVideoAllCallBack (new GSYSampleCallBack ( ) {
                    @Override
                    public void onStartPrepared(String url, Object... objects) {
                        super.onStartPrepared(url, objects);
                    }

                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared (url, objects);
                        //开始播放了才能旋转和全屏
                        orientationUtils.setEnable (true);
                        isPlay = true;
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen (url, objects);
                        if (orientationUtils != null) {
                            orientationUtils.backToProtVideo ( );
                        }
                    }
                });
        gsyVideoOptionBuilder.build (detailPlayer);
        detailPlayer.getFullscreenButton ( ).setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick ( );
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen (getActivity ( ), true, true);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo ( );
        }

        if (GSYVideoManager.backFromWindowFull (this)) {
            return;
        }
        super.onBackPressed ( );
    }

    @Override
    protected void onPause() {
        getCurPlay ( ).onVideoPause ( );
        super.onPause ( );
        isPause = true;
    }

    @Override
    protected void onResume() {
        getCurPlay ( ).onVideoResume ( );
        super.onResume ( );
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        if (isPlay) {
            getCurPlay ( ).release ( );
        }
        if (orientationUtils != null)
            orientationUtils.releaseListener ( );
        super.onDestroy ( );
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged (newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            detailPlayer.onConfigurationChanged (this, newConfig, orientationUtils, true, true);
        }
    }

    private GSYVideoPlayer getCurPlay() {
        if (detailPlayer.getFullWindowPlayer ( ) != null) {
            return detailPlayer.getFullWindowPlayer ( );
        }
        return detailPlayer;
    }

    private void playVideo() {
        if (TextUtils.isEmpty (videoUrl) || detailPlayer == null) {
            detailPlayer.onLoadError();
            return;
        }
        detailPlayer.release ( );
        gsyVideoOptionBuilder.setUrl (videoUrl)
                .setCacheWithPlay (true)
                .setVideoTitle ("测试视频")
                .build (detailPlayer);
        gsyVideoOptionBuilder.build (detailPlayer);
        detailPlayer.postDelayed (() -> detailPlayer.startPlayLogic ( ), 1000);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_player_me_detail;
    }

    public void VideoInfoBean(VideoInfoBean bean) {
        ArrayList<VideoInfoBean> b = new ArrayList<VideoInfoBean> ( );
        b.add (bean);
        topDesAdapter.setData (b);
        topDesAdapter.notifyDataSetChanged ( );
        shareAdapter.setData (b);
        shareAdapter.notifyDataSetChanged ( );
        videoUrl = bean.getSites ( ).get (0).getSite_url ( );
        sit_host = bean.getSites ( ).get (0).getSite_domain ( );
        switchLine (videoUrl);

    }

    public void switchLine(String videoUrl) {
        detailPlayer.onLoading();
        String finalSite_url = "http://www.1717yun.com/jx/ty.php?url=" + videoUrl;
        webView.setInterceptRequest ((view, url) -> {
            //判断是否是广告相关的资源链接
            if (AdFilterTool.isAd("www.1717yun.com", url)) {
                //有广告的请求数据，我们直接返回空数据，注：不能直接返回null
                return new WebResourceResponse (null, null, null);
            }
            Log.e ("setInterceptRequest11", url);
            //这里是不做处理的数据
            return null;
        });
        webView.setOnParseListener (url -> setRealPath (url));
        webView.loadUrl (finalSite_url);
    }

    public void setRealPath(String realPath) {
        this.videoUrl = realPath;
        playVideo ( );
    }

}
