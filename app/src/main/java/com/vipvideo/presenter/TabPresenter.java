package com.vipvideo.presenter;


import android.os.Bundle;
import android.text.TextUtils;

import com.jayway.jsonpath.JsonPath;
import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.lixh.utils.ULog;
import com.vipvideo.api.Api;
import com.vipvideo.api.HostType;
import com.vipvideo.ui.TabsActivity;
import com.vipvideo.util.web.mahua.AesUtil;
import com.vipvideo.util.web.mahua.MhSdk;

;


/**
 * Created by LIXH on 2016/12/21.
 * email lixhVip9@163.com
 * des
 */
public class TabPresenter extends BasePresenter {
    TabsActivity tabsActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        tabsActivity = getActivity();
        LoginMh();
    }

    public void LoginMh() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(MhSdk.init().getAppInfo().getDevice());
        stringBuilder.append(MhSdk.init().getAppInfo().getSystemName());
        String model = stringBuilder.toString();
        rxHelper.createSubscriber(Api.getDefault(HostType.M_MAHUA_URL).MhLogin(MhSdk.init().getAppInfo().getUuid(), model, ""), new RxSubscriber<String>(tabsActivity, false) {
            @Override
            protected void _onNext(String str) {
                try {
                    ULog.e("LoginMh==" + str);
                    String result = AesUtil.decryptHex(str, AesUtil.getKey(false));
                    ULog.e("LogindecryptHex==" + result);
                    String mToken = JsonPath.read(result, "$.data.detail.token");
                    ULog.e("LoginmToken==" + result);
                    MhSdk.init().getAppInfo().setXAuthToken(mToken);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
