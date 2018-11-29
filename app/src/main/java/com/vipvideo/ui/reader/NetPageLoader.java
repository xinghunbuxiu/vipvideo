package com.vipvideo.ui.reader;

import com.lixh.utils.SharedPreferencesUtil;
import com.vipvideo.ui.reader.duokanBook.CategoryInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import lixh.ireader.api.Constant;
import lixh.ireader.bean.BookChapterBean;
import lixh.ireader.util.MD5Utils;
import lixh.ireader.widget.page.PageLoader;
import lixh.ireader.widget.page.TxtChapter;

/**
 * Created by newbiechen on 17-5-29.
 * 网络页面加载器
 */

public class NetPageLoader extends PageLoader<List<BookChapterBean>> {
    private static final String TAG = "PageFactory";

    private List<TxtChapter> convertTxtChapter(List<BookChapterBean> bookChapters) {
        List<TxtChapter> txtChapters = new ArrayList<>(bookChapters.size());
        for (BookChapterBean bean : bookChapters) {
            TxtChapter chapter = new TxtChapter();
            chapter.setBookId(bean.getBookId());
            chapter.setTitle(bean.getTitle());
            chapter.setLink(bean.getLink());
            txtChapters.add(chapter);
        }
        return txtChapters;
    }

    public void setCategoryInfo(List<BookChapterBean> bookChapterBeen) {
        //进行设定BookChapter所属的书的id。
        for (BookChapterBean bookChapter : bookChapterBeen) {
            bookChapter.setId(MD5Utils.strToMd5By16(bookChapter.getLink()));
            bookChapter.setBookId();
        }
    }

    @Override
    public void refreshChapterList() {
        if (categoryInfo.getChapters() == null) return;

        // 将 BookChapter 转换成当前可用的 Chapter
        mChapterList = convertTxtChapter(mCollBook.getBookChapters());
        isChapterListPrepare = true;

        // 目录加载完成，执行回调操作。
        if (mPageChangeListener != null) {
            mPageChangeListener.onCategoryFinish(mChapterList);
        }

        // 如果章节未打开
        if (!isChapterOpen()) {
            // 打开章节
            openChapter();
        }
    }

    @Override
    protected BufferedReader getChapterReader(TxtChapter chapter) throws Exception {
        String path = chapter.getLink() + File.separator + chapter.title + Constant.BookSuffix.SUFFIX_NB;
        String info = SharedPreferencesUtil.getInstance().getString(path);
        Reader reader = new StringReader(info);
        BufferedReader br = new BufferedReader(reader);
        return br;
    }

    @Override
    protected boolean hasChapterData(TxtChapter chapter) {
        return false;
    }

    // 装载上一章节的内容
    @Override
    public boolean parsePrevChapter() {
        boolean isRight = super.parsePrevChapter();

        if (mStatus == STATUS_FINISH) {
            loadPrevChapter();
        } else if (mStatus == STATUS_LOADING) {
            loadCurrentChapter();
        }
        return isRight;
    }

    // 装载当前章内容。
    public boolean parseCurChapter() {
        boolean isRight = super.parseCurChapter();

        if (mStatus == STATUS_LOADING) {
            loadCurrentChapter();
        }
        return isRight;
    }

    // 装载下一章节的内容
    public boolean parseNextChapter() {
        boolean isRight = super.parseNextChapter();

        if (mStatus == STATUS_FINISH) {
            loadNextChapter();
        } else if (mStatus == STATUS_LOADING) {
            loadCurrentChapter();
        }

        return isRight;
    }

    /**
     * 加载当前页的前面两个章节
     */
    private void loadPrevChapter() {
        if (mPageChangeListener != null) {
            int end = mCurChapterPos;
            int begin = end - 2;
            if (begin < 0) {
                begin = 0;
            }

            requestChapters(begin, end);
        }
    }

    /**
     * 加载前一页，当前页，后一页。
     */
    private void loadCurrentChapter() {
        if (mPageChangeListener != null) {
            int begin = mCurChapterPos;
            int end = mCurChapterPos;

            // 是否当前不是最后一章
            if (end < mChapterList.size()) {
                end = end + 1;
                if (end >= mChapterList.size()) {
                    end = mChapterList.size() - 1;
                }
            }

            // 如果当前不是第一章
            if (begin != 0) {
                begin = begin - 1;
                if (begin < 0) {
                    begin = 0;
                }
            }

            requestChapters(begin, end);
        }
    }

    /**
     * 加载当前页的后两个章节
     */
    private void loadNextChapter() {
        if (mPageChangeListener != null) {

            // 提示加载后两章
            int begin = mCurChapterPos + 1;
            int end = begin + 1;

            // 判断是否大于最后一章
            if (begin >= mChapterList.size()) {
                // 如果下一章超出目录了，就没有必要加载了
                return;
            }

            if (end > mChapterList.size()) {
                end = mChapterList.size() - 1;
            }

            requestChapters(begin, end);
        }
    }

    private void requestChapters(int start, int end) {
        // 检验输入值
        if (start < 0) {
            start = 0;
        }

        if (end >= mChapterList.size()) {
            end = mChapterList.size() - 1;
        }


        List<TxtChapter> chapters = new ArrayList<>();

        // 过滤，哪些数据已经加载了
        for (int i = start; i <= end; ++i) {
            TxtChapter txtChapter = mChapterList.get(i);
            if (!hasChapterData(txtChapter)) {
                chapters.add(txtChapter);
            }
        }

        if (!chapters.isEmpty()) {
            mPageChangeListener.requestChapters(chapters);
        }
    }

    @Override
    public void saveRecord() {
        super.saveRecord();
        if (mCollBook != null && isChapterListPrepare) {
            //表示当前CollBook已经阅读
            mCollBook.setIsUpdate(false);
            mCollBook.setLastRead(StringUtils.
                    dateConvert(System.currentTimeMillis(), Constant.FORMAT_BOOK_DATE));
            //直接更新
            BookRepository.getInstance()
                    .saveCollBook(mCollBook);
        }
    }
}

