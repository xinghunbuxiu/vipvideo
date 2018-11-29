package com.vipvideo.ui.reader.fragment;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
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
    private String mBookTitle;
    private String mBookId;
    private TextSelectionSupport mTextSelectionSupport;
    private String mSelectedText;
    private int mChapterPosition = 1;

    public static FolioPageFragment newInstance(String bookId, int pageIndex) {
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
    }


    private void initWebView() {
        contentWebView.addJavascriptInterface(new WebViewListening(), "controller");
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
                layout.getEmptyView().setLoadingTip(LoadingTip.LoadStatus.LOADING);

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
        layout.getEmptyView().setLoadingTip(LoadingTip.LoadStatus.FINISH);

    }
}