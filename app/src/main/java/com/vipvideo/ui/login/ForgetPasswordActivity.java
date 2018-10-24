package com.vipvideo.ui.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lixh.base.BaseActivity;
import com.lixh.utils.Alert;
import com.lixh.utils.CountdownUtil;
import com.lixh.view.LoadView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.LoginPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import carbon.widget.EditText;

/**
 * author: rsw
 * created on: 2018/10/21 下午1:40
 * description:找回密码
 */

public class ForgetPasswordActivity extends BaseActivity<LoginPresenter> {

    LoginPresenter loginPresenter;
    @Bind(R.id.edit_phone)
    EditText editPhone;
    @Bind(R.id.edit_verification_code)
    EditText editVerificationCode;
    @Bind(R.id.text_send_verification_code)
    TextView textSendVerificationCode;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.edit_password_confirm)
    EditText editPasswordConfirm;

    private String username;
    private String password;
    private String code;
    private String passwordConfirm;
    private String code_data;

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
        return R.layout.layout_forget_pwd;
    }

    @OnClick({R.id.text_send_verification_code, R.id.btn_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.text_send_verification_code:
                username = editPhone.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    Alert.displayAlertDialog(this, "请输入手机号");
                    return;
                }
                code_data=rndNum();
                Map<String, String> value = new HashMap<>();
                value.put("username",username);
                value.put("key",code_data);
                loginPresenter.validateCode(value);

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
                Map<String,String> query=new HashMap<>();
                query.put("username",username);
                query.put("password",password);
                query.put("key",code);

                loginPresenter.forgetPassword(query);
                break;
        }

    }

    /**
     * 登录成功
     */
    public void forgetSuccess() {
        finish();
    }

    /**
     * 发送短信
     */
    public void sendMobile() {
        CountdownUtil countdownUtil = new CountdownUtil(textSendVerificationCode, "%ss重新获取");
        countdownUtil.start();
    }

    public void validate(){
        Map<String, String> param = new HashMap<>();
        param.put("mobile", username);
        param.put("tpl_id", "#code#="+code_data);
        loginPresenter.sendMobileCode(param);
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

}