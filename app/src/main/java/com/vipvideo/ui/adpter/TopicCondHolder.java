package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.bean.MovieTypeBean;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class TopicCondHolder extends VBaseHolder<MovieTypeBean.TopcondsBean> {
    @Bind(R.id.tv_tag)
    TextView tvTag;

    public TopicCondHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, MovieTypeBean.TopcondsBean data) {
        super.setData(ps, data);
        tvTag.setText(data.getTitle());
    }

    public void init() {
        tvTag.setOnClickListener((v) -> {
            if (mListener != null) {
                mListener.onItemClick(v, 0, mData);
            }
        });
    }
}
