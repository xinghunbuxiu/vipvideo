package com.vipvideo.ui.reader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.vipvideo.ui.reader.fragment.FolioPageFragment;

/**
 * Created by mahavir on 4/2/16.
 */
public class FolioPageFragmentAdapter extends FragmentStatePagerAdapter {

    BookChapterBean bookChapterBean;
    FolioPageFragment mFolioPageFragment;
    int count = 0;
    String mBookId;

    public FolioPageFragmentAdapter(FragmentManager supportFragmentManager, String mBookId) {
        super(supportFragmentManager);
        this.mBookId = mBookId;
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("0", position + "1");
        mFolioPageFragment = FolioPageFragment.newInstance(mBookId, position + 1, bookChapterBean);
        return mFolioPageFragment;
    }

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return count;
    }

}
