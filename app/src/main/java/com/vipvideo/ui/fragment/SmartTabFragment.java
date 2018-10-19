package com.vipvideo.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.lixh.base.BaseFragment;
import com.lixh.presenter.BasePresenter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.vipvideo.R;

import butterknife.Bind;

/**
 * Created by LIXH on 2018/10/18.
 * email lixhVip9@163.com
 * des
 */

public abstract class SmartTabFragment<T extends BasePresenter> extends BaseFragment {


    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    FragmentPagerItemAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_tabsmart;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        adapter = new FragmentPagerItemAdapter(activity.getSupportFragmentManager(), getFragmentPagerItems());
        viewpager.setAdapter(adapter);
        viewpagertab.setViewPager(viewpager);
    }

    protected abstract FragmentPagerItems getFragmentPagerItems();
}
