package com.vipvideo.app;

import com.lixh.BuildConfig;
import com.lixh.app.BaseApplication;
import com.lixh.utils.ULog;
import com.vipvideo.util.web.jscrawler.JsCrawler;

/**
 * APPLICATION
 */
public class UApplication extends BaseApplication {

    public static UApplication instance;

    @Override
    public void init() {
        instance = this;
        JsCrawler.initialize(this);
        // 获取JsCrawler实例
        JsCrawler jsCrawler = JsCrawler.getInstance();
        // 设置是否开启使用JQuery
        jsCrawler.setJQueryEnabled(true);
        ULog.logInit(BuildConfig.LOG_DEBUG);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        JsCrawler.release();
    }
}
