package com.lixh.presenter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lixh.base.BaseActivity;
import com.lixh.base.BaseFragment;
import com.lixh.rxhttp.RxHelper;
import com.lixh.rxlife.LifeEvent;
import com.lixh.utils.LoadingTip;
import com.lixh.utils.UIntent;
import com.lixh.view.ILayout;
import com.lixh.view.UToolBar;

import io.reactivex.subjects.BehaviorSubject;


/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
public abstract class BasePresenter {
    public UToolBar toolbar;
    public LoadingTip tip;
    public UIntent intent;
    public RxHelper rxHelper;
    public FragmentActivity activity;
    private BaseFragment fragment;
    public ILayout view;

    public abstract void onCreate(Bundle savedInstanceState);

    /**
     * 对外部开放 以实现外面调用
     *
     * @param <T>
     * @return
     */
    public <T> T getPresenter() {
        return (T) this;
    }

    /**
     * 获取Activity 实例
     *
     * @param <T>
     * @return
     */
    public <T extends BaseActivity> T getActivity() {
        return (T) ((BaseActivity) activity).getActivity();
    }

    /**
     * 获取fragment 实例
     *
     * @param <T>
     * @return
     */
    public <T> T getFragment() {
        return (T) fragment.getFragment();
    }

    public BasePresenter bind(ILayout layout) {
        this.view = layout;
        return this;
    }

    public void init(Bundle savedInstanceState, BehaviorSubject<LifeEvent> lifecycleSubject) {
        if (view instanceof BaseActivity) {
            activity = ((BaseActivity) view);
            tip = ((BaseActivity) view).tip;
        } else {
            this.fragment = ((BaseFragment) view);
            tip = ((BaseFragment) view).tip;
            activity = fragment.getActivity();
        }
        intent = new UIntent(activity);
        rxHelper = RxHelper.build(activity).setCaChe(view.getClass().getName()).bindLifeCycle(lifecycleSubject);
        onCreate(savedInstanceState);
    }

    public static enum Theme {
        DAY, NIGHT
    }

    /**
     * 设置主题
     */
    private void initTheme(Theme theme) {

    }


    public void setToolBar(UToolBar toolbar) {
        this.toolbar = toolbar;
    }

    public void onDestroy() {
        rxHelper.clearSubject();
    }


}
