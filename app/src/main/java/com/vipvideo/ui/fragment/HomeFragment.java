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
import com.vipvideo.bean.TitleBean;
import com.vipvideo.bean.VideoInfoBean;
import com.vipvideo.model.HomeModel;
import com.vipvideo.presenter.VideoPresenter;
import com.vipvideo.ui.adpter.BannerHolder;
import com.vipvideo.ui.adpter.HomeListHolder;
import com.vipvideo.ui.adpter.TitleHolder;
import com.vipvideo.ui.adpter.TopHotSearchHolder;
import com.vipvideo.ui.adpter.TopicCondHolder;

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
        page.onRefresh();
    }

    PageView.OnLoadingListener onLoadingListener = new PageView.OnLoadingListener() {
        @Override
        public void load(int page, PageView.OnLoadFinish onLoadFinish) {
            presenter.getMainInfo();
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

    private void initBannerView(HomeModel model) {
        bannerAdapter = new VBaseAdapter(getActivity())//
                .setLayout(R.layout.home_first_banner)//
                .setItem(model)
                .setLayoutHelper(new LinearLayoutHelper(0, 1))//
                .setHolder(BannerHolder.class)//
                .setListener((ItemListener<VideoInfoBean>) (view, position, mData) -> {

                });
        page.addAdapter(bannerAdapter);
    }

    private LayoutHelper getListHelper(int spanCount) {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(spanCount);
        gridLayoutHelper.setPadding(0, 16, 0, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(0);
        gridLayoutHelper.setBgColor(Color.WHITE);
        return gridLayoutHelper;
    }

    private void initListView(HomeModel model) {
        initBannerView(model);
        List<HomeModel.HotBean> hotBeans = model.getHotBeans();
        for (HomeModel.HotBean hotBean : hotBeans) {
            VBaseAdapter titleAdapter = new VBaseAdapter<TitleBean>(getActivity())//
                    .setLayout(R.layout.home_vlayout_title)//
                    .setLayoutHelper(new LinearLayoutHelper(0, 1))//
                    .setHolder(TitleHolder.class)
                    .setItem(new TitleBean(hotBean.getTypeName()));
            page.addAdapter(titleAdapter);
            VBaseAdapter mListAdapter = new VBaseAdapter<VideosBean>(getActivity());
            switch (hotBean.getType()) {
                case 3://大家都在搜
                    mListAdapter.setItem(hotBean)//
                            .setLayout(R.layout.hom_hot_search_layout)//
                            .setLayoutHelper(new LinearLayoutHelper())//
                            .setHolder(TopHotSearchHolder.class)//
                            .setListener((ItemListener<HomeModel.HotTvBean>) (view, position, mData) -> {

                                    }
                            );
                    break;
                case 0://热门话题
                    mListAdapter.setData(hotBean.getBeans())//
                            .setLayout(R.layout.home_vlayout_topic)//
                            .setLayoutHelper(getListHelper(2))//
                            .setHolder(TopicCondHolder.class)//
                            .setListener((ItemListener<HomeModel.HotTvBean>) (view, position, mData) -> {
                                mData.getLink();
                                    }
                            );
                    break;
                default:
                    mListAdapter.setItem(hotBean)//
                            .setLayout(R.layout.hom_hot_list_layout)//
                            .setLayoutHelper(new LinearLayoutHelper())//
                            .setHolder(HomeListHolder.class)//
                            .setListener((ItemListener<HomeModel.HotTvBean>) (view, position, mData) -> {

                                    }
                            );
                    break;
            }
            page.addAdapter(mListAdapter);
        }
    }

    public void finish() {
        page.finish(null, LoadingTip.LoadStatus.FINISH);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    public void setMainInfo(HomeModel model) {
        page.clear();
        initListView(model);
        finish();
    }
}
