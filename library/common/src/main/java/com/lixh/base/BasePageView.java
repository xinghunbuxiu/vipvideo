package com.lixh.base;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;

import butterknife.ButterKnife;


/**
 * Created by LIXH on 2017/5/8.
 * email lixhVip9@163.com
 * des
 */

public abstract class BasePageView implements IPageView {
    public View baseView;
    public Activity activity;

    public <T> T getActivity() {
        return (T) activity;
    }

    public BasePageView(Activity activity) {
        this.activity = activity;
        initView();
        init();
    }

    @Override
    public void initView() {
        if (baseView == null) {
            baseView = View.inflate(activity, getLayoutId(), null);
            ButterKnife.bind(baseView);
        }
    }

    @Override
    public View getView() {
        return baseView;
    }

    protected <VT extends View> VT $(@IdRes int id) {
        return (VT) baseView.findViewById(id);
    }

}
