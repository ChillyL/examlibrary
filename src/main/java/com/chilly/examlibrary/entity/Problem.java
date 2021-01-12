package com.chilly.examlibrary.entity;

import java.util.Date;

/**
 * @auther ChillyLin
 * @create 2020/12/25
 */
public class Problem {
    private Long problem_id;
    private String problem_content;  //题目详情
    private Date problem_createTime; //创建时间
    private Long problem_book_id;    //对应得书籍id
    private Long problem_teacher_id; //出题老师

    public Long getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Long problem_id) {
        this.problem_id = problem_id;
    }

    public String getProblem_content() {
        return problem_content;
    }

    public void setProblem_content(String problem_content) {
        this.problem_content = problem_content;
    }

    public Date getProblem_createTime() {
        return problem_createTime;
    }

    public void setProblem_createTime(Date problem_createTime) {
        this.problem_createTime = problem_createTime;
    }

    public Long getProblem_book_id() {
        return problem_book_id;
    }

    public void setProblem_book_id(Long problem_book_id) {
        this.problem_book_id = problem_book_id;
    }

    public Long getProblem_teacher_id() {
        return problem_teacher_id;
    }

    public void setProblem_teacher_id(Long problem_teacher_id) {
        this.problem_teacher_id = problem_teacher_id;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problem_id=" + problem_id +
                ", problem_content='" + problem_content + '\'' +
                ", problem_createTime=" + problem_createTime +
                ", problem_book_id=" + problem_book_id +
                ", problem_teacher_id=" + problem_teacher_id +
                '}';
    }
}
