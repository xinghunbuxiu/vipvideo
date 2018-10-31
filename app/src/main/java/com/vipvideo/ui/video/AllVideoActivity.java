package com.vipvideo.ui.video;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lixh.base.BaseActivity;
import com.lixh.base.adapter.ItemListener;
import com.lixh.base.adapter.VBaseAdapter;
import com.lixh.base.adapter.recycleview.PageView;
import com.lixh.utils.DensityUtil;
import com.lixh.utils.LoadingTip;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.bean.AllVideoInfo;
import com.vipvideo.bean.AllVideoInfoBean;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.bean.VideoInfoBean;
import com.vipvideo.presenter.VideoPresenter;
import com.vipvideo.ui.adpter.AllVideoFilterHolder;
import com.vipvideo.ui.adpter.VideoHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LIXH on 2018/10/17.
 * email lixhVip9@163.com
 * des
 */

public class AllVideoActivity extends BaseActivity<VideoPresenter> {


    PageView page;
    TextView lastType;
    private MovieTypeBean movieTypeBean;
    private List<AllVideoInfo.VideoListBean.VideosBean> videoListBeans = new ArrayList<> ( );
    int pageSize = 20;
    VideoPresenter presenter;
    String order = "hot";
    String type = "";
    String area = "";
    String start = "";
    String actor = "";
    VBaseAdapter tabsAdapter, mListAdapter;
    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setTitle ("电影筛选");
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init (savedInstanceState);
        presenter = getPresenter ( );
        type = intent.getString ("type");
        order = intent.getString ("order");
        addTopNavView ( );
        initPageList ( );
        initFilterView();
        initVideoList();
    }

    private void initPageList() {
        page = PageView.with(this)
                .setPullLoadMore(true)
                .setRefresh(true)
                .setDivideHeight(R.dimen.space_1)
                .setLoadTip(tip)
                .setOnLoadingListener(onLoadingListener)
                .setAutoRefresh(false)
                .setMaxRecycledViews(0, 20)
                .build();
        layout.setContentView(page.getRootView());
    }

    public void initVideoList() {
        mListAdapter = new VBaseAdapter(getActivity())//
                .setData(new ArrayList<AllVideoInfo.VideoListBean.VideosBean>())//
                .setLayout(R.layout.layout_all_video_rv_item)//
                .setLayoutHelper(getListHelper())//
                .setHolder(VideoHolder.class)//
                .setListener((ItemListener<AllVideoInfo.VideoListBean.VideosBean>) (view, position, mData) -> {
                            intent.withString("workId", mData.getWorks_id()).go(VideoPlayerActivity.class);

                        }
                );
        page.addAdapter(mListAdapter);
    }

    private void initFilterView() {
        tabsAdapter = new VBaseAdapter(getActivity())//
                .setData(new ArrayList<MovieTypeBean>())//
                .setLayout(R.layout.home_first_banner)//
                .setLayoutHelper(new LinearLayoutHelper())//
                .setHolder(AllVideoFilterHolder.class)//
                .setListener((ItemListener<VideoInfoBean>) (view, position, mData) -> {

                        }
                );
        page.addAdapter(tabsAdapter);
    }
    private void addTopNavView() {
        lastType = new TextView (getApplicationContext ( ));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px (getApplicationContext ( ), 40));
        lp.addRule (RelativeLayout.CENTER_HORIZONTAL);
        layout.addView (lastType, lp, true);
    }

    PageView.OnLoadingListener onLoadingListener = (page, onLoadFinish) -> getMoveInfo(page, order, actor, start, area, type);

    private void getMoveInfo(int page, String order, String actor, String start, String area, String type) {
        Map<String, String> params = new HashMap<> ( );
        params.put ("beg", page * pageSize + "");
        params.put ("end", (page + 1) * pageSize + "");
        params.put ("order", order == null ? "" : order);
        params.put ("type", type == null ? "" : type);
        params.put ("area", area);
        params.put ("start", start);
        params.put ("actor", actor);
        presenter.getMovieByWhere (page, params);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }


    private LayoutHelper getListHelper() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        return gridLayoutHelper;
    }
    public void setVideoInfo(int page, AllVideoInfoBean videoInfo) {
        if (page == 0) {
            movieTypeBean = videoInfo.getMovieTypeBean ( );
            tabsAdapter.setItem(movieTypeBean);
            tabsAdapter.notifyDataSetChanged();
        }
        videoListBeans.addAll(videoInfo.getAllVideoInfo().getVideo_list().getVideos());
        mListAdapter.setData(videoListBeans);
        mListAdapter.notifyDataSetChanged();
        this.page.finish (null, LoadingTip.LoadStatus.FINISH);

    }

}
