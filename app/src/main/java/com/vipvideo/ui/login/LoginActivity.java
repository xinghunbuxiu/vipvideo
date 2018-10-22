package com.vipvideo.ui.login;

import android.text.TextUtils;
import android.view.View;

import com.lixh.base.BaseActivity;
import com.lixh.utils.Alert;
import com.lixh.utils.LocalAppInfo;
import com.lixh.view.LoadView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.LoginPresenter;
import com.vipvideo.view.ClearEditText;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: rsw
 * created on: 2018/10/21 下午1:35
 * description: 登录页面
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {

    LoginPresenter loginPresenter;
    @Bind(R.id.edit_account)
    ClearEditText editAccount;
    @Bind(R.id.edit_password)
    ClearEditText editPassword;
    private String username;
    private String password;

    @Override
    public void initTitle(UToolBar toolBar) {
        loginPresenter = getPresenter();
    }

    @Override
    public void initLoad(LoadView.Builder builder) {
        builder.swipeBack = true;
        builder.hasToolbar = false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_login;
    }

    @OnClick({R.id.image_back, R.id.text_forget_password, R.id.btn_login,R.id.text_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.text_register:
                intent.go(RegisterActivity.class);
                break;
            case R.id.text_forget_password:
                intent.go(ForgetPasswordActivity.class);
                break;
            case R.id.btn_login:
                username = editAccount.getText().toString();
                password = editPassword.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Alert.displayAlertDialog(this, "请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Alert.displayAlertDialog(this, "请输入密码");
                    return;
                }
                loginPresenter.login(username, password, LocalAppInfo.getLocalAppInfo().getIMei());
                break;
        }
    }
}
