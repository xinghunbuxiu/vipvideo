package com.vipvideo.ui.video;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lixh.base.BaseActivity;
import com.lixh.base.adapter.ItemListener;
import com.lixh.base.adapter.VBaseAdapter;
import com.lixh.utils.Alert;
import com.lixh.utils.UToast;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.bean.VideoInfoBean;
import com.vipvideo.presenter.VideoPresenter;
import com.vipvideo.ui.adpter.VideoTopDesHolder;
import com.vipvideo.ui.adpter.VideoTopShareHolder;

import org.yczbj.ycvideoplayerlib.constant.ConstantKeys;
import org.yczbj.ycvideoplayerlib.controller.VideoPlayerController;
import org.yczbj.ycvideoplayerlib.manager.VideoPlayerManager;
import org.yczbj.ycvideoplayerlib.player.VideoPlayer;

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
    VideoPlayer videoPlayer;
    @Bind(R.id.rv_video)
    RecyclerView rvVideo;
    //网络视频地址
    String videoUrl = "";
    DelegateAdapter delegateAdapter;
    VideoInfoBean bean;
    VideoPresenter presenter;
    String workId;
    private VBaseAdapter shareAdapter, topDesAdapter;
    VideoPlayerController controller;
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
                                presenter.getRealPath (sitesBean.getSite_url ( ));
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
        //设置播放类型
        //videoPlayer.setPlayerType(VideoPlayer.TYPE_NATIVE);
        videoPlayer.setPlayerType (ConstantKeys.IjkPlayerType.TYPE_IJK);

        //是否从上一次的位置继续播放
        videoPlayer.continueFromLastPosition (true);
        //设置播放速度
        videoPlayer.setSpeed (1.0f);

        //创建视频控制器
        controller = new VideoPlayerController(this);
        controller.setTitle ("高仿优酷视频播放页面");
        controller.setLoadingType (ConstantKeys.Loading.LOADING_QQ);
        controller.setTopVisibility (true);
        controller.imageView ( ).setBackgroundResource (R.color.colorAccent);
        controller.setOnVideoBackListener (() -> {
            onBackPressed ( );
        });
        controller.setOnPlayOrPauseListener (isPlaying -> {

        });
        controller.setOnVideoControlListener (type -> {
            switch (type) {
                case ConstantKeys.VideoControl.DOWNLOAD:
                    UToast.showShort ("下载音视频");
                    break;
                case ConstantKeys.VideoControl.AUDIO:
                    UToast.showShort ("切换音频");
                    break;
                case ConstantKeys.VideoControl.SHARE:
                    UToast.showShort ("分享内容");
                    break;
                default:
                    break;
            }
        });
        //设置视频控制器
        videoPlayer.setController (controller);
    }

    @Override
    protected void onStop() {
        super.onStop ( );
        VideoPlayerManager.instance ( ).releaseVideoPlayer ( );
    }


    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance ( ).onBackPressed ( )) {
            return;
        }
        super.onBackPressed ( );
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
        String videoUrl = bean.getSites ( ).get (0).getSite_url ( );
        presenter.getRealPath (videoUrl);
    }

    public void setRealPath(String realPath) {
        this.videoUrl = realPath;
        controller.setTitle(videoUrl);
        //设置视频地址和请求头部
        videoPlayer.setUp(videoUrl, null);
        videoPlayer.start();
    }
}
