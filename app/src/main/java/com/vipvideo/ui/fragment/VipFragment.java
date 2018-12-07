package com.vipvideo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lixh.base.BaseFragment;
import com.lixh.base.adapter.ItemListener;
import com.lixh.base.adapter.VBaseAdapter;
import com.lixh.base.adapter.recycleview.PageView;
import com.lixh.utils.LoadingTip;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.bean.GroupVideoInfo;
import com.vipvideo.bean.GroupVideoInfo.SlicesBean.VideosBean;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.bean.MovieTypeBean.TopcondsBean;
import com.vipvideo.bean.TitleBean;
import com.vipvideo.bean.VideoInfoBean;
import com.vipvideo.presenter.VideoPresenter;
import com.vipvideo.ui.adpter.BannerHolder;
import com.vipvideo.ui.adpter.MoreHolder;
import com.vipvideo.ui.adpter.TitleHolder;
import com.vipvideo.ui.adpter.TopCondHolder;
import com.vipvideo.ui.adpter.VideoHolder;
import com.vipvideo.ui.video.AllVideoActivity;

import java.util.ArrayList;
import java.util.List;


public class VipFragment extends BaseFragment<VideoPresenter> {
    PageView page;
    private VBaseAdapter bannerAdapter,//首页轮播图
            topCondAdapter;//二级筛选类型

    VideoPresenter presenter;

    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setVisibility(View.GONE);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        presenter = getPresenter();
        initVLayout();
        initAllTypeView();
        initTopData();
        page.onRefresh();
    }

    private void initTopData() {
        presenter.getBannerData();
        presenter.getAllMovieType();
    }

    PageView.OnLoadingListener onLoadingListener = new PageView.OnLoadingListener() {
        @Override
        public void load(int page, PageView.OnLoadFinish onLoadFinish) {
            presenter.getGroupVideoInfo();
        }
    };

    private void initVLayout() {
        page = PageView.with(activity)
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

    /**
     * 添加不同类型数据布局
     */
    private void initAllTypeView() {
        initBannerView();
        initSecondView();
//        page.addAdapter(bannerAdapter);
        page.addAdapter(topCondAdapter);
    }

    private void initBannerView() {
        bannerAdapter = new VBaseAdapter(getActivity())//
                .setLayout(R.layout.home_first_banner)//
                .setLayoutHelper(new LinearLayoutHelper(0, 1))//
                .setHolder(BannerHolder.class)//
                .setListener((ItemListener<VideoInfoBean>) (view, position, mData) -> {

                });
    }

    //筛选

    private void initSecondView() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        topCondAdapter = new VBaseAdapter(getActivity())//
                .setData(new ArrayList<TopcondsBean>())//
                .setLayout(R.layout.home_sencond_layout)//
                .setLayoutHelper(gridLayoutHelper)//
                .setHolder(TopCondHolder.class)//
                .setListener((ItemListener<TopcondsBean>) (view, position, mData) -> {
                            intent.withString("type", mData.getType()).withString("order", mData.getOrder()).go(AllVideoActivity.class);
                        }
                );

    }


    private void initListView(List<DelegateAdapter.Adapter> adapters, GroupVideoInfo.SlicesBean slicesBean) {
        VBaseAdapter titleAdapter = new VBaseAdapter<TitleBean>(getActivity())//
                .setLayout(R.layout.home_vlayout_title)//
                .setLayoutHelper(new LinearLayoutHelper())//
                .setHolder(TitleHolder.class)
                .setItem(new TitleBean(slicesBean.getName()));
        VBaseAdapter mMoreAdapter = new VBaseAdapter<TitleBean>(getActivity())//
                .setItem(new TitleBean("更多"))//
                .setLayout(R.layout.home_vlayout_more)//
                .setLayoutHelper(new LinearLayoutHelper())//
                .setHolder(MoreHolder.class);

        VBaseAdapter mListAdapter = new VBaseAdapter<VideosBean>(getActivity())//
                .setData(slicesBean.getVideos())//
                .setLayout(R.layout.home_video_layout)//
                .setLayoutHelper(getListHelper())//
                .setHolder(VideoHolder.class)//
                .setListener((ItemListener<VideosBean>) (view, position, mData) -> {

                        }
                );
        adapters.add(titleAdapter);
        adapters.add(mListAdapter);
        adapters.add(mMoreAdapter);
    }

    public void finish() {
        page.finish(null, LoadingTip.LoadStatus.FINISH);
    }

    private LayoutHelper getListHelper() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        return gridLayoutHelper;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    //类型填充
    public void setMovieTypeBean(MovieTypeBean movieTypeBean) {
        TopcondsBean all = movieTypeBean.getTopconds().get(0);//全部
        List<TopcondsBean> list = movieTypeBean.getTopconds().subList(3, movieTypeBean.getTopconds().size());
        list.add(all);
        topCondAdapter.setData(list);
        topCondAdapter.notifyDataSetChanged();
    }

    public void setGroupVideoInfo(GroupVideoInfo groupVideoInfo) {
        List<DelegateAdapter.Adapter> adapters = new ArrayList<>();
        for (GroupVideoInfo.SlicesBean slicesBean : groupVideoInfo.getSlices()) {
            if (!slicesBean.getName().equals("index_flash")) {
                initListView(adapters, slicesBean);
            }
        }
        Log.e("nnihao", "aaaaaaaaaaaaaaaaaaaa");
        page.addAllAdapter(adapters);

    }
}
