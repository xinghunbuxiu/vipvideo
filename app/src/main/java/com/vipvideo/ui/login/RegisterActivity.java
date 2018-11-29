package com.vipvideo.ui.login;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.lixh.app.AppManager;
import com.lixh.base.BaseActivity;
import com.lixh.utils.Alert;
import com.lixh.utils.CountdownUtil;
import com.lixh.view.LoadView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.bean.UserBean;
import com.vipvideo.presenter.LoginPresenter;
import com.vipvideo.ui.TabsActivity;
import com.vipvideo.util.UserInfoUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import carbon.widget.Button;
import carbon.widget.EditText;

/**
 * author: rsw
 * created on: 2018/10/21 下午1:40
 * description:注册
 */

public class RegisterActivity extends BaseActivity<LoginPresenter> implements TextWatcher {

    LoginPresenter loginPresenter;
    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.layout_phone)
    ConstraintLayout layoutPhone;
    @Bind(R.id.edit_verification_code)
    EditText editVerificationCode;
    @Bind(R.id.text_send_verification_code)
    TextView textSendVerificationCode;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.edit_password_confirm)
    EditText editPasswordConfirm;
    @Bind(R.id.btn_register)
    Button btnRegister;

    private String username;
    private String password;
    private String code;
    private String passwordConfirm;
    private String creatCode;


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
        editPhone.addTextChangedListener(this);
        editVerificationCode.addTextChangedListener(this);
        editPassword.addTextChangedListener(this);
        editPasswordConfirm.addTextChangedListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_register;
    }

    @OnClick({R.id.image_back, R.id.text_send_verification_code, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.text_send_verification_code:
                username = editPhone.getText().toString();
                creatCode = rndNum();
                if (!TextUtils.isEmpty(username)) {
                    Alert.displayAlertDialog(this, "请输入手机号");
                    return;
                }
                Map<String, String> param = new HashMap<>();
                param.put("mobile", "");
                param.put("tpl_id", creatCode);
                loginPresenter.sendMobileCode(param);
                break;
            case R.id.btn_register:
                username = editPhone.getText().toString();
                password = editPassword.getText().toString();
                passwordConfirm = editPasswordConfirm.getText().toString();
                code = editVerificationCode.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    Alert.displayAlertDialog(this, "请输入手机号");
                    return;
                }
                if (!TextUtils.isEmpty(password)) {
                    Alert.displayAlertDialog(this, "请输入密码");
                    return;
                }
                if (!TextUtils.isEmpty(code)) {
                    Alert.displayAlertDialog(this, "请输入手机验证码");
                    return;
                }
                if (password.length() < 6) {
                    Alert.displayAlertDialog(this, "请输入6位以上密码");
                    return;
                }
                if (!password.equals(passwordConfirm)) {
                    Alert.displayAlertDialog(this, "两次输入的密码不一致");
                    return;
                }
                if (!code.equals(creatCode)) {
                    Alert.displayAlertDialog(this, "验证码不正确");
                    return;
                }
                loginPresenter.register(username, password);
                break;
        }

    }

    /**
     * 登录成功
     */
    public void loginSuccess(UserBean userInfoBean) {
        userInfoBean.setUsername(username);
        userInfoBean.setPassword(password);
        UserInfoUtils.saveUserInfo(userInfoBean);
        AppManager.getAppManager().returnToActivity(TabsActivity.class);
        finish();
    }

    /**
     * 发送短信
     */
    public void sendMobile() {
        CountdownUtil countdownUtil = new CountdownUtil(textSendVerificationCode, "%ss重新获取");
        countdownUtil.start();
    }

    /**
     * 生成验证码
     */
    public String rndNum() {
        StringBuilder rnd = new StringBuilder();
        for (int i = 0; i < 6; i++)
            rnd.append(Math.floor(Math.random() * 10));
        return rnd.toString();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        username = editPhone.getText().toString();
        password = editPassword.getText().toString();
        passwordConfirm = editPasswordConfirm.getText().toString();
        code = editVerificationCode.getText().toString();
        btnRegister.setEnabled(TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)&&TextUtils.isEmpty(passwordConfirm)&&TextUtils.isEmpty(code));

    }
}