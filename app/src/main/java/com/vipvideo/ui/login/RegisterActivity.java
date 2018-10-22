package com.vipvideo.ui.login;

import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;

import com.lixh.base.BaseActivity;
import com.lixh.utils.Alert;
import com.lixh.view.LoadView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.LoginPresenter;
import com.vipvideo.view.ClearEditText;

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

public class RegisterActivity extends BaseActivity<LoginPresenter> {

    LoginPresenter loginPresenter;
    @Bind(R.id.edit_phone)
    ClearEditText editPhone;
    @Bind(R.id.layout_phone)
    ConstraintLayout layoutPhone;
    @Bind(R.id.edit_verification_code)
    EditText editVerificationCode;
    @Bind(R.id.edit_password)
    ClearEditText editPassword;
    @Bind(R.id.edit_password_confirm)
    ClearEditText editPasswordConfirm;
    @Bind(R.id.btn_register)
    Button btnRegister;

    private String username;
    private String password;
    private String code;
    private String passwordConfirm;

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
                if(!TextUtils.isEmpty(username)){
                    Alert.displayAlertDialog(this,"请输入手机号");
                    return;
                }
                Map<String,String> param=new HashMap<>();
                param.put("mobile","");
                param.put("tpl_id","107511");
                loginPresenter.sendMobileCode(param);
                break;
            case R.id.btn_register:

                password = editPassword.getText().toString();
                passwordConfirm = editPasswordConfirm.getText().toString();
                code = editVerificationCode.getText().toString();


                break;
        }
    }
}