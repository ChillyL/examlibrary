package com.chilly.examlibrary.entity;

import java.util.Date;


public class Course {

    private Long course_id;
    private String course_title;   //标题
    private String course_intro;   //简介
    private String course_author;  //作者
    private String course_data;    //相关资料url
    private Date course_createTime;//创建时间
    private String course_work;    //课后作业

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_intro() {
        return course_intro;
    }

    public void setCourse_intro(String course_intro) {
        this.course_intro = course_intro;
    }

    public String getCourse_author() {
        return course_author;
    }

    public void setCourse_author(String course_author) {
        this.course_author = course_author;
    }

    public String getCourse_data() {
        return course_data;
    }

    public void setCourse_data(String course_data) {
        this.course_data = course_data;
    }

    public Date getCourse_createTime() {
        return course_createTime;
    }

    public void setCourse_createTime(Date course_createTime) {
        this.course_createTime = course_createTime;
    }

    public String getCourse_work() {
        return course_work;
    }

    public void setCourse_work(String course_work) {
        this.course_work = course_work;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", course_title='" + course_title + '\'' +
                ", course_intro='" + course_intro + '\'' +
                ", course_author='" + course_author + '\'' +
                ", course_data='" + course_data + '\'' +
                ", course_createTime=" + course_createTime +
                ", course_work='" + course_work + '\'' +
                '}';
    }
}
