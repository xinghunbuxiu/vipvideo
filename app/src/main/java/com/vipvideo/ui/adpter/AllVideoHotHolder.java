package com.vipvideo.ui.adpter;

import android.support.design.widget.TabLayout;
import android.view.View;

import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.bean.MovieTypeBean;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class AllVideoHotHolder extends VBaseHolder<MovieTypeBean> {
    @Bind(R.id.tab_view)
    TabLayout tabLayout;

    public AllVideoHotHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, MovieTypeBean bean) {
        super.setData(ps, bean);
        for (MovieTypeBean.CondsBean condsBean : bean.getOrders()) {
            tabLayout.addTab(tabLayout.newTab().setText(condsBean.getName()));
        }
    }

    @Override
    public void init() {
        super.init();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (mListener != null) {
                    mListener.onItemClick(tabLayout, tab.getPosition(), mData);
                }
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
