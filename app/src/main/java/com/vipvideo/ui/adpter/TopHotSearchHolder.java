package com.vipvideo.ui.adpter;

import android.view.View;
import android.widget.TextView;

import com.lixh.base.adapter.VBaseHolder;
import com.lixh.base.adapter.abslistview.EasyLVAdapter;
import com.lixh.base.adapter.abslistview.EasyLVHolder;
import com.lixh.view.FlexBoxLayout;
import com.vipvideo.R;
import com.vipvideo.model.HomeModel;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class TopHotSearchHolder extends VBaseHolder<HomeModel.HotBean> {
    @Bind(R.id.flex_box)
    FlexBoxLayout searchLayout;

    public TopHotSearchHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(int ps, HomeModel.HotBean data) {
        super.setData(ps, data);
        searchLayout.setAdapter(new EasyLVAdapter<HomeModel.HotTvBean>(mContext, data.getBeans(), R.layout.home_sencond_layout) {
            @Override
            public void convert(EasyLVHolder holder, int position, HomeModel.HotTvBean o) {
                TextView tv_tag = holder.getView(R.id.tv_tag);
                tv_tag.setText(o.getHotTv_label());
            }
        });
    }

    public void init() {
        searchLayout.setOnTabSelectListner((v, obj, position) -> {
            if (mListener != null) {
                mListener.onItemClick(v, position, obj);
            }
        });
    }
}
