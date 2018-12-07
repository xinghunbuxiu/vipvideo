package com.vipvideo.presenter;

import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.vipvideo.api.Api;
import com.vipvideo.api.HostType;
import com.vipvideo.model.HomeModel;
import com.vipvideo.ui.TestActivity;
import com.vipvideo.util.UJsonp;

/**
 * Created by LIXH on 2018/11/16.
 * email lixhVip9@163.com
 * des
 */

public class TestPresenter extends BasePresenter {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }


    public void loadPageInfo() {
        TestActivity activity = getActivity();
        rxHelper.createSubscriber(Api.getDefault(HostType.M_BAIDU_URL).getMainInfo(), new RxSubscriber<String>(activity, false) {
            @Override
            protected void _onNext(String result) {
                HomeModel model = UJsonp.getInstance().jiexi(result);
            }
        });
    }
}
