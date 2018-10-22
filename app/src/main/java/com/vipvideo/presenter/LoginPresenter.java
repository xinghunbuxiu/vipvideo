package com.vipvideo.presenter;

import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.vipvideo.api.Api;
import com.vipvideo.api.ApiService;
import com.vipvideo.bean.UserInfoBean;

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
                , new RxSubscriber<UserInfoBean>(activity, true) {
                    @Override
                    protected void _onNext(UserInfoBean bean) {

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

                    }
                });
    }


}
