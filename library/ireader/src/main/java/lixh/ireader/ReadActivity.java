package lixh.ireader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.lixh.base.BaseActivity;
import com.lixh.ireader.R;
import com.lixh.view.LoadView.Builder;
import com.lixh.view.UToolBar;

import lixh.ireader.adapter.CategoryAdapter;
import lixh.ireader.bean.CollBookBean;
import lixh.ireader.presenter.ReadPresenter;
import lixh.ireader.view.CurlPage;
import lixh.ireader.view.CurlView;
import lixh.ireader.view.CurlView.PageProvider;

/**
 * Created by newbiechen on 17-5-16.
 */

public class ReadActivity extends BaseActivity<ReadPresenter> {
    private static final String TAG = "ReadActivity";
    public static final int REQUEST_MORE_SETTING = 1;
    public static final String EXTRA_COLL_BOOK = "extra_coll_book";
    public static final String EXTRA_IS_COLLECTED = "extra_is_collected";

    // 注册 Brightness 的 uri
    private final Uri BRIGHTNESS_MODE_URI =
            Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS_MODE);
    private final Uri BRIGHTNESS_URI =
            Settings.System.getUriFor(Settings.System.SCREEN_BRIGHTNESS);
    private final Uri BRIGHTNESS_ADJ_URI =
            Settings.System.getUriFor("screen_auto_brightness_adj");

    CurlView mPvPage;
    private PageProvider mPageLoader;
    private CategoryAdapter mCategoryAdapter;
    private CollBookBean mCollBook;


    private String mBookId;

    public static void startActivity(Context context, CollBookBean collBook, boolean isCollected) {
        context.startActivity(new Intent(context, ReadActivity.class)
                .putExtra(EXTRA_IS_COLLECTED, isCollected)
                .putExtra(EXTRA_COLL_BOOK, collBook));
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mCollBook = getIntent().getParcelableExtra(EXTRA_COLL_BOOK);
        mBookId = mCollBook.get_id();
        initView();
    }

    @Override
    public void initTitle(UToolBar toolBar) {
        //设置标题
        toolBar.setTitle(mCollBook.getTitle());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    public void initLoad(Builder builder) {
        super.initLoad(builder);
    }


    protected void initView() {
        mPvPage = findViewById(R.id.read_pv_page);
        mPvPage.setPageProvider(new PageProvider() {
            @Override
            public int getPageCount() {
                return 0;
            }

            @Override
            public void updatePage(CurlPage page, int width, int height, int index) {

            }
        });
    }


}
