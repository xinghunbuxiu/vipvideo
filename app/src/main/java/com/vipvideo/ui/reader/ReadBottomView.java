package com.vipvideo.ui.reader;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lixh.base.BasePageView;
import com.lixh.ireader.R;
import com.vipvideo.ui.reader.dialog.ReadSettingDialog;

import butterknife.Bind;

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
    private boolean isNightMode = false;
    private Animation mBottomInAnim;
    private Animation mBottomOutAnim;
    private ReadSettingDialog mSettingDialog;
    ReadBottomView.IConfigChangedListener iConfigChangedListener;

    public interface IConfigChangedListener {
        void OnClick(View v);

        void onStopTrackingTouch(SeekBar seekBar);
    }

    public void setConfigChangedListener(IConfigChangedListener iConfigChangedListener) {
        this.iConfigChangedListener = iConfigChangedListener;
    }

    public ReadBottomView(ReadActivityNew readActivityNew, FrameLayout bottomView) {
        super(readActivityNew, bottomView);
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
        mSettingDialog = new ReadSettingDialog(activity);
        mSbChapterProgress.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (root.getVisibility() == VISIBLE) {
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
                        iConfigChangedListener.onStopTrackingTouch(seekBar);
                        //隐藏提示
                        mTvPageTip.setVisibility(GONE);
                    }
                }
        );

        mTvCategory.setOnClickListener(
                (v) -> {
                    iConfigChangedListener.OnClick(v);
                }
        );
        mTvSetting.setOnClickListener(
                (v) -> {
                    iConfigChangedListener.OnClick(v);
                }
        );

        mTvPreChapter.setOnClickListener(
                (v) -> {
                    iConfigChangedListener.OnClick(v);
                }
        );

        mTvNextChapter.setOnClickListener(
                (v) -> {
                    iConfigChangedListener.OnClick(v);
                }
        );

        mTvNightMode.setOnClickListener(
                (v) -> {
                    iConfigChangedListener.OnClick(v);
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


    public void onPageChange(int pos) {
        mSbChapterProgress.post(
                () -> mSbChapterProgress.setProgress(pos)
        );
    }

    public void toggleMenu() {
        if (mSettingDialog.isShowing()) {
            mSettingDialog.dismiss();
        }
        if (root.getVisibility() == VISIBLE) {
            root.startAnimation(mBottomOutAnim);
            root.setVisibility(GONE);
            mTvPageTip.setVisibility(GONE);
        } else {
            root.startAnimation(mBottomInAnim);
            root.setVisibility(View.VISIBLE);
        }
    }

    public boolean isVisible() {
        return root.getVisibility() == VISIBLE || mSettingDialog.isShowing();
    }
}
