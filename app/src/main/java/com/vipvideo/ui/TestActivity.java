package com.vipvideo.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lixh.base.BaseActivity;
import com.lixh.view.LoadView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.TestPresenter;

/**
 * Created by LIXH on 2016/12/21.
 * email lixhVip9@163.com
 * des
 */
public class TestActivity extends BaseActivity<TestPresenter> {


    @Override
    public boolean isShowBack() {
        return false;
    }


    @Override
    public int getLayoutId() {
        return R.layout.home_first_ad;
    }

    @Override
    public void initTitle(UToolBar toolBar) {

    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
       ImageView img= findViewById(R.id.iv_image_ad);
       ImageView imgs= (ImageView) findViewById(R.id.iv_image_ad);


        TestPresenter presenter=mPresenter.getPresenter();
        findViewById(R.id.iv_image_ad).setOnClickListener((View v) -> {
            presenter.loadPageInfo();
        });
    }

    @Override
    public void initLoad(LoadView.Builder builder) {

    }

}
