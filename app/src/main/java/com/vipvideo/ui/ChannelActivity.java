package com.vipvideo.ui;


import android.os.Bundle;

import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.lixh.base.BaseActivity;
import com.lixh.base.adapter.ItemListener;
import com.lixh.base.adapter.VBaseAdapter;
import com.lixh.base.adapter.recycleview.PageView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.bean.SearchVideoInfo;
import com.vipvideo.presenter.ChannelPresenter;
import com.vipvideo.ui.adpter.SearchVideoHolder;
import com.vipvideo.ui.video.VideoPlayerActivity;

import java.util.List;

/**
 * Created by LIXH on 2016/12/21.
 * email lixhVip9@163.com
 * des
 */
public class ChannelActivity extends BaseActivity<ChannelPresenter> {
    String key;
    PageView pageView;
    ChannelPresenter presenter;
    private List<SearchVideoInfo.DataBean> videoInfoDatas;
    VBaseAdapter mListAdapter;

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initTitle(UToolBar toolBar) {
        key = intent.getString("title");
        toolBar.setTitle(key);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        presenter = getPresenter();
        pageView = PageView.with(this)
                .setPullLoadMore(true)
                .setRefresh(true)
                .setAutoRefresh(true)
                .setDivideHeight(R.dimen.space_1)
                .setLoadTip(tip)
                .setOnLoadingListener(onLoadingListener)
                .setMaxRecycledViews(0, 20)
                .build();
        layout.setContentView(pageView.getRootView());
        initAdapter();
    }

    private void initAdapter() {
        mListAdapter = new VBaseAdapter<SearchVideoInfo>(getActivity())//
                .setLayout(R.layout.item_channel_search_video)//
                .setLayoutHelper(new LinearLayoutHelper(2))//
                .setHolder(SearchVideoHolder.class)//
                .setListener((ItemListener<SearchVideoInfo.DataBean>) (view, position, mData) -> {
                            intent.withString("workId", mData.getId() + "").go(VideoPlayerActivity.class);
                        }
                );
        pageView.addAdapter(mListAdapter);
    }

    PageView.OnLoadingListener onLoadingListener = new PageView.OnLoadingListener() {
        @Override
        public void load(int page, PageView.OnLoadFinish onLoadFinish) {
            presenter.loadPageInfo(key, page);
        }
    };

    public void setVideoInfoDatas(List<SearchVideoInfo.DataBean> videoInfoDatas) {
        this.videoInfoDatas = videoInfoDatas;
        if (!videoInfoDatas.isEmpty()) {
            mListAdapter.addDatas(videoInfoDatas);
            mListAdapter.notifyDataSetChanged();
        }
        pageView.onFinish();

    }
}
