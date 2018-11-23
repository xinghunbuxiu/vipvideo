package com.vipvideo.ui.reader;

import android.os.Bundle;
import android.view.animation.Animation;

import com.lixh.base.BaseActivity;
import com.lixh.view.LoadView.Builder;
import com.lixh.view.SlideMenu;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.ReadPresenter;
import com.vipvideo.ui.reader.adapter.FolioPageFragmentAdapter;
import com.vipvideo.ui.reader.category.CategoryLayout;
import com.vipvideo.ui.reader.duokanBook.CategoryInfo;

import butterknife.Bind;
import lixh.ireader.widget.DirectionalViewpager;

import static lixh.ireader.widget.DirectionalViewpager.Direction;
import static lixh.ireader.widget.DirectionalViewpager.OnPageChangeListener;

/**
 * Created by newbiechen on 17-5-16.
 */

public class ReadActivityNew extends BaseActivity<ReadPresenter> {
    private static final String TAG = "ReadActivity";
    @Bind(R.id.pageView)
    DirectionalViewpager mPageViewPager;
    //目录
    CategoryLayout categoryLayout;
    //底部菜单
    ReadBottomView readBottomView;
    CategoryInfo categoryInfo;
    private String mBookId;
    private Animation mTopInAnim;
    private Animation mTopOutAnim;
    private boolean isNightMode;
    private int mChapterPosition;
    private FolioPageFragmentAdapter mFolioPageFragmentAdapter;

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
        categoryLayout = new CategoryLayout(this);
        builder.slideMenu = true;
        builder.setSlideMenu(SlideMenu.Slide.LEFT, categoryLayout);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mBookId = "00d6c9893c104a14af2f7595523d4263";
        initWidget();
        mPresenter.loadCategory(mBookId);
    }

    protected void initWidget() {
        mPageViewPager.setDirection(Direction.VERTICAL);
        mPageViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position,
                                       float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mChapterPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mFolioPageFragmentAdapter = new FolioPageFragmentAdapter(getSupportFragmentManager(), mBookId);
        mPageViewPager.setAdapter(mFolioPageFragmentAdapter);

    }

    public void onOrenationChange(int orentation) {
        if (orentation == 0) {
            mPageViewPager.setDirection(Direction.VERTICAL);
            mFolioPageFragmentAdapter =
                    new FolioPageFragmentAdapter(getSupportFragmentManager(),
                            mBookId);
            mPageViewPager.setAdapter(mFolioPageFragmentAdapter);
            mPageViewPager.setOffscreenPageLimit(1);
            mPageViewPager.setCurrentItem(mChapterPosition);
        } else {
            mPageViewPager.setDirection(Direction.HORIZONTAL);
            mFolioPageFragmentAdapter =
                    new FolioPageFragmentAdapter(getSupportFragmentManager(),
                            mBookId);
            mPageViewPager.setAdapter(mFolioPageFragmentAdapter);
            mPageViewPager.setCurrentItem(mChapterPosition);
        }
    }

    public void setCategoryInfo(CategoryInfo categoryInfo) {
        this.categoryInfo = categoryInfo;
        mFolioPageFragmentAdapter.setCount(categoryInfo.getNumber_of_pages());
        categoryLayout.notifyCategory(categoryInfo);
    }
}
