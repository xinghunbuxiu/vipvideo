package lixh.ireader.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by mahavir on 3/31/16.
 */
public class ObservableWebView extends WebView {

    PageCallback pageCallback;

    public interface PageCallback {
        String getChapterHtmlContent(int position);

        void hideOrshowToolBar();

        void hideToolBarIfVisible();

        void setPagerToPosition(String href);

        void setLastWebViewPosition(int position);
    }

    public PageCallback getPageCallback() {
        return pageCallback;
    }

    public void setPageCallback(ObservableWebView.PageCallback pageCallback) {
        this.pageCallback = pageCallback;
    }

    public static interface ScrollListener {
        public void onScrollChange(int percent);
    }

    private ScrollListener mScrollListener;

    public ObservableWebView(Context context) {
        super(context);
    }

    public ObservableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ObservableWebView(Context context, AttributeSet attrs,
                             int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollListener(ScrollListener listener) {
        mScrollListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (pageCallback != null) {
            pageCallback.hideToolBarIfVisible();
        }
        if (mScrollListener != null) mScrollListener.onScrollChange(t);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public int getContentHeightVal() {
        int height = (int) Math.floor(this.getContentHeight() * this.getScale());
        return height;
    }

    public int getWebViewHeight() {
        return this.getMeasuredHeight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final boolean[] mMoveOccured = new boolean[1];
        final float[] mDownPosX = new float[1];
        final float[] mDownPosY = new float[1];
        final float MOVE_THRESHOLD_DP = 20 * getResources().getDisplayMetrics().density;
        final int action = event.getAction();
        int positionScroll = 0;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mMoveOccured[0] = false;
                mDownPosX[0] = event.getX();
                mDownPosY[0] = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (!mMoveOccured[0]) {
                    if (pageCallback != null)
                        pageCallback.hideOrshowToolBar();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(event.getX() - mDownPosX[0]) > MOVE_THRESHOLD_DP
                        || Math.abs(event.getY() - mDownPosY[0]) > MOVE_THRESHOLD_DP) {
                    mMoveOccured[0] = true;
                }
                break;
        }

        return super.dispatchTouchEvent(event);
    }
}
