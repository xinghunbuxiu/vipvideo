package com.vipvideo.presenter;

import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.vipvideo.ui.fragment.CategoryFragment;


/**
 * Created by LIXH on 2016/11/14.
 * email lixhVip9@163.com
 * des
 */
public class HomePresenter extends BasePresenter {
    CategoryFragment homeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        homeFragment = getFragment();
    }

}
