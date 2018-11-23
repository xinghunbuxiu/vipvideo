package com.vipvideo.presenter;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.lixh.presenter.BasePresenter;
import com.lixh.rxhttp.RxSubscriber;
import com.lixh.utils.ULog;
import com.vipvideo.api.Api;
import com.vipvideo.api.HostType;
import com.vipvideo.ui.reader.ReadActivityNew;
import com.vipvideo.ui.reader.duokanBook.CategoryInfo;
import com.vipvideo.ui.reader.fragment.FolioPageFragment;
import com.vipvideo.util.web.jscrawler.JsCrawler;
import com.vipvideo.util.web.jsevaluator.interfaces.JsCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LIXH on 2018/11/16.
 * email lixhVip9@163.com
 * des
 */

public class ReadPresenter extends BasePresenter {
    JsCrawler jsCrawler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        jsCrawler = JsCrawler.getInstance();
    }

    public void loadCategory(String mBookId) {
        ReadActivityNew readActivity = getActivity();

        rxHelper.createSubscriber(Api.getDefault(HostType.BASE_DUOKAN_URL).getChapterInfoPackage(mBookId), new RxSubscriber<String>(readActivity, true) {
            @Override
            protected void _onNext(String result) {
                ULog.e("onResult1: " + result);
                String finalJs = jsCrawler.loadJs("parse.js");
                jsCrawler.init("duoduo.js").callFunction(finalJs, new JsCallback() {

                    @Override
                    public void onResult(String result) {
                        ULog.e("onResult: " + result);
                        CategoryInfo categoryInfo = JSON.parseObject(result, CategoryInfo.class);
                        readActivity.setCategoryInfo(categoryInfo);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        ULog.e("onError: " + errorMessage);
                    }
                }, "getduoduo", result);
            }
        });

    }

    public void loadPageInfo(String mBookId, String page) {
        FolioPageFragment fragment = getFragment();
        rxHelper.createSubscriber(Api.getDefault(HostType.BASE_DUOKAN_INFO_URL).getChapterInfo(mBookId, page), new RxSubscriber<String>(fragment.getActivity(), true) {
            @Override
            protected void _onNext(String result) {
                ULog.e("onResult1: " + result);
                Pattern p = Pattern.compile("('.*?')");
                Matcher m = p.matcher(result);
                if (m.find()) {
                    String finalResult = m.group();
                    finalResult = finalResult.replaceAll("'|'", "");
                    fragment.onDrawPage(finalResult);
                }
            }
        });

    }
}
