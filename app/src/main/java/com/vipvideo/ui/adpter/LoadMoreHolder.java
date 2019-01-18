package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.bean.GroupVideoInfo.SlicesBean.VideosBean;
import com.vipvideo.view.RoundImageView;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class LoadMoreHolder extends VBaseHolder<VideosBean> {
    @Bind(R.id.iv_image)
    RoundImageView iv_image;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_label)
    TextView tv_content;

    public LoadMoreHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, VideosBean data) {
        super.setData(ps, data);
        Glide.with (mContext).load (data.getImgv_url()).centerCrop ( ).diskCacheStrategy (DiskCacheStrategy.ALL).into(iv_image);
        tv_title.setText(data.getTitle());
        tv_content.setText(data.getBrief());
    }

}
