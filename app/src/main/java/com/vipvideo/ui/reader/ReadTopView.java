package com.vipvideo.ui.reader;

import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lixh.base.BasePageView;
import com.lixh.ireader.R;
import com.lixh.view.UToolBar;

import butterknife.Bind;

import static android.view.View.GONE;

/**
 * Created by LIXH on 2018/11/19.
 * email lixhVip9@163.com
 * des
 */

public class ReadTopView extends BasePageView {
    @Bind(R.id.read_tv_brief)
    TextView readTvBrief;
    @Bind(R.id.read_tv_community)
    TextView readTvCommunity;
    @Bind(R.id.toolbar)
    UToolBar toolbar;
    @Bind(R.id.read_abl_top_menu)
    AppBarLayout readAblTopMenu;
    private Animation mTopInAnim;
    private Animation mTopOutAnim;

    public ReadTopView(ReadActivityNew readActivityNew, FrameLayout topView) {
        super(readActivityNew, topView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.read_top_layout;
    }

    @Override
    public void init() {
        mTopInAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_top_in);
        mTopOutAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_top_out);
        //退出的速度要快
        mTopOutAnim.setDuration(200);
    }

    public void toggleMenu() {
        if (root.getVisibility() == View.VISIBLE) {
            //关闭
            root.startAnimation(mTopOutAnim);
            root.setVisibility(GONE);
        } else {
            root.startAnimation(mTopInAnim);
            root.setVisibility(View.VISIBLE);
        }
    }

    public boolean isVisible() {
        return root.getVisibility() == View.VISIBLE;
    }
}
