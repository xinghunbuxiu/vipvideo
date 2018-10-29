package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.bean.TitleBean;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class MoreHolder extends VBaseHolder<TitleBean> {
    @Bind(R.id.tv_more)
    TextView tvMore;

    public MoreHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, TitleBean data) {
        super.setData(ps, data);
        tvMore.setText(data.getTitle());
    }

    public void init() {
        tvMore.setOnClickListener((v) -> {
            if (mListener != null) {
                mListener.onItemClick(v, 0, mData);
            }
        });
    }
}
