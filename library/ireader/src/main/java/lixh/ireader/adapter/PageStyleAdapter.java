package lixh.ireader.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.lixh.base.adapter.recycleview.EasyRVAdapter;
import com.lixh.base.adapter.recycleview.EasyRVHolder;

import java.util.List;

import lixh.ireader.widget.page.PageStyle;


/**
 * Created by newbiechen on 17-5-19.
 */

public class PageStyleAdapter extends EasyRVAdapter<Drawable> {
    private int currentChecked;

    public PageStyleAdapter(Context context, List<Drawable> list) {
        super(context, list);
    }


    public void setPageStyleChecked(PageStyle pageStyle) {
        currentChecked = pageStyle.ordinal();
    }


    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, Drawable item) {
        viewHolder.setOnItemViewClickListener(v -> {

        });

    }
}
