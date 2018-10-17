package com.vipvideo.presenter;


import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.vipvideo.ui.TabsActivity;

;


/**
 * Created by LIXH on 2016/12/21.
 * email lixhVip9@163.com
 * des
 */
public class TabPresenter extends BasePresenter {
    TabsActivity tabsActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        tabsActivity = getActivity();

    }
}
