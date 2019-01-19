package com.vipvideo.ui.video;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
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
import com.vipvideo.bean.MovieTypeBean.CondsBean.ValuesBean;
import com.vipvideo.presenter.VideoPresenter;
import com.vipvideo.ui.adpter.AllVideoFilterHolder;
import com.vipvideo.ui.adpter.AllVideoHolder;
import com.vipvideo.ui.adpter.AllVideoHotHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LIXH on 2018/10/17.
 * email lixhVip9@163.com
 * des
 */

public class AllVideoActivity extends BaseActivity<VideoPresenter> {

    List<DelegateAdapter.Adapter> adapters = new ArrayList<>();
    PageView page;
    TextView lastType;
    private MovieTypeBean movieTypeBean;
    private List<AllVideoInfo.VideoListBean.VideosBean> videoListBeans = new ArrayList<> ( );
    int pageSize = 18;
    VideoPresenter presenter;
    String order = "hot";
    String type = "";
    String area = "";
    String start = "";
    String actor = "";
    String lastStr = "[hotTab]/[actionTab]/[areaTab]/[timeTab]/[nameTab]";
    String reg = "[hotTab]/[actionTab]/[areaTab]/[timeTab]/[nameTab]";
    VBaseAdapter mListAdapter;
    VBaseAdapter hotAdapter;
    VBaseAdapter actionAdapter;
    VBaseAdapter areaAdapter;
    VBaseAdapter timeAdapter;
    VBaseAdapter nameAdapter;
    boolean isFirst = true;
    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setTitle ("电影筛选");
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init (savedInstanceState);
        presenter = getPresenter ( );
        type = intent.getString ("type");
        order = intent.getString("order", order);
        addTopNavView ( );
        initPageList();
        initFilterView();
        initVideoList();
        page.onRefresh();
    }

    private void initPageList() {
        page = PageView.with(this)
                .setAutoLoadMore(true)
                .setRefresh(true)
                .setDivideHeight(R.dimen.space_1)
                .setLoadTip(tip)
                .setOnLoadingListener(onLoadingListener)
                .setAutoRefresh(false)
                .setMaxRecycledViews(0, 20)
                .build();
        layout.setContentView(page.getRootView());
    }

    private void initFilterView() {
        hotAdapter = new VBaseAdapter<MovieTypeBean>(getActivity())//
                .setLayout(R.layout.layout_all_video_header)//
                .setLayoutHelper(new LinearLayoutHelper(0, 1))//
                .setHolder(AllVideoHotHolder.class)//
                .setListener((ItemListener<MovieTypeBean>) (view, position, mData) -> {
                            order = mData.getOrders().get(position).getField();
                            lastStr.replace("[hotTab]", order);
                            Pattern pat = Pattern.compile(reg);
                            Matcher mat = pat.matcher(lastStr);
                            lastStr = mat.replaceAll("");
                        }
                );
        actionAdapter = new VBaseAdapter<MovieTypeBean.CondsBean>(getActivity())//
                .setLayout(R.layout.layout_all_video_header)//
                .setLayoutHelper(new LinearLayoutHelper(0, 1))//
                .setHolder(AllVideoFilterHolder.class)//
                .setListener((ItemListener<MovieTypeBean.CondsBean>) (view, position, mData) -> {
                            type = mData.getValues().get(position).getTerm();
                            lastStr.replace("[actionTab]", order);
                            Pattern pat = Pattern.compile(reg);
                            Matcher mat = pat.matcher(lastStr);
                            lastStr = mat.replaceAll("");
                        }
                );
        areaAdapter = new VBaseAdapter<MovieTypeBean.CondsBean>(getActivity())//
                .setLayout(R.layout.layout_all_video_header)//
                .setLayoutHelper(new LinearLayoutHelper(0, 1))//
                .setHolder(AllVideoFilterHolder.class)//
                .setListener((ItemListener<MovieTypeBean.CondsBean>) (view, position, mData) -> {
                            area = mData.getValues().get(position).getTerm();
                            lastStr.replace("[areaTab]", order);
                            Pattern pat = Pattern.compile(reg);
                            Matcher mat = pat.matcher(lastStr);
                            lastStr = mat.replaceAll("");
                        }
                );
        timeAdapter = new VBaseAdapter<MovieTypeBean.CondsBean>(getActivity())//
                .setLayout(R.layout.layout_all_video_header)//
                .setLayoutHelper(new LinearLayoutHelper(0, 1))//
                .setHolder(AllVideoFilterHolder.class)//
                .setListener((ItemListener<MovieTypeBean.CondsBean>) (view, position, mData) -> {
                            start = mData.getValues().get(position).getTerm();
                            lastStr.replace("[timeTab]", order);
                            Pattern pat = Pattern.compile(reg);
                            Matcher mat = pat.matcher(lastStr);
                            lastStr = mat.replaceAll("");
                        }
                );
        nameAdapter = new VBaseAdapter<MovieTypeBean.CondsBean>(getActivity())//
                .setLayout(R.layout.layout_all_video_header)//
                .setLayoutHelper(new LinearLayoutHelper(0, 1))//
                .setHolder(AllVideoFilterHolder.class)//
                .setListener((ItemListener<MovieTypeBean.CondsBean>) (view, position, mData) -> {
                            actor = mData.getValues().get(position).getTerm();
                            lastStr.replace("[nameTab]", order);
                            Pattern pat = Pattern.compile(reg);
                            Matcher mat = pat.matcher(lastStr);
                            lastStr = mat.replaceAll("");
                        }
                );
        page.addAdapter(hotAdapter);
        page.addAdapter(actionAdapter);
        page.addAdapter(areaAdapter);
        page.addAdapter(timeAdapter);
        page.addAdapter(nameAdapter);
    }

    public void initVideoList() {
        mListAdapter = new VBaseAdapter(getActivity())//
                .setData(new ArrayList<AllVideoInfo.VideoListBean.VideosBean>())//
                .setLayout(R.layout.layout_all_video_rv_item)//
                .setLayoutHelper(getListHelper())//
                .setHolder(AllVideoHolder.class)//
                .setListener((ItemListener<AllVideoInfo.VideoListBean.VideosBean>) (view, position, mData) -> {
                            intent.withString("workId", mData.getWorks_id()).go(VideoPlayerActivity.class);

                        }
                );
        page.addAdapter(mListAdapter);
    }

    private void fillFilterView(MovieTypeBean movieTypeBean) {
        hotAdapter.setItem(movieTypeBean);
        hotAdapter.notifyDataSetChanged();
        ValuesBean all = new ValuesBean();
        all.setTitle("全部");
        all.setTerm("");
        for (MovieTypeBean.CondsBean bean : movieTypeBean.getConds()) {
            bean.getValues().add(0, all);
            switch (bean.getField()) {
                case "type":
                    actionAdapter.setItem(bean);
                    actionAdapter.notifyDataSetChanged();
                    break;
                case "area":
                    areaAdapter.setItem(bean);
                    areaAdapter.notifyDataSetChanged();
                    break;
                case "start":
                    timeAdapter.setItem(bean);
                    timeAdapter.notifyDataSetChanged();
                    break;
                case "actor":
                    nameAdapter.setItem(bean);
                    nameAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private void addTopNavView() {
        lastType = new TextView (getApplicationContext ( ));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px (40));
        lp.addRule (RelativeLayout.CENTER_HORIZONTAL);
        layout.addView (lastType, lp, true);
    }

    PageView.OnLoadingListener onLoadingListener = (page, onLoadFinish) -> getMoveInfo(page, order, actor, start, area, type);

    private void getMoveInfo(int page, String order, String actor, String start, String area, String type) {
        Map<String, String> params = new TreeMap<>();
        params.put ("beg", page * pageSize + "");
        params.put ("end", (page + 1) * pageSize + "");
        params.put("order", order);
        if (!TextUtils.isEmpty(type)) {
            params.put("type", type);
        }
        if (!TextUtils.isEmpty(area)) {
            params.put("area", area);
        }
        if (!TextUtils.isEmpty(start)) {
            params.put("start", start);
        }
        if (!TextUtils.isEmpty(actor)) {
            params.put("actor", actor);
        }
        presenter.getMovieByWhere (page, params);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }




    private LayoutHelper getListHelper() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setPadding(16, 16, 16, 16);
        // 控制子元素之间的垂直间距
        gridLayoutHelper.setVGap(16);
        // 控制子元素之间的水平间距
        gridLayoutHelper.setHGap(16);
        gridLayoutHelper.setBgColor(Color.WHITE);
        return gridLayoutHelper;
    }
    public void setVideoInfo(int page, AllVideoInfoBean videoInfo) {
        this.page.finish(LoadingTip.LoadStatus.FINISH);
        if (isFirst) {
            isFirst = false;
            movieTypeBean = videoInfo.getMovieTypeBean ( );
            fillFilterView(movieTypeBean);
        }
        videoListBeans.addAll(videoInfo.getAllVideoInfo().getVideo_list().getVideos());
        mListAdapter.setData(videoListBeans);
        mListAdapter.notifyDataSetChanged();
    }

}
