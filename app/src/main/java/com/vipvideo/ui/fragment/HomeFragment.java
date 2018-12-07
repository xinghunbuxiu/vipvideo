package com.vipvideo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

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
import com.vipvideo.bean.GroupVideoInfo.SlicesBean.VideosBean;
import com.vipvideo.bean.MovieTypeBean.TopcondsBean;
import com.vipvideo.bean.TitleBean;
import com.vipvideo.bean.VideoInfoBean;
import com.vipvideo.model.HomeModel;
import com.vipvideo.presenter.VideoPresenter;
import com.vipvideo.ui.adpter.BannerHolder;
import com.vipvideo.ui.adpter.TitleHolder;
import com.vipvideo.ui.adpter.TopicCondHolder;
import com.vipvideo.ui.adpter.VideoHolder;
import com.vipvideo.ui.video.AllVideoActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<VideoPresenter> {
    PageView page;
    private VBaseAdapter bannerAdapter,//首页轮播图
            topicAdapter;//二级筛选类型

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
        initTopicView();
        page.addAdapter(bannerAdapter);
        page.addAdapter(topicAdapter);
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

    private void initTopicView() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        topicAdapter = new VBaseAdapter(getActivity())//
                .setData(new ArrayList<TopcondsBean>())//
                .setLayout(R.layout.home_vlayout_huati)//
                .setLayoutHelper(gridLayoutHelper)//
                .setHolder(TopicCondHolder.class)//
                .setListener((ItemListener<TopcondsBean>) (view, position, mData) -> {
                            intent.withString("type", mData.getType()).withString("order", mData.getOrder()).go(AllVideoActivity.class);
                        }
                );

    }


    private void initListView(HomeModel model) {
        List<HomeModel.HotBean> hotBeans = model.getHotBeans();
        for (HomeModel.HotBean hotBean : hotBeans) {
            VBaseAdapter titleAdapter = new VBaseAdapter<TitleBean>(getActivity())//
                    .setLayout(R.layout.home_vlayout_title)//
                    .setLayoutHelper(new LinearLayoutHelper())//
                    .setHolder(TitleHolder.class)
                    .setItem(new TitleBean(hotBean.getTypeName()));

            VBaseAdapter mListAdapter = new VBaseAdapter<HomeModel.HotTvBean>(getActivity())//
                    .setData(hotBean.getBeans())//
                    .setLayout(R.layout.home_video_layout)//
                    .setLayoutHelper(getListHelper())//
                    .setHolder(VideoHolder.class)//
                    .setListener((ItemListener<VideosBean>) (view, position, mData) -> {

                            }
                    );
            adapters.add(titleAdapter);
            adapters.add(mListAdapter);
        }
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

    public void setMainInfo(HomeModel model) {
        initListView(model);
    }
}
