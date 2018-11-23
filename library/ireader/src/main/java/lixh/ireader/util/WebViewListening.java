package lixh.ireader.util;

import android.webkit.JavascriptInterface;

import com.lixh.utils.ULog;

public class WebViewListening {

    @JavascriptInterface
    public void log(String msg) {
        ULog.e("WebViewlog" + msg);
    }
}