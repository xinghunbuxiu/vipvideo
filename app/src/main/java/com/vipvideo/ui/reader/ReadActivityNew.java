package com.vipvideo.ui.reader;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.lixh.base.BaseActivity;
import com.lixh.view.LoadView.Builder;
import com.lixh.view.SlideMenu;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.ReadPresenter;
import com.vipvideo.ui.reader.bean.BookInfoBean;
import com.vipvideo.ui.reader.category.CategoryLayout;
import com.vipvideo.ui.reader.duokanBook.CategoryInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import lixh.ireader.widget.page.ChapterTxtChapter;
import lixh.ireader.widget.page.PageLoader;
import lixh.ireader.widget.page.PageView;
import lixh.ireader.widget.page.TxtChapter;


/**
 * Created by newbiechen on 17-5-16.
 */

public class ReadActivityNew extends BaseActivity<ReadPresenter> implements PageView.TouchListener {
    private static final String TAG = "ReadActivity";
    @Bind(R.id.pageView)
    PageView mPageView;

    PageLoader pageLoader;

    @Bind(R.id.fl_top_view)
    FrameLayout topMenuView;
    @Bind(R.id.bl_bottom_menu)
    FrameLayout bottomView;
    //目录
    CategoryLayout categoryLayout;
    //底部菜单
    ReadTopView readTopView;
    //底部菜单
    ReadBottomView readBottomView;
    CategoryInfo categoryInfo;
    private String mBookId;

    private boolean isNightMode;
    private int mChapterPosition;


    @Override
    public void initTitle(UToolBar toolBar) {
//        toolBar.setTitle(mCollBook.getTitle());
        //半透明化StatusBar
//        SystemBarUtils.transparentStatusBar(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_read_new;
    }

    @Override
    public void initLoad(Builder builder) {
        super.initLoad(builder);
        builder.hasToolbar = false;
        categoryLayout = new CategoryLayout(this);
        builder.slideMenu = true;
        builder.setSlideMenu(SlideMenu.Slide.LEFT, categoryLayout);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mBookId = "00d6c9893c104a14af2f7595523d4263";
        initMenuView();
        initWidget();
        mPresenter.loadCategory(mBookId);
    }

    private void initMenuView() {
        readTopView = new ReadTopView(this, topMenuView);
        readBottomView = new ReadBottomView(this, bottomView);
        readBottomView.setConfigChangedListener(new ReadBottomView.IConfigChangedListener() {
            @Override
            public void OnClick(View v) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    protected void initWidget() {
        mPageView.setPageLoader(pageLoader = new NetPageLoader());
        mPageView.setTouchListener(this);
        pageLoader.setOnPageChangeListener(new PageLoader.OnPageChangeListener() {
            @Override
            public void onChapterChange(int pos) {

            }

            @Override
            public void requestChapters(List requestChapters) {

            }

            @Override
            public void onCategoryFinish(List chapters) {

            }

            @Override
            public void onPageCountChange(int count) {

            }

            @Override
            public void onPageChange(int pos) {

            }
        });
    }

    //多看
    public void setCategoryInfo(CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
        BookInfoBean bookInfoBean = new BookInfoBean();
        pageLoader.setCategoryInfo(bookInfoBean);
        categoryLayout.notifyCategory(converChapterList(categoryInfo));
        pageLoader.refreshChapterList();
    }

    public List<ChapterTxtChapter> converChapterList(CategoryInfo categoryInfo) {
        List<ChapterTxtChapter> chapters = new ArrayList<>();
        for (CategoryInfo.TocBean.ChildrenBean bean : categoryInfo.getToc().getChildren()) {
            ChapterTxtChapter chapter = new ChapterTxtChapter();
            chapter.setTitle(bean.getName());
            chapter.setBookId(mBookId);
            List<TxtChapter> subChapters = new ArrayList<>();
            if (bean != null) {
                for (CategoryInfo.TocBean.ChildrenBean subBean : bean.getChildren()) {
                    TxtChapter subChapter = new TxtChapter();
                    subChapter.setTitle(bean.getName());
                    subChapter.setBookId(mBookId);
                    subChapters.add(subChapter);
                }
            }
            chapters.add(chapter);
        }
        return chapters;
    }

    @Override
    public boolean onTouch() {
        return hideReadMenu();
    }

    private boolean hideReadMenu() {
        if (readTopView.isVisible() || readBottomView.isVisible()) {
            readTopView.toggleMenu();
            readBottomView.toggleMenu();
            return true;
        }
        return false;
    }

    @Override
    public void center() {
        readTopView.toggleMenu();
        readBottomView.toggleMenu();
    }

    @Override
    public void prePage() {
    }

    @Override
    public void nextPage() {

    }

    @Override
    public void cancel() {

    }


}
