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

public class AllVideoFilterHolder extends VBaseHolder<MovieTypeBean.CondsBean> {
    @Bind(R.id.tab_view)
    TabLayout tabLayout;

    public AllVideoFilterHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, MovieTypeBean.CondsBean condsBean) {
        super.setData(ps, condsBean);
        for (MovieTypeBean.CondsBean.ValuesBean bean : condsBean.getValues()) {
            tabLayout.addTab(tabLayout.newTab().setText(bean.getTitle()));
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
