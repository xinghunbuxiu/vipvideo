package lixh.ireader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by mahavir on 3/31/16.
 */
public class ObservableWebView extends WebView {

    WebSettings settings;

    public ObservableWebView(Context context) {
        this(context, null);
    }

    public ObservableWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ObservableWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        getViewTreeObserver().
                addOnGlobalLayoutListener(() -> {
                    int height =
                            (int) Math.floor(getContentHeight() * getScale());
                    int webViewHeight = getMeasuredHeight();
                });
        settings = getSettings();
        settings.setJavaScriptEnabled(true);
        setVerticalScrollBarEnabled(false);
        settings.setAllowFileAccess(true);
        setHorizontalScrollBarEnabled(false);
        settings.setDefaultTextEncodingName("utf-8");

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
