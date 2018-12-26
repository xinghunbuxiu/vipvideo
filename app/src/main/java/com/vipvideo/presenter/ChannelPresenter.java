package com.vipvideo.presenter;

import android.os.Bundle;

import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.vipvideo.api.Api;
import com.vipvideo.api.HostType;
import com.vipvideo.ui.ChannelActivity;

/**
 * Created by LIXH on 2018/11/16.
 * email lixhVip9@163.com
 * des
 */

public class ChannelPresenter extends BasePresenter {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    }


    public void loadPageInfo(String searchName) {
        ChannelActivity activity = getActivity();
        rxHelper.createSubscriber(Api.getDefault(HostType.M_MAHUA_URL).searchVideoInfo( 1, 10, searchName, 1), new RxSubscriber<String>(activity, false) {
            @Override
            protected void _onNext(String result) {

            }
        });
    }


}
