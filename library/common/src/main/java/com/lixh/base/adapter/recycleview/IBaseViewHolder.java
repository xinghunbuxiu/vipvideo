package com.lixh.base.adapter.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixh.base.adapter.IViewHolder;

/**
 * Created by LIXH on 2018/11/16.
 * email lixhVip9@163.com
 * des
 **/

public class IBaseViewHolder<T> extends RecyclerView.ViewHolder {
    public IViewHolder<T> holder;

    public IBaseViewHolder(View itemView, IViewHolder<T> holder) {
        super(itemView);
        this.holder = holder;
        holder.initView();
    }
}
