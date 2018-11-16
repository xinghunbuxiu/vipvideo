package lixh.ireader.view;

import android.app.Activity;
import android.view.View;

import com.lixh.view.BaseSlideView;

/**
 * Created by LIXH on 2017/5/15.
 * email lixhVip9@163.com
 * des
 */

public class CategoryLayout extends BaseSlideView {

    public CategoryLayout(Activity activity) {
        super(activity);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_category;
    }

    @Override
    public void init() {
        isAnim = false;
        following = false;
    }

    @Override
    public void initView(View slideView) {

    }


}
