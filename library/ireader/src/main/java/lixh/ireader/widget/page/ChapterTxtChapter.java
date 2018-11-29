package lixh.ireader.widget.page;

import java.util.List;

/**
 * Created by newbiechen on 17-7-1.
 */

public class ChapterTxtChapter {

    //章节所属的小说(网络)
    public String bookId;
    //章节的链接(网络)
    public String link;

    //章节名(共用)
    public String title;
    //二级
    List<TxtChapter> chapters;

    public void setChapters(List<TxtChapter> chapters) {
        this.chapters = chapters;
    }

    public List<TxtChapter> getChapters() {
        return this.chapters;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
