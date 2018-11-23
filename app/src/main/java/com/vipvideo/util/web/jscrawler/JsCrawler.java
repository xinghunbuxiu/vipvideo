package com.vipvideo.util.web.jscrawler;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.vipvideo.util.web.jsevaluator.JsEvaluator;
import com.vipvideo.util.web.jsevaluator.interfaces.JsCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by ykj on 17/6/30.
 */
public class JsCrawler {

    private static JsCrawler singleton;
    public static final String REQUEST_MODEL = "request-model.js";
    public static final String JQUERY = "jquery-3.2.1.min.js";
    private Context mContext;
    private JsEvaluator mJsEvaluator;
    private String jsLibCode = "";

    public static void initialize(Context context) {
        if (singleton == null) {
            synchronized (JsCrawler.class) {
                if (singleton == null) {
                    singleton = new JsCrawler(context);
                }
            }
        }
    }

    public static void release() {
        if (singleton != null) {
            synchronized (JsCrawler.class) {
                if (singleton != null) {
                    singleton.getJsEvaluator().destroy();
                    singleton = null;
                }
            }
        }
    }

    public static JsCrawler getInstance() {
        return singleton;
    }

    private JsCrawler(@NonNull Context context) {
        mContext = context;
        mJsEvaluator = new JsEvaluator(context);
        setRequestEngine(new JsoupEngine());
    }

    public JsEvaluator getJsEvaluator() {
        return mJsEvaluator;
    }

    public void setRequestEngine(RequestEngine requestEngine) {
        mJsEvaluator.getWebView().addJavascriptInterface(requestEngine, "RequestEngine");
    }

    public String loadJs(String name) {
        String jsCode;
        try {
            final AssetManager am = mContext.getAssets();
            final InputStream inputStream = am.open(name);
            jsCode = getFileString(inputStream);
            return jsCode;
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public JsCrawler init(String... names) {
        jsLibCode = "";
        for (String name : names) {
            jsLibCode += loadJs(name) + ";\u2000";
        }
        return this;
    }
    private String getFileString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.useDelimiter("\\A").next();
    }

    public void callFunction(String jsCode, JsCallback resultCallback) {
        jsCode = jsLibCode + ";\u2000" + jsCode;
        mJsEvaluator.callFunction(jsCode, resultCallback, "", new Object[]{});
    }
    public void callFunction(String jsCode, JsCallback resultCallback, String name, Object... args) {
        jsCode = jsLibCode + ";\u2000" + jsCode;
        mJsEvaluator.callFunction(jsCode, resultCallback, name, args);
    }


}
