package com.vipvideo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.lixh.utils.DensityUtil;


public class HorizontalListView<T> extends HorizontalScrollView {
    LinearLayout root;
    private OnItemClickListener<T> onItemClickListener;
    float itemWidth;

    /**
     * 回调接口
     */
    public interface OnItemClickListener<T> {
        /**
         * @param v        点击的 view
         * @param obj      点击的 view 所绑定的对象
         * @param position 点击位置的 index
         */
        public void onItemClicked(View v, T obj, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public HorizontalListView(Context context) {
        this(context, null);
    }

    public HorizontalListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        itemWidth = DensityUtil.getScreenWidth()/3;
        setFillViewport(true);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        root = new LinearLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(lp);
        addView(root);
    }

    public void setAdapter(BaseAdapter adapter) {
        if (adapter == null) {
            return;
        }
        root.removeAllViews();
        for (int i = 0; i < adapter.getCount(); i++) {
            final int tmp = i;
            final T obj = (T) adapter.getItem(i);
            final View v = adapter.getView(i, null, null);
            LayoutParams lp1 = new LayoutParams((int) itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp1.gravity = Gravity.CENTER;
            v.setLayoutParams(lp1);
            // view 点击事件触发时回调我们自己的接口
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(v, obj, tmp);
                    }
                }
            });

            root.addView(v);
        }

    }
}
