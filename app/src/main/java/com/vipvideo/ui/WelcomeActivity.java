package com.vipvideo.ui;

import com.lixh.base.LaunchActivity;
import com.vipvideo.R;


/**
 * Created by LIXH on 2017/2/buy_7.
 * email lixhVip9@163.com
 * des
 */

public class WelcomeActivity extends LaunchActivity {


    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_launch;
    }

    /**
     * 这里执行动画
     */
    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public Class toActivity(int what) {

        return what == GO_HOME ? TabsActivity.class : TabsActivity.class;
    }

}
