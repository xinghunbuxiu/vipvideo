package com.vipvideo.view;

import android.app.Activity;
import android.widget.TextView;

import com.lixh.view.BaseSlideView;
import com.vipvideo.R;

import butterknife.Bind;

/**
 * Created by LIXH on 2017/5/15.
 * email lixhVip9@163.com
 * des
 */

public class SlideLeftView extends BaseSlideView {

    @Bind(R.id.textView)
    TextView textView;
    public SlideLeftView(Activity activity) {
        super(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.slide_menu_layout;
    }

    @Override
    public void init() {
        isAnim = false;
        following = false;
        textView.setText("dddddddd");
    }

}
