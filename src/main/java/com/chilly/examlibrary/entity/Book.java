package com.chilly.examlibrary.entity;

/**
 * @auther ChillyLin
 * @create 2020/12/21
 */
public class Book {
    private Long book_id;
    private String book_title;    //书名
    private String book_intro;    //书籍简介
    private String book_author;   //书籍作者
    private String book_data;     //书籍相关的资料路径
    private String book_createTime; //出版时间
    private String book_publisher; //出版社
    private String book_directory; //书籍目录
    private String book_img;       //书籍封面

    private Long book_teacher_id;  //对应推荐该书的老师的ID
    private Teacher teacher;

    private Long book_classify_id; //书籍分类
    private Classify classify;

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public String getBook_directory() {
        return book_directory;
    }

    public void setBook_directory(String book_directory) {
        this.book_directory = book_directory;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
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

    public String getBook_intro() {
        return book_intro;
    }

    public void setBook_intro(String book_intro) {
        this.book_intro = book_intro;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_data() {
        return book_data;
    }

    public void setBook_data(String book_data) {
        this.book_data = book_data;
    }

    public String getBook_createTime() {
        return book_createTime;
    }

    public void setBook_createTime(String book_createTime) {
        this.book_createTime = book_createTime;
    }

    public Long getBook_teacher_id() {
        return book_teacher_id;
    }

    public void setBook_teacher_id(Long book_teacher_id) {
        this.book_teacher_id = book_teacher_id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getBook_classify_id() {
        return book_classify_id;
    }

    public void setBook_classify_id(Long book_classify_id) {
        this.book_classify_id = book_classify_id;
    }

    public Classify getClassifty() {
        return classify;
    }

    public void setClassifty(Classify classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", book_title='" + book_title + '\'' +
                ", book_intro='" + book_intro + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_data='" + book_data + '\'' +
                ", book_createTime='" + book_createTime + '\'' +
                ", book_publisher='" + book_publisher + '\'' +
                ", book_directory='" + book_directory + '\'' +
                ", book_img='" + book_img + '\'' +
                ", book_teacher_id=" + book_teacher_id +
                ", teacher=" + teacher +
                ", book_classify_id=" + book_classify_id +
                ", classify=" + classify +
                '}';
    }
}
