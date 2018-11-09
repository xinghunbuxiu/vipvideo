package com.vipvideo.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.vipvideo.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/3/22.
 */

public class ParseWebUrlHelper extends Dialog {
    private static ParseWebUrlHelper parseWebUrlHelper;
    private String webUrl;
    private WebView webView;
    private int timeOut = 20 * 1000;
    private OnParseWebUrlListener onParseListener;
    private WebView mWebView;
    ProgressBar mProgressBar;
    View rootView;

    public ParseWebUrlHelper(Context context) {
        super(context);
    }

    public ParseWebUrlHelper(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_web_progress);
        mWebView = findViewById(R.id.web_view);
        mProgressBar = findViewById(com.lixh.R.id.progress_bar);
        initWebSettings();
    }

    public ParseWebUrlHelper setWebView(WebView mWebView) {
        this.mWebView = mWebView;
        mWebView.clearFocus();
        initWebSettings();
        return this;
    }

    private void initWebSettings() {
        mWebView.clearFocus();
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebSettings.setPluginState(WebSettings.PluginState.ON);
        mWebSettings.setDisplayZoomControls(false);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAllowContentAccess(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setAllowContentAccess(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setBuiltInZoomControls(true);// 隐藏缩放按钮
        mWebSettings.setUseWideViewPort(true);// 可任意比例缩放
        mWebSettings.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        mWebSettings.setSavePassword(true);
        mWebSettings.setSaveFormData(true);// 保存表单数据
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setTextZoom(100);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setSupportMultipleWindows(true);// 新加//我就是没有这一行，死活不出来。MD，硬是没有人写这一句！
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mWebSettings.setMediaPlaybackRequiresUserGesture(true);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            mWebSettings.setAllowFileAccessFromFileURLs(true);
            mWebSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setGeolocationDatabasePath(getContext().getDir("database", 0).getPath());
        mWebSettings.setGeolocationEnabled(true);
        CookieManager instance = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance(getContext());
        }
        instance.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(mWebView, true);
        }
        mWebView.setWebViewClient(new MyWebViewClient());
        enabledCookie(webView);//启用cookie
        enabledCookie(mWebView);//启用cookie
    }

    public ParseWebUrlHelper setLoadUrl(String url) {
        this.webUrl = url;
        return this;
    }

    @Override
    public void show() {
        super.show();
        mWebView.loadUrl(webUrl);
    }

    /*启用cookie*/
    private void enabledCookie(WebView web) {
        CookieManager instance = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance(getContext());
        }
        instance.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            instance.setAcceptThirdPartyCookies(web, true);
        }
    }

    public ParseWebUrlHelper setOnParseListener(OnParseWebUrlListener onParseListener) {
        this.onParseListener = onParseListener;
        return this;
    }

    private class MyWebViewClient extends WebViewClient {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        /*解决ssl证书问题*/
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            Log.e("shouldInterceptRequest", url);
            return super.shouldInterceptRequest(view, url);
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Log.e("shouldInterceptRequest", request.getUrl().toString());
            }
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
            startConut();//加载超时处理
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }
    }

    /*解决webview加载超时问题*/
    private void startConut() {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                onParseListener.onError("解析视频超时，请检查网速或网络是否出现问题...");
                timer.cancel();
                timer.purge();
            }
        };
        timer.schedule(timerTask, timeOut, 1);
    }

    public interface OnParseWebUrlListener {
        void onFindUrl(String url);

        void onError(String errorMsg);
    }
}
