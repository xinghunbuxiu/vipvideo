package com.vipvideo.ui.adpter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lixh.base.adapter.VBaseHolder;
import com.lixh.utils.ImageLoaderUtils;
import com.vipvideo.R;
import com.vipvideo.bean.SearchVideoInfo;
import com.vipvideo.view.RoundImageView;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class SearchVideoHolder extends VBaseHolder<SearchVideoInfo.DataBean> {
    @Bind(R.id.iv_image)
    RoundImageView iv_image;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_video_num)
    TextView tvVideoNum;
    @Bind(R.id.tv_video_tip)
    TextView tvVideoTip;

    public SearchVideoHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, SearchVideoInfo.DataBean data) {
        super.setData(ps, data);
        ImageLoaderUtils.displayRound(mContext, iv_image, TextUtils.isEmpty(data.getCoverHUrl()) ? data.getCoverUrl() : data.getCoverHUrl());

        tv_title.setText(data.getTitle());
        tvVideoNum.setText(data.getBasePalyNum());
        tvVideoTip.setText("豆瓣:" + data.getDoubanScore());
    }

    @Override
    public void init() {
        super.init();
        itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(v, position, mData);
            }
        });
    }
}
