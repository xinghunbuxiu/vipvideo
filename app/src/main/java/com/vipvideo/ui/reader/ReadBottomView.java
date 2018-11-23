package com.vipvideo.ui.reader;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lixh.base.BasePageView;
import com.lixh.ireader.R;
import com.vipvideo.ui.reader.category.CategoryLayout;
import com.vipvideo.ui.reader.dialog.ReadSettingDialog;

import butterknife.Bind;
import lixh.ireader.widget.page.PageLoader;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by LIXH on 2018/11/19.
 * email lixhVip9@163.com
 * des
 */

public class ReadBottomView extends BasePageView {
    @Bind(R.id.read_tv_page_tip)
    TextView mTvPageTip;
    @Bind(R.id.read_ll_bottom_menu)
    LinearLayout mLlBottomMenu;
    @Bind(R.id.read_tv_pre_chapter)
    TextView mTvPreChapter;
    @Bind(R.id.read_sb_chapter_progress)
    SeekBar mSbChapterProgress;
    @Bind(R.id.read_tv_next_chapter)
    TextView mTvNextChapter;
    @Bind(R.id.read_tv_category)
    TextView mTvCategory;
    @Bind(R.id.read_tv_night_mode)
    TextView mTvNightMode;
    @Bind(R.id.read_tv_setting)
    TextView mTvSetting;
    PageLoader mPageLoader;
    CategoryLayout categoryLayout;
    private boolean isNightMode = false;
    private Animation mTopInAnim;
    private Animation mTopOutAnim;
    private Animation mBottomInAnim;
    private Animation mBottomOutAnim;
    private ReadSettingDialog mSettingDialog;

    public ReadBottomView(Activity activity, CategoryLayout categoryLayout, PageLoader mPageLoad) {
        super(activity);
        this.mPageLoader = mPageLoad;
        this.categoryLayout = categoryLayout;
    }

    @Override
    public int getLayoutId() {
        return R.layout.read_bottom_layout;
    }

    @Override
    public void init() {
        mBottomInAnim = AnimationUtils.loadAnimation(activity, R.anim.slide_bottom_in);
        mBottomOutAnim = AnimationUtils.loadAnimation(activity, R.anim.slide_bottom_out);
        mBottomOutAnim.setDuration(200);
        mSettingDialog = new ReadSettingDialog(activity, mPageLoader);
        mSbChapterProgress.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (mLlBottomMenu.getVisibility() == VISIBLE) {
                            //显示标题
                            mTvPageTip.setText((progress + 1) + "/" + (mSbChapterProgress.getMax() + 1));
                            mTvPageTip.setVisibility(VISIBLE);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //进行切换
                        int pagePos = mSbChapterProgress.getProgress();
                        if (pagePos != mPageLoader.getPagePos()) {
                            mPageLoader.skipToPage(pagePos);
                        }
                        //隐藏提示
                        mTvPageTip.setVisibility(GONE);
                    }
                }
        );

        mTvCategory.setOnClickListener(
                (v) -> {
                    //移动到指定位置
                    categoryLayout.setSelection(mPageLoader.getChapterPos());
                    categoryLayout.slideMenu.open();
                }
        );
        mTvSetting.setOnClickListener(
                (v) -> {
                    categoryLayout.slideMenu.close();
                    mSettingDialog.show();
                }
        );

        mTvPreChapter.setOnClickListener(
                (v) -> {
                    if (mPageLoader.skipPreChapter()) {
                        categoryLayout.setChapter(mPageLoader.getChapterPos());
                    }
                }
        );

        mTvNextChapter.setOnClickListener(
                (v) -> {
                    if (mPageLoader.skipNextChapter()) {
                        categoryLayout.setChapter(mPageLoader.getChapterPos());
                    }
                }
        );

        mTvNightMode.setOnClickListener(
                (v) -> {
                    mPageLoader.setNightMode(!isNightMode);
                    toggleNightMode(!isNightMode);
                }
        );

    }

    public void toggleNightMode(boolean isNightMode) {
        if (isNightMode) {
            mTvNightMode.setText(activity.getString(R.string.nb_mode_morning));
            Drawable drawable = ContextCompat.getDrawable(activity, com.vipvideo.R.drawable.ic_read_menu_morning);
            mTvNightMode.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        } else {
            mTvNightMode.setText(activity.getString(R.string.nb_mode_night));
            Drawable drawable = ContextCompat.getDrawable(activity, com.vipvideo.R.drawable.ic_read_menu_night);
            mTvNightMode.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        }
    }

    public void hideTip() {
        mTvPageTip.setVisibility(GONE);
    }

    public void setPageCountChange(int count) {
        mSbChapterProgress.setMax(Math.max(0, count - 1));
        mSbChapterProgress.setProgress(0);
        // 如果处于错误状态，那么就冻结使用
        if (mPageLoader.getPageStatus() == PageLoader.STATUS_LOADING
                || mPageLoader.getPageStatus() == PageLoader.STATUS_ERROR) {
            mSbChapterProgress.setEnabled(false);
        } else {
            mSbChapterProgress.setEnabled(true);
        }
    }

    public void onPageChange(int pos) {
        mSbChapterProgress.post(
                () -> mSbChapterProgress.setProgress(pos)
        );
    }

    public void setVisible(int visible) {
        if (visible == GONE) {
            mLlBottomMenu.startAnimation(mBottomOutAnim);
            mLlBottomMenu.setVisibility(GONE);
            mTvPageTip.setVisibility(GONE);
        } else {
            mLlBottomMenu.startAnimation(mBottomInAnim);
            mLlBottomMenu.setVisibility(View.VISIBLE);
        }
    }
}
