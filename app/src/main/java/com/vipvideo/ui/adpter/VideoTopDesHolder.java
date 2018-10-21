package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.bean.VideoInfoBean;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class VideoTopDesHolder extends VBaseHolder<VideoInfoBean> {
    @Bind(R.id.tv_video_name)
    TextView tvName;
    @Bind(R.id.tv_video_des)
    TextView tvDes;
    @Bind(R.id.tv_video_type_des)
    TextView tvTypeDes;


    public VideoTopDesHolder(View itemView) {
        super (itemView);
    }

    @Override
    public void setData(int ps, VideoInfoBean data) {
        super.setData (ps, data);
        tvName.setText (mData.getTitle ( ));
        tvTypeDes.setText (mData.getPubtime ( ));

    }

    public void init() {
        tvDes.setOnClickListener ((View v) -> {
            if (actionListener != null) {

            }
        });
    }
}
