package com.vipvideo.ui.adpter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.bean.BannerBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class GridHolder extends VBaseHolder<BannerBean> {
    @Bind(R.id.banner)
    Banner mBanner;

    public GridHolder(View itemView) {
        super (itemView);
    }

    @Override
    public void setData(int ps, BannerBean data) {
        super.setData (ps, data);
        initBanner ( );
    }

    private void initBanner() {
        mBanner.setIndicatorGravity (BannerConfig.RIGHT);
        //设置图片集合
        mBanner.setImages (mData.getPic_url ( ));
        //设置图片加载器
        mBanner.setImageLoader (new ImageLoader ( ) {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with (mContext).load ((String) o).centerCrop ( ).diskCacheStrategy (DiskCacheStrategy.ALL).into
                        (imageView);
            }
        });

        //设置点击事件监听
        mBanner.setOnBannerListener (i -> mListener.onItemClick (mView, i, mData));
        //banner设置方法全部调用完毕时最后调用
        mBanner.start ( );
    }
}
