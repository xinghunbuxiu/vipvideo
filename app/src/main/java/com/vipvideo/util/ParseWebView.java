package com.vipvideo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.vipvideo.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/3/22.
 */

public class ParseWebView extends FrameLayout {
    private WebView mWebView;
    ProgressBar mProgressBar;
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36";
    public String mideaReg = ".*(.+\\.m3u8|.+\\.mp4|.+\\.flv).*";

    OnParseWebUrlListener parseWebUrlListener;
    InterceptRequest interceptRequest;

    String finalUrl;
    boolean isFinish;
    public interface InterceptRequest {
        WebResourceResponse shouldInterceptRequest(WebView view, String url);

    }

    public interface OnParseWebUrlListener {
        void onFindUrl(String finalUrl);

        void onError(String msg);
    }

    public void setOnParseListener(OnParseWebUrlListener parseListener) {
        this.parseWebUrlListener = parseListener;

    }

    public ParseWebView(Context context) {
        this (context, null);
    }

    public ParseWebView(Context context, AttributeSet attrs) {
        this (context, attrs, 0);
    }

    public ParseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super (context, attrs, defStyleAttr);
        initView (context);
    }

    private void initView(Context context) {
        if (!isInEditMode ( )) {
            View view = View.inflate (context, R.layout.layout_web_progress, null);
            mWebView = view.findViewById (R.id.web_view);
            mProgressBar = view.findViewById (R.id.progress_bar);
            addView (view);
            initWebSettings ( );

        }
    }

    private void initWebSettings() {
        mWebView.clearFocus ( );
        mWebView.setVisibility (INVISIBLE);
        WebSettings mWebSettings = mWebView.getSettings ( );
        mWebSettings.setJavaScriptEnabled (true);
        mWebSettings.setDefaultTextEncodingName ("utf-8");
        mWebSettings.setCacheMode (WebSettings.LOAD_DEFAULT);
        mWebSettings.setPluginState (WebSettings.PluginState.ON);
        mWebSettings.setDisplayZoomControls (false);
        mWebSettings.setUseWideViewPort (true);
        mWebSettings.setAllowFileAccess (true);
        mWebSettings.setAllowContentAccess (true);
        mWebSettings.setSupportZoom (true);
        mWebSettings.setAllowContentAccess (true);
        mWebSettings.setLoadWithOverviewMode (true);
        mWebSettings.setBuiltInZoomControls (true);// 隐藏缩放按钮
        mWebSettings.setUseWideViewPort (true);// 可任意比例缩放
        mWebSettings.setLoadWithOverviewMode (true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        mWebSettings.setSavePassword (true);
        mWebSettings.setSaveFormData (true);// 保存表单数据
        mWebSettings.setJavaScriptEnabled (true);
        mWebSettings.setTextZoom (100);

        mWebSettings.setDomStorageEnabled (true);
        mWebSettings.setSupportMultipleWindows (true);// 新加//我就是没有这一行，死活不出来。MD，硬是没有人写这一句！
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode (WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mWebSettings.setMediaPlaybackRequiresUserGesture (true);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            mWebSettings.setAllowFileAccessFromFileURLs (true);
            mWebSettings.setAllowUniversalAccessFromFileURLs (true);
        }
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically (true);
        mWebSettings.setAppCacheEnabled (false);
        mWebSettings.setDatabaseEnabled (true);
        mWebSettings.setGeolocationDatabasePath (getContext ( ).getDir ("database", 0).getPath ( ));
        mWebSettings.setGeolocationEnabled (false);
        enabledCookie (mWebView);//启用cookie
        mWebView.setWebViewClient (new MyWebViewClient ( ));
        mWebView.setWebChromeClient(new WebChromeClient());


    }


    public void loadUrl(String url) {
        mWebView.loadUrl (url);
    }

    /*启用cookie*/
    private void enabledCookie(WebView web) {
        CookieManager instance = CookieManager.getInstance ( );
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance (getContext ( ));
        }
        instance.setAcceptCookie (true);
        if (Build.VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies (web, true);
        }
    }


    private class MyWebViewClient extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("intent") || url.startsWith("youku")) {
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }

        }

        /*解决ssl证书问题*/
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            Log.e("shouldInterceptRequest", url);

            if (Pattern.matches(mideaReg, url)) {
                finalUrl = url;
                isFinish = true;
                // 获取页面内容
                if (parseWebUrlListener != null) {
                    Log.e("onProgressChanged", finalUrl + "");
                    parseWebUrlListener.onFindUrl(finalUrl);
                }
            }
            if (interceptRequest != null) {
                return interceptRequest.shouldInterceptRequest(view, url);
            }
            return super.shouldInterceptRequest(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            startCount();//加载超时处理
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e("onPageFinished", finalUrl + "");
        }
    }

    /*解决webview加载超时问题*/

    private void startCount() {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                timer.purge();
                if (parseWebUrlListener != null && !isFinish) {
                    parseWebUrlListener.onError(null);
                }

            }
        };
        timer.schedule(timerTask, 20 * 1000, 1);
    }

    public void onDestroy() {
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.destroy();
            mWebView.clearHistory();

        }
    }
    public void setInterceptRequest(ParseWebView.InterceptRequest interceptRequest) {
        this.interceptRequest = interceptRequest;
    }
}
