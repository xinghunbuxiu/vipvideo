package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lixh.base.adapter.VBaseHolder;
import com.lixh.base.adapter.abslistview.EasyLVAdapter;
import com.lixh.base.adapter.abslistview.EasyLVHolder;
import com.vipvideo.R;
import com.vipvideo.model.HomeModel;
import com.vipvideo.view.HorizontalListView;
import com.vipvideo.view.RoundImageView;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class HomeListHolder extends VBaseHolder<HomeModel.HotBean> {

    @Bind(R.id.hz_list_view)
    HorizontalListView hz_list_view;

    public HomeListHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, HomeModel.HotBean data) {
        super.setData(ps, data);
        hz_list_view.setAdapter(new EasyLVAdapter<HomeModel.HotTvBean>(mContext, data.getBeans(), R.layout.home_video_layout) {
            @Override
            public void convert(EasyLVHolder holder, int position, HomeModel.HotTvBean hotBean) {
                RoundImageView iv_image = holder.getView(R.id.iv_image);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_label = holder.getView(R.id.tv_label);
                Glide.with(mContext).load(hotBean.getHotTv_img()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_image);
                tv_label.setText(hotBean.getHotTv_label());
                tv_title.setText(hotBean.getHotTv_name() + "\n" + hotBean.getHotTv_star());
            }
        });
    }

    @Override
    public void init() {
        hz_list_view.setOnItemClickListener((v, obj, position1) -> {
            if (mListener != null) {
                mListener.onItemClick(v, position1, obj);
            }
        });
    }
}
