package com.vipvideo.ui;


import android.os.Bundle;

import com.lixh.base.BaseActivity;
import com.lixh.view.UToolBar;
import com.vipvideo.presenter.ChannelPresenter;

/**
 * Created by LIXH on 2016/12/21.
 * email lixhVip9@163.com
 * des
 */
public class ChannelActivity extends BaseActivity<ChannelPresenter> {


    @Override
    public boolean isShowBack() {
        return false;
    }


    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initTitle(UToolBar toolBar) {

    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }


}
