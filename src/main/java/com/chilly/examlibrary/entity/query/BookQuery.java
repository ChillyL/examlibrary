package com.chilly.examlibrary.entity.query;

/**
 * 用于首页列表展示
 * @auther ChillyLin
 * @create 2020/12/23
 */
public class BookQuery {
    private Long book_id;
    private String book_title;    //书名
    private Long book_classify_id;
    private String book_classify_name; //书籍分类
    private String book_img;   //书籍封面

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public Long getBook_classify_id() {
        return book_classify_id;
    }

    public void setBook_classify_id(Long book_classify_id) {
        this.book_classify_id = book_classify_id;
    }

    public String getBook_classify_name() {
        return book_classify_name;
    }

    public void setBook_classify_name(String book_classify_name) {
        this.book_classify_name = book_classify_name;
    }

    @Override
    public String toString() {
        return "BookQuery{" +
                "book_id=" + book_id +
                ", book_title='" + book_title + '\'' +
                ", book_classify_id=" + book_classify_id +
                ", book_classify_name=" + book_classify_name +
                '}';
    }
}
