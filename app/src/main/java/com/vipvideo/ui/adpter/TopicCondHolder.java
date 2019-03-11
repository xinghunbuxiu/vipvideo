package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lixh.base.adapter.VBaseHolder;
import com.lixh.utils.ImageLoaderUtils;
import com.vipvideo.R;
import com.vipvideo.model.HomeModel;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class TopicCondHolder extends VBaseHolder<HomeModel.HotTvBean> {
    @Bind(R.id.tv_topic_name)
    TextView tvTopicName;
    @Bind(R.id.tv_topic_author)
    TextView tvTopicAuthor;
    @Bind(R.id.iv_topic)
    ImageView ivTopic;

    public TopicCondHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, HomeModel.HotTvBean data) {
        super.setData(ps, data);
        tvTopicName.setText(data.getHotTv_label());
        tvTopicAuthor.setText(data.getHotTv_name());
        ImageLoaderUtils.displayRound(mContext, ivTopic, data.getHotTv_img());

    }

    public void init() {
        itemView.setOnClickListener((v) -> {
            if (mListener != null) {
                mListener.onItemClick(v, 0, mData);
            }
        });
    }
}
