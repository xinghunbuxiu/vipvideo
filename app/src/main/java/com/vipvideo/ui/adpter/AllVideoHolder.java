package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lixh.base.adapter.VBaseHolder;
import com.lixh.utils.ImageLoaderUtils;
import com.vipvideo.R;
import com.vipvideo.bean.AllVideoInfo.VideoListBean.VideosBean;
import com.vipvideo.view.RoundImageView;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class AllVideoHolder extends VBaseHolder<VideosBean> {
    @Bind(R.id.iv_video)
    RoundImageView iv_image;
    @Bind(R.id.tv_title)
    TextView tv_title;

    public AllVideoHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, VideosBean data) {
        super.setData(ps, data);
        ImageLoaderUtils.displayRound(mContext, iv_image, data.getImg_url());
        tv_title.setText(data.getTitle());
    }

    @Override
    public void init() {
        mView.setOnClickListener((v -> {
            if (mListener != null) mListener.onItemClick(v, position, mData);
        }));
    }
}
