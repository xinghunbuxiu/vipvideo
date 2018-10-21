package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.bean.VideoInfoBean;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class VideoTopShareHolder extends VBaseHolder<VideoInfoBean> {
    @Bind(R.id.tv_video_urls)
    TextView videoInfos;


    public VideoTopShareHolder(View itemView) {
        super (itemView);
    }

    @Override
    public void setData(int ps, VideoInfoBean data) {
        super.setData (ps, data);
        List<VideoInfoBean.SitesBean> sitesBeans = mData.getSites ( );
        videoInfos.setText (sitesBeans.get (0).getSite_name ( ));

    }

    public void init() {

        videoInfos.setOnClickListener ((View v) -> {
            if (mListener != null) {
                mListener.onItemClick (v, 0, mData);
            }
        });
    }
}
