package com.vipvideo.ui.adpter;

import android.content.Context;
import android.view.View;

import com.lixh.base.adapter.VBaseHolder;
import com.vipvideo.R;
import com.vipvideo.model.HomeModel;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import butterknife.Bind;

/**
 * Created by Moushao on 2017/8/30.
 */

public class BannerHolder extends VBaseHolder<HomeModel> {
    @Bind(R.id.banner)
    Banner mBanner;

    public BannerHolder(View itemView) {
        super (itemView);
    }

    @Override
    public void setData(int ps, HomeModel data) {
        super.setData (ps, data);
        initBanner ( );
    }

    private void initBanner() {
        mBanner.setIndicatorGravity (BannerConfig.RIGHT);
        //设置图片集合
        mBanner.setImages (mData.getBannerBeans ( ));
        //设置图片加载器
        mBanner.setImageLoader (new ImageLoaderInterface( ) {

            @Override
            public void displayImage(Context context, Object o, View view) {

            }

            @Override
            public View createImageView(Context context) {
                return null;
            }
        });

        //设置点击事件监听
        mBanner.setOnBannerListener (i -> mListener.onItemClick (mView, i, mData));
        //banner设置方法全部调用完毕时最后调用
        mBanner.start ( );
    }
}
