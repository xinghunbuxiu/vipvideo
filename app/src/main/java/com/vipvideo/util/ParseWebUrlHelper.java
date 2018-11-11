package com.vipvideo.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.vipvideo.R;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/3/22.
 */

public class ParseWebUrlHelper extends Dialog {
    private WebView mWebView;
    ProgressBar mProgressBar;
    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36";
    public String mideaReg = ".*(.+\\.m3u8|.+\\.mp4|.+\\.flv).*";

    OnParseWebUrlListener parseWebUrlListener;
    InterceptRequest interceptRequest;
    private int timeOut = 20 * 1000;

    String finalUrl;

    public interface InterceptRequest {
        WebResourceResponse shouldInterceptRequest(WebView view, String url);

    }

    public interface OnParseWebUrlListener {
        void onFindUrl(String finalUrl);
    }

    public void setOnParseListener(OnParseWebUrlListener parseListener) {
        this.parseWebUrlListener = parseListener;

    }

    public ParseWebUrlHelper(Context context) {
        super (context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.layout_web_progress);
        mWebView = findViewById (R.id.web_view);
        mProgressBar = findViewById (R.id.progress_bar);
        setCanceledOnTouchOutside (false);
        initWebSettings ( );
    }

    private void initWebSettings() {
        mWebView.clearFocus ( );
        mWebView.addJavascriptInterface (new InJavaScriptLocalObj ( ), "local_obj");
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
        mWebSettings.setLoadsImagesAutomatically (true);
        mWebSettings.setAppCacheEnabled (true);
        mWebSettings.setAppCachePath (getContext ( ).getCacheDir ( ).getAbsolutePath ( ));
        mWebSettings.setDatabaseEnabled (true);
        mWebSettings.setGeolocationDatabasePath (getContext ( ).getDir ("database", 0).getPath ( ));
        mWebSettings.setGeolocationEnabled (true);
        enabledCookie (mWebView);//启用cookie
        mWebView.setWebViewClient (new MyWebViewClient ( ));
        mWebView.setWebChromeClient (new WebChromeClient ( ));


    }

    public final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String src) {
            Log.e ("ddddddd", src);
        }
    }

    public void loadUrl(String url) {
        super.show ( );
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
            if (url.startsWith ("intent") || url.startsWith ("youku")) {
                return true;
            } else {
                return super.shouldOverrideUrlLoading (view, url);
            }

        }

        /*解决ssl证书问题*/
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed ( );
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            Log.e ("shouldInterceptRequest", url);

            if (Pattern.matches (mideaReg, url)) {
                finalUrl = url;
            }
            if (interceptRequest != null) {
                return interceptRequest.shouldInterceptRequest (view, url);
            }
            return super.shouldInterceptRequest (view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setVisibility (View.VISIBLE);
            super.onPageStarted (view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished (view, url);
            // 获取页面内容
            view.loadUrl ("javascript:window.local_obj.showSource('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            mProgressBar.setVisibility (View.GONE);
//            dismiss ( );
//            if (parseWebUrlListener != null) {
//                Log.e ("onPageFinished", finalUrl+"");
//                mWebView.destroy ();
//                parseWebUrlListener.onFindUrl (finalUrl);
//            }

        }
    }


    public void setInterceptRequest(ParseWebUrlHelper.InterceptRequest interceptRequest) {
        this.interceptRequest = interceptRequest;
    }

}
