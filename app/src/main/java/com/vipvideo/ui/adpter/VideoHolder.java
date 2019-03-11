package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lixh.base.adapter.VBaseHolder;
import com.lixh.utils.ImageLoaderUtils;
import com.vipvideo.R;
import com.vipvideo.bean.GroupVideoInfo.SlicesBean.VideosBean;
import com.vipvideo.view.RoundImageView;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class VideoHolder extends VBaseHolder<VideosBean> {
    @Bind(R.id.iv_image)
    RoundImageView iv_image;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_label)
    TextView tv_content;

    public VideoHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, VideosBean data) {
        super.setData(ps, data);
        ImageLoaderUtils.displayRound(mContext, iv_image, data.getImgv_url());
        tv_title.setText(data.getTitle());
        tv_content.setText(data.getBrief());
    }

}
