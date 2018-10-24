package com.vipvideo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.lixh.base.BaseFragment;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.util.UserInfoUtils;

import butterknife.Bind;


public class MyFragment extends BaseFragment {

    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_vip_time)
    TextView tvVipTime;

    @Override
    public void initTitle(UToolBar toolBar) {
        toolBar.setTitle("会员中心");
        toolBar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if (UserInfoUtils.isLogin()) {
            tvUsername.setText(UserInfoUtils.getUserInfo().getAdvert());
            tvVipTime.setText(UserInfoUtils.getUserInfo().getTime()+"");
        } else {
            tvUsername.setText("请登录");
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
