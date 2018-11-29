package com.vipvideo.ui.reader.bean;

import java.util.List;

import lixh.ireader.bean.BookChapterBean;

/**
 * Created by newbiechen on 17-5-20.
 */
public class BookInfoBean {
    //所属的书的id
    private String bookId;
    List<BookChapterBean> chapterBeans;

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public List<BookChapterBean> getChapterBeans() {
        return this.chapterBeans;
    }

    public void setChapterBeans(List<BookChapterBean> chapterBeans) {
        this.chapterBeans = chapterBeans;
    }
}
