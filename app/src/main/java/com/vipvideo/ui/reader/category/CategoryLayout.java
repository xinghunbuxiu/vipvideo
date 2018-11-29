package com.vipvideo.ui.reader.category;

import android.app.Activity;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.lixh.base.adapter.abslistview.CommonExpandableListAdapter;
import com.lixh.ireader.R;
import com.lixh.view.BaseSlideView;

import java.util.List;

import butterknife.Bind;
import lixh.ireader.widget.page.ChapterTxtChapter;
import lixh.ireader.widget.page.TxtChapter;

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
        mCategoryAdapter = new CommonExpandableListAdapter<TxtChapter, ChapterTxtChapter>(getActivity(), R.layout.item_category, R.layout.item_category) {

            @Override
            protected void getChildView(ViewHolder holder, int groupPositon, int childPositon, boolean isLastChild, TxtChapter data) {
                TextView textView = holder.getView(R.id.category_tv_chapter);//分组名字
                textView.setText(data.getTitle());
            }

            @Override
            protected void getGroupView(ViewHolder holder, int groupPositon, boolean isExpanded, ChapterTxtChapter data) {
                TextView textView = holder.getView(R.id.category_tv_chapter);//分组名字
                textView.setText(data.getTitle());
            }
        };
        mLvCategory.setAdapter(mCategoryAdapter);
        mLvCategory.setFastScrollEnabled(true);
    }

    public void setChapter(int pos) {
    }

    public void notifyCategory(List<ChapterTxtChapter> categoryInfo) {
        mCategoryAdapter.getGroupData().addAll(categoryInfo);
        for (int i = 0; i < mCategoryAdapter.getGroupCount(); i++) {
            List<TxtChapter> childrenBeans = categoryInfo.get(i).getChapters();
            if (childrenBeans != null) {
                mCategoryAdapter.getChildrenData().addAll(childrenBeans);
            }
        }
        mCategoryAdapter.notifyDataSetChanged();
    }
}
