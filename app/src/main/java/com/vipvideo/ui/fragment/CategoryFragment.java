package com.vipvideo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.lixh.view.UToolBar;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems.Creator;
import com.vipvideo.presenter.HomePresenter;


public class CategoryFragment extends SmartTabFragment<HomePresenter> {

    String title[] = {"热播", "电影", "电视剧", "动漫", "综艺"};

    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setTitle("小爱");
        toolBar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected FragmentPagerItems getFragmentPagerItems() {
        Creator creator = FragmentPagerItems.with(getContext());
        creator.add(title[0], HomeFragment.class);
        Bundle bundle = new Bundle();
        for (int i = 1; i < title.length; i++) {
            bundle.putInt("type", i);
            creator.add(title[i], VipFragment.class, bundle);
        }
        return creator.create();
    }

}
