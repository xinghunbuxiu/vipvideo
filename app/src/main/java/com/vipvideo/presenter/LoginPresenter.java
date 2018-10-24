package com.vipvideo.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.lixh.utils.LocalAppInfo;
import com.lixh.utils.UToast;
import com.vipvideo.api.Api;
import com.vipvideo.api.ApiService;
import com.vipvideo.bean.UserBean;
import com.vipvideo.ui.login.ForgetPasswordActivity;
import com.vipvideo.ui.login.LoginActivity;
import com.vipvideo.ui.login.RegisterActivity;

import java.util.Map;


public class LoginPresenter extends BasePresenter {

    ApiService apiService = Api.getDefault();

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    /**
     * 登录
     */
    public void login(String username, String password, String imei) {
        rxHelper.createSubscriber(apiService.login(username, password, imei)

                , new RxSubscriber<UserBean>(activity, true) {
                    @Override
                    protected void _onNext(UserBean bean) {
                        if (getActivity() instanceof LoginActivity)
                            ((LoginActivity) getActivity()).loginSuccess(bean);
                        if (getActivity() instanceof RegisterActivity)
                            ((RegisterActivity) getActivity()).loginSuccess(bean);
                    }
                });
    }

    /**
     * 注册
     */
    public void register(String username, String password) {
        rxHelper.createSubscriber(apiService.register(username, password)
                , new RxSubscriber<String>(activity, true) {
                    @Override
                    protected void _onNext(String bean) {
                        UToast.showShort("注册成功,当前身份：体验会员");
                        login(username, password, LocalAppInfo.getLocalAppInfo().getIMei());
                    }
                });
    }

    /**
     * 找回密码或者修改密码
     */
    public void forgetPassword(Map<String, String> param) {
        rxHelper.createSubscriber(apiService.forgetPwd(param)
                , new RxSubscriber<String>(activity, true) {
                    @Override
                    protected void _onNext(String bean) {

                    }
                });
    }

    /**
     * 找回密码或者修改密码
     */
    public void sendMobileCode(Map<String, String> param) {
        rxHelper.createSubscriber(apiService.sendMobileCode(param)
                , new RxSubscriber<String>(activity, true) {
                    @Override
                    protected void _onNext(String bean) {
                        try {
                            if (TextUtils.isEmpty(bean))
                                return;
                            String error_code = JSON.parseObject(bean).getString("error_code");
                            //成功
                            if (error_code.equals("0")) {
                                UToast.showShort("验证码发送成功");
                                if (getActivity() instanceof RegisterActivity)
                                    ((RegisterActivity) getActivity()).sendMobile();
                            } else {
                                UToast.showShort("发送失败");
                            }
                        } catch (Exception e) {
                            UToast.showShort("发送失败");
                        }
                    }
                });
    }

    /**
     * 验证验证码
     *
     * @param param
     */
    public void validateCode(Map<String, String> param) {
        rxHelper.createSubscriber(apiService.validateCode(param)
                , new RxSubscriber<String>(activity, true) {
                    @Override
                    protected void _onNext(String bean) {
                        if (getActivity() instanceof ForgetPasswordActivity)
                            ((ForgetPasswordActivity) getActivity()).validate();
                    }
                });
    }


}
