package com.vipvideo.ui;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lixh.base.BaseActivity;
import com.lixh.view.LoadView;
import com.lixh.view.UToolBar;
import com.vipvideo.R;
import com.vipvideo.presenter.TabPresenter;
import com.vipvideo.ui.fragment.FoundFragment;
import com.vipvideo.ui.fragment.HomeFragment;
import com.vipvideo.ui.fragment.LiveStreamingFragment;
import com.vipvideo.ui.fragment.MyFragment;
import com.vipvideo.ui.login.LoginActivity;

/**
 * Created by LIXH on 2016/12/21.
 * email lixhVip9@163.com
 * des
 */
public class TabsActivity extends BaseActivity<TabPresenter> {


    @Override
    public boolean isShowBack() {
        return false;
    }

    @Override
    public boolean isDoubleExit() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initTitle(UToolBar toolBar) {

    }

    @Override
    public void initLoad(LoadView.Builder builder) {
        builder.swipeBack = false;
        builder.hasToolbar = false;
        builder.hasBottomBar = true;
        builder.addItem(new BottomNavigationItem(R.drawable.shouye, "首页")
                        .setActiveColorResource(R.color.colorAccent)
                , new BottomNavigationItem(R.drawable.zhibo, "直播")
                        .setActiveColorResource(R.color.colorAccent)
                , new BottomNavigationItem(R.drawable.faxian, "福利社")
                        .setActiveColorResource(R.color.colorAccent)
                , new BottomNavigationItem(R.drawable.wode, "我的")
                        .setActiveColorResource(R.color.colorAccent));
        builder.addFragment(new HomeFragment(), new LiveStreamingFragment(),new FoundFragment(),new MyFragment());
        builder.setOnTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (position == 2) {
                    intent.go(LoginActivity.class);
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

}
