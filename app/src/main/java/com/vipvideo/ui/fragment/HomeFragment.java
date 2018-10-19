package com.vipvideo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.lixh.view.UToolBar;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems.Creator;
import com.vipvideo.presenter.HomePresenter;


public class HomeFragment extends SmartTabFragment<HomePresenter> {

    String title[] = {"电视剧", "电影", "动画", "动漫", "vip影视"};
    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setTitle("小爱");
        toolBar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected FragmentPagerItems getFragmentPagerItems() {
        Creator creator = FragmentPagerItems.with(getContext());
        Bundle bundle = new Bundle();
        for (int i = 0; i < title.length; i++) {
            bundle.putInt("type", i);
            creator.add(title[i], VipFragment.class, bundle);
        }
        return creator.create();
    }

}
