package com.chilly.examlibrary.entity.query;

import com.chilly.examlibrary.entity.Student;

/**
 * @auther ChillyLin
 * @create 2020/12/27
 */
public class AnswerBookQuery {

    private Long classify_id;
    private String classify_name;

    private Long book_id;
    private String book_title;

    private Long answer_id;
    private int answer_score;

    private Long student_id;

    public Long getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(Long classify_id) {
        this.classify_id = classify_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
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

    public Long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    public int getAnswer_score() {
        return answer_score;
    }

    public void setAnswer_score(int answer_score) {
        this.answer_score = answer_score;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return  " 分类：" + classify_name + ' ' +
                " 书籍：" + book_title + ' ' +
                ", 思考题得分：" + answer_score + ' ';
    }
}
