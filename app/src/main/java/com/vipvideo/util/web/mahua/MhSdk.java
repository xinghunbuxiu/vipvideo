package com.vipvideo.util.web.mahua;

import android.app.Application;

import com.alibaba.fastjson.JSON;
import com.lixh.app.BaseApplication;

import java.io.InputStream;

/**
 * Created by LIXH on 2018/12/26.
 * email lixhVip9@163.com
 * des
 */

public class MhSdk {
    static MhSdk sdk;
    Application application;
    AppInfo appInfo;

    public MhSdk(Application application) {
        this.application = application;
    }

    public static MhSdk init() {
        if (sdk == null) {
            sdk = new MhSdk(BaseApplication.getAppContext());
            sdk.initAppInfo();
            try {
                sdk.initPackInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sdk;
    }

    private void initAppInfo() {
        appInfo = new AppInfo(application);
    }

    public AppInfo getAppInfo() {
        return this.appInfo;
    }

    private void initPackInfo() throws Exception {
        InputStream open = application.getAssets().open("pack_info");
        byte[] bArr = new byte[open.available()];
        open.read(bArr);
        open.close();
        String str = new String(bArr, "utf8");
        String decryptHex = AesUtil.decryptHex(str, AesUtil.getKey(true));
        if (decryptHex != null) {
            str = decryptHex;
        }
        PackInfo packInfo = JSON.parseObject(str, PackInfo.class);
        RHelp.initInfo(packInfo);
    }

}
