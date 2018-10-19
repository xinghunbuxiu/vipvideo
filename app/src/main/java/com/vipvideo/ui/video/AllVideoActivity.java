package com.vipvideo.ui.video;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixh.base.BaseActivity;
import com.lixh.base.Page;
import com.lixh.base.adapter.recycleview.BaseViewHolder;
import com.lixh.utils.DensityUtil;
import com.lixh.utils.LoadingTip;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.bean.AllVideoInfo;
import com.vipvideo.bean.AllVideoInfoBean;
import com.vipvideo.bean.MovieTypeBean;
import com.vipvideo.presenter.VideoPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LIXH on 2018/10/17.
 * email lixhVip9@163.com
 * des
 */

public class AllVideoActivity extends BaseActivity<VideoPresenter> {

    String lastStr = "[hotTab]/[actionTab]/[areaTab]/[timeTab]/[nameTab]";
    String reg = "[hotTab]/[actionTab]/[areaTab]/[timeTab]/[nameTab]";
    Page.Builder builder;
    Page page;
    TabLayout hotTab;
    TabLayout actionTab;
    TabLayout areaTab;
    TabLayout timeTab;
    TabLayout nameTab;
    TextView lastType;
    private MovieTypeBean movieTypeBean;
    private List<AllVideoInfo.VideoListBean.VideosBean> videoListBeans;
    int pageSize = 20;
    VideoPresenter presenter;
    String order = "hot";
    String type = "";
    String area = "";
    String start = "";
    String actor = "";

    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setTitle("电影筛选");
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        presenter = getPresenter();
        type = intent.getString("type");
        order = intent.getString("order");
        addTopNavView();
        initPageList();

    }

    private void initPageList() {
        builder = new Page.Builder<AllVideoInfo.VideoListBean.VideosBean>(getApplicationContext()) {
            @Override
            public void onBindViewData(BaseViewHolder viewHolder, int position, AllVideoInfo.VideoListBean.VideosBean bean) {
                viewHolder.setText(R.id.tv_title, bean.getTitle());
                viewHolder.setImageUrl(R.id.iv_video, bean.getImg_url());
            }

            @Override
            public void onBindHeaderViewData(BaseViewHolder headerView, int position) {
                hotTab = (TabLayout) headerView.$(R.id.hot_tab);
                actionTab = (TabLayout) headerView.$(R.id.hot_tab);
                areaTab = (TabLayout) headerView.$(R.id.hot_tab);
                timeTab = (TabLayout) headerView.$(R.id.hot_tab);
                nameTab = (TabLayout) headerView.$(R.id.hot_tab);
                addOnTabSelectedListener(hotTab);
                addOnTabSelectedListener(actionTab);
                addOnTabSelectedListener(areaTab);
                addOnTabSelectedListener(timeTab);
                addOnTabSelectedListener(nameTab);
            }

            public void addOnTabSelectedListener(TabLayout layout) {
                layout.setTabMode(TabLayout.MODE_SCROLLABLE);
                layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        switch (layout.getId()) {
                            case R.id.hot_tab:
                                order = tab.getText().toString();
                                lastStr.replace("[hot_tab]", order);
                                break;
                            case R.id.action_tab:
                                type = tab.getText().toString();
                                lastStr.replace("[action_tab]", type);
                                break;
                            case R.id.time_tab:
                                start = tab.getText().toString();
                                lastStr.replace("[time_tab]", start);
                                break;
                            case R.id.name_tab:
                                actor = tab.getText().toString();
                                lastStr.replace("[name_tab]", actor);
                                break;
                            case R.id.area_tab:
                                area = tab.getText().toString();
                                lastStr.replace("[area_tab]", area);
                                break;
                        }
                        Pattern pat = Pattern.compile(reg);
                        Matcher mat = pat.matcher(lastStr);
                        lastStr = mat.replaceAll("");
                        lastType.setText(lastStr);
                        page.onRefresh();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        }.setAutoLoadMore(true).setRefresh(true).setDivideHeight(R.dimen.space_1).setLoadTip(tip);
        builder.setOnLoadingListener(onLoadingListener).setAutoRefresh(true);
        builder.addHeaderView(R.layout.layout_all_video_header);
        builder.setArrayAdapter(R.layout.layout_all_video_rv_item);
        page = builder.Build(Page.PageType.Grid);
        layout.setContentView(page.getRootView());
    }

    private void addTopNavView() {
        lastType = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(getApplicationContext(), 40));
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layout.addView(lastType, lp, true);
    }

    Page.OnLoadingListener onLoadingListener = new Page.OnLoadingListener() {
        @Override
        public void load(int page, final Page.OnLoadFinish onLoadFinish) {
            getMoveInfo(page, order, actor, start, area, type);
        }
    };

    private void getMoveInfo(int page, String order, String actor, String start, String area, String type) {
        Map<String, String> params = new HashMap<>();
        params.put("beg", page * pageSize + "");
        params.put("end", (page + 1) * pageSize + "");
        params.put("order", order);
        params.put("type", type);
        params.put("area", area);
        params.put("start", start);
        params.put("actor", actor);
        presenter.getMovieByWhere(params);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }


    public void setVideoInfo(AllVideoInfoBean videoInfo) {
        movieTypeBean = videoInfo.getMovieTypeBean();
        videoListBeans = videoInfo.getAllVideoInfo().getVideo_list().getVideos();
        page.finish(videoListBeans, LoadingTip.LoadStatus.FINISH);
        for (MovieTypeBean.CondsBean condsBean : movieTypeBean.getConds()) {
            for (MovieTypeBean.CondsBean.ValuesBean bean : condsBean.getValues()) {
                switch (condsBean.getField()) {
                    case "type"://类型
                        actionTab.addTab(actionTab.newTab().setText("全部"));
                        actionTab.addTab(actionTab.newTab().setText(condsBean.getName()));
                        break;
                    case "area"://区域
                        areaTab.addTab(areaTab.newTab().setText("全部"));
                        areaTab.addTab(areaTab.newTab().setText(condsBean.getName()));
                        break;
                    case "start"://年代
                        timeTab.addTab(timeTab.newTab().setText("全部"));
                        timeTab.addTab(timeTab.newTab().setText(condsBean.getName()));
                        break;
                    case "actor"://演员
                        nameTab.addTab(nameTab.newTab().setText("全部"));
                        nameTab.addTab(nameTab.newTab().setText(condsBean.getName()));
                        break;
                }
            }
            for (MovieTypeBean.CondsBean bean : movieTypeBean.getOrders()) {
                hotTab.addTab(hotTab.newTab().setText(bean.getName()));
            }
        }
    }

}
