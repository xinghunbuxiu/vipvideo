package com.vipvideo.ui.reader.category;

import android.app.Activity;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.lixh.base.adapter.abslistview.CommonExpandableListAdapter;
import com.lixh.ireader.R;
import com.lixh.view.BaseSlideView;
import com.vipvideo.ui.reader.duokanBook.CategoryInfo;
import com.vipvideo.ui.reader.duokanBook.CategoryInfo.TocBean.ChildrenBean;

import java.util.List;

import butterknife.Bind;

/**
 * Created by LIXH on 2017/5/15.
 * email lixhVip9@163.com
 * des
 */

public class CategoryLayout extends BaseSlideView {
    /***************left slide*******************************/
    @Bind(R.id.read_iv_category)
    ExpandableListView mLvCategory;
    private int selection;
    CommonExpandableListAdapter mCategoryAdapter;

    public CategoryLayout(Activity activity) {
        super(activity);
        isAnim = false;
        following = false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_category;
    }

    @Override
    public void init() {
        setUpAdapter();
        mLvCategory.setOnItemClickListener(
                (parent, view, position, id) -> {
                    slideMenu.close();
                }
        );
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public void open() {
        slideMenu.open();
    }

    public void setUpAdapter() {
        mCategoryAdapter = new CommonExpandableListAdapter<ChildrenBean, ChildrenBean>(getActivity(), R.layout.item_category, R.layout.item_category) {

            @Override
            protected void getChildView(ViewHolder holder, int groupPositon, int childPositon, boolean isLastChild, ChildrenBean data) {
                TextView textView = holder.getView(R.id.category_tv_chapter);//分组名字
                textView.setText(data.getName());
            }

            @Override
            protected void getGroupView(ViewHolder holder, int groupPositon, boolean isExpanded, ChildrenBean data) {
                TextView textView = holder.getView(R.id.category_tv_chapter);//分组名字
                textView.setText(data.getName());
            }
        };
        mLvCategory.setAdapter(mCategoryAdapter);
        mLvCategory.setFastScrollEnabled(true);
    }

    public void setChapter(int pos) {
    }

    public void notifyCategory(CategoryInfo categoryInfo) {
        mCategoryAdapter.getGroupData().addAll(categoryInfo.getToc().getChildren());
        for (int i = 0; i < mCategoryAdapter.getGroupCount(); i++) {
            List<ChildrenBean> childrenBeans = categoryInfo.getToc().getChildren().get(i).getChildren();
            if (childrenBeans != null) {
                mCategoryAdapter.getChildrenData().addAll(childrenBeans);
            }
        }
        mCategoryAdapter.notifyDataSetChanged();
    }
}
