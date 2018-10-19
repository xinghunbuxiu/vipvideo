package com.vipvideo.ui.video;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import com.lixh.base.BaseActivity;
import com.lixh.utils.UToast;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.VideoPresenter;

import org.yczbj.ycvideoplayerlib.constant.ConstantKeys;
import org.yczbj.ycvideoplayerlib.controller.VideoPlayerController;
import org.yczbj.ycvideoplayerlib.inter.listener.OnPlayOrPauseListener;
import org.yczbj.ycvideoplayerlib.inter.listener.OnVideoControlListener;
import org.yczbj.ycvideoplayerlib.manager.VideoPlayerManager;
import org.yczbj.ycvideoplayerlib.player.VideoPlayer;

import butterknife.Bind;

/**
 * Created by LIXH on 2018/10/17.
 * email lixhVip9@163.com
 * des
 */

public class VideoPlayerActivity extends BaseActivity<VideoPresenter> {
    @Bind(R.id.video_player)
    VideoPlayer videoPlayer;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    public void initTitle(UToolBar toolBar) {

    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initVideoPlayer();
    }

    private void initVideoPlayer() {
        //设置播放类型
        //videoPlayer.setPlayerType(VideoPlayer.TYPE_NATIVE);
        videoPlayer.setPlayerType(ConstantKeys.IjkPlayerType.TYPE_IJK);
        //网络视频地址
        String videoUrl = "";
        //设置视频地址和请求头部
        videoPlayer.setUp(videoUrl, null);
        //是否从上一次的位置继续播放
        videoPlayer.continueFromLastPosition(true);
        //设置播放速度
        videoPlayer.setSpeed(1.0f);

        //创建视频控制器
        VideoPlayerController controller = new VideoPlayerController(this);
        controller.setTitle("高仿优酷视频播放页面");
        controller.setLoadingType(ConstantKeys.Loading.LOADING_QQ);
        controller.setTopVisibility(true);
        controller.imageView().setBackgroundResource(R.color.colorAccent);
        controller.setOnVideoBackListener(() -> {
            onBackPressed();
        });
        controller.setOnPlayOrPauseListener(new OnPlayOrPauseListener() {
            @Override
            public void onPlayOrPauseClick(boolean isPlaying) {

            }
        });
        controller.setOnVideoControlListener(new OnVideoControlListener() {
            @Override
            public void onVideoControlClick(int type) {
                switch (type) {
                    case ConstantKeys.VideoControl.DOWNLOAD:
                        UToast.showShort("下载音视频");
                        break;
                    case ConstantKeys.VideoControl.AUDIO:
                        UToast.showShort("切换音频");
                        break;
                    case ConstantKeys.VideoControl.SHARE:
                        UToast.showShort("分享内容");
                        break;
                    default:
                        break;
                }
            }
        });
        //设置视频控制器
        videoPlayer.setController(controller);
    }

    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }


    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_player_me_detail;
    }

}
