package com.vipvideo.ui.reader.fragment;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lixh.base.BaseFragment;
import com.lixh.utils.LoadingTip;
import com.lixh.utils.ULog;
import com.lixh.view.LoadView.Builder;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.ReadPresenter;

import butterknife.Bind;
import lixh.ireader.bean.BookChapterBean;
import lixh.ireader.bean.WebViewPosition;
import lixh.ireader.util.Highlight;
import lixh.ireader.util.WebViewListening;
import lixh.ireader.widget.ObservableWebView;
import lixh.ireader.widget.controller.TextSelectionSupport;

import static lixh.ireader.api.Constant.BOOK_ID;
import static lixh.ireader.api.Constant.BOOK_PAGE_INDEX;


/**
 * Created by mahavir on 4/2/16.
 */
public class FolioPageFragment extends BaseFragment<ReadPresenter> {

    public static final String TAG = FolioPageFragment.class.getSimpleName();
    @Bind(R.id.contentWebView)
    ObservableWebView contentWebView;
    private WebViewPosition contentWebViewposition;
    private String mBookTitle;
    private String mBookId;
    private String mHtmlContent;
    private TextSelectionSupport mTextSelectionSupport;
    private int mScrollY;
    private String mSelectedText;
    private int mChapterPosition = 1;

    public static FolioPageFragment newInstance(String bookId, int pageIndex, BookChapterBean bookChapterBean) {
        FolioPageFragment fragment = new FolioPageFragment();
        Bundle args = new Bundle();
        args.putString(BOOK_ID, bookId);
        args.putInt(BOOK_PAGE_INDEX, pageIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initLoad(Builder builder) {
        builder.hasToolbar = false;
    }

    @Override
    public void initTitle(UToolBar toolBar) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_read_page;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        if ((intent.getBundle() != null)) {
            mBookId = intent.getString(BOOK_ID);
            mChapterPosition = intent.getInt(BOOK_PAGE_INDEX, 1);
        }
        initWebView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        float positionTopView = contentWebView.getTop();
        float contentHeight = contentWebView.getContentHeight();
        float currentScrollPosition = mScrollY;
        float percentWebview = (currentScrollPosition - positionTopView) / contentHeight;
        float webviewsize = contentWebView.getContentHeight() - contentWebView.getTop();
        float positionInWV = webviewsize * percentWebview;
        int positionY = Math.round(contentWebView.getTop() + positionInWV);
        mScrollY = positionY;
    }


    private void initWebView() {

        contentWebView.getViewTreeObserver().
                addOnGlobalLayoutListener(() -> {
                    int height =
                            (int) Math.floor(contentWebView.getContentHeight() * contentWebView.getScale());
                    int webViewHeight = contentWebView.getMeasuredHeight();
                });
        contentWebView.getSettings().setJavaScriptEnabled(true);
        contentWebView.setVerticalScrollBarEnabled(false);
        contentWebView.getSettings().setAllowFileAccess(true);
        contentWebView.setHorizontalScrollBarEnabled(false);
        contentWebView.addJavascriptInterface(new WebViewListening(), "controller");
        contentWebView.setScrollListener(new ObservableWebView.ScrollListener() {
            @Override
            public void onScrollChange(int percent) {
            }
        });
        mTextSelectionSupport = TextSelectionSupport.support(getActivity(), contentWebView);
        mTextSelectionSupport.setSelectionListener(new TextSelectionSupport.SelectionListener() {
            @Override
            public void startSelection() {
            }

            @Override
            public void selectionChanged(String text) {
                mSelectedText = text;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        contentWebView.loadUrl("javascript:alert(getRectForSelectedText())");
                    }
                });
            }

            @Override
            public void endSelection() {

            }
        });
        contentWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (TextUtils.isEmpty(mBookId)) {
                    layout.getEmptyView().setLoadingTip(LoadingTip.LoadStatus.SERVER_ERROR);
                    return;
                }
                mPresenter.loadPageInfo(mBookId, String.valueOf(mChapterPosition));
            }
        });
        contentWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (view.getProgress() == 100) {
                    contentWebView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("scroll y", "Scrolly" + mScrollY);
                            contentWebView.scrollTo(0, mScrollY);
                        }
                    }, 100);
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return true;
            }
        });
        contentWebView.getSettings().setDefaultTextEncodingName("utf-8");
        contentWebView.loadUrl("file:///android_asset/duoduo.html");
    }


    public String getSelectedText() {
        return mSelectedText;
    }

    public void highlight(Highlight.HighlightStyle style, boolean isCreated) {
        if (isCreated) {
            contentWebView.loadUrl(String.format(getString(R.string.getHighlightString),
                    Highlight.HighlightStyle.classForStyle(style)));
        } else {
            contentWebView.loadUrl(String.format(getString(R.string.sethighlightstyle),
                    Highlight.HighlightStyle.classForStyle(style)));
        }


    }

    public void highlightRemove() {
        contentWebView.loadUrl("javascript:alert(removeThisHighlight())");
    }

    public void onDrawPage(String result) {
        ULog.e("onResult1onDrawPage: " + result);
        String jsCode = "javascript:drawPageView(\"" + mChapterPosition + "\",\"" + result + "\")";

        ULog.e(jsCode);
        contentWebView.loadUrl(jsCode);
    }
}