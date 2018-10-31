package com.vipvideo.ui.adpter;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.bean.MovieTypeBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class AllVideoFilterHolder extends VBaseHolder<MovieTypeBean> {
    @Bind(R.id.hot_tab)
    TabLayout hotTab;
    @Bind(R.id.name_tab)
    TabLayout nameTab;
    @Bind(R.id.action_tab)
    TabLayout actionTab;
    @Bind(R.id.area_tab)
    TabLayout areaTab;
    @Bind(R.id.time_tab)
    TabLayout timeTab;
    String order = "hot";
    String type = "";
    String area = "";
    String start = "";
    String actor = "";

    String lastStr = "[hotTab]/[actionTab]/[areaTab]/[timeTab]/[nameTab]";
    String reg = "[hotTab]/[actionTab]/[areaTab]/[timeTab]/[nameTab]";

    public AllVideoFilterHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, MovieTypeBean typeBean) {
        super.setData(ps, typeBean);
        for (MovieTypeBean.CondsBean condsBean : typeBean.getConds()) {
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
        }
        for (MovieTypeBean.CondsBean condsBean : typeBean.getOrders()) {
            hotTab.addTab(hotTab.newTab().setText(condsBean.getName()));
        }

    }

    @Override
    public void init() {
        super.init();
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
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
