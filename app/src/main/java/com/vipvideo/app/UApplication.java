package com.vipvideo.app;

import com.lixh.BuildConfig;
import com.lixh.app.BaseApplication;
import com.lixh.utils.ULog;
import com.vipvideo.util.web.jscrawler.JsCrawler;
import com.vipvideo.util.web.mahua.MhSdk;

/**
 * APPLICATION
 */
public class UApplication extends BaseApplication {

    public static UApplication instance;

    @Override
    public void init() {
        instance = this;
        JsCrawler.initialize(this);
        MhSdk.init();
        ULog.logInit(BuildConfig.LOG_DEBUG);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        JsCrawler.release();
    }
}
