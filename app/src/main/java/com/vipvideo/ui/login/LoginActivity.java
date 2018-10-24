package com.vipvideo.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lixh.base.BaseActivity;
import com.lixh.utils.Alert;
import com.lixh.utils.LocalAppInfo;
import com.lixh.utils.UToast;
import com.lixh.view.LoadView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.bean.UserBean;
import com.vipvideo.presenter.LoginPresenter;
import com.vipvideo.util.UserInfoUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author: rsw
 * created on: 2018/10/21 下午1:35
 * description: 登录页面
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements TextWatcher {

    LoginPresenter loginPresenter;
    @Bind(R.id.edit_account)
    EditText editAccount;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
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
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        editAccount.addTextChangedListener(this);
        editPassword.addTextChangedListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_login;
    }

    @OnClick({R.id.image_back, R.id.text_forget_password, R.id.btn_login, R.id.text_register})
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
                if (password.length() < 6) {
                    Alert.displayAlertDialog(this, "请输入6位以上密码");
                    return;
                }
                loginPresenter.login(username, password, LocalAppInfo.getLocalAppInfo().getIMei());
                break;
        }
    }

    /**
     * 登录成功
     */
    public void loginSuccess(UserBean userInfoBean) {
        UserInfoUtils.saveUserInfo(userInfoBean);
        UToast.showShort("登录成功");
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        btnLogin.setEnabled(!TextUtils.isEmpty(editAccount.getText().toString()) && !TextUtils.isEmpty(editPassword.getText().toString()));
    }
}
