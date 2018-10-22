package com.vipvideo.ui.login;

import com.lixh.base.BaseActivity;
import com.lixh.view.LoadView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.LoginPresenter;

/**
 * author: rsw
 * created on: 2018/10/21 下午1:40
 * description:找回密码
 */

public class ForgetPasswordActivity extends BaseActivity<LoginPresenter> {

    LoginPresenter loginPresenter;

    @Override
    public void initTitle(UToolBar toolBar) {
        loginPresenter = getPresenter();
        toolBar.setTitle("找回密码");
    }

    @Override
    public void initLoad(LoadView.Builder builder) {
        builder.swipeBack = true;
        builder.hasToolbar = true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_register;
    }

}