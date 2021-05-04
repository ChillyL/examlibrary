package com.chilly.examlibrary.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2021/5/3
 **/
public class CourseAnswer {
    private Long course_answer_id;
    private String course_answer_user_name;
    private String course_answer_content;
    private Date course_answer_createTime;
    private Long course_id;
    private Boolean course_answer_teacher;
    private Long parent_course_answer_id;  //自关联,父级id
    private int answer_sort;  //排

    private boolean is_teacher;  //是否是教师回复

    private String parentName; //
    //回复评论
    private List<CourseAnswer> replyCourseAnswers = new ArrayList<>();
    private CourseAnswer parentCourseAnswer;

    public boolean isIs_teacher() {
        return is_teacher;
    }

    public void setIs_teacher(boolean is_teacher) {
        this.is_teacher = is_teacher;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<CourseAnswer> getReplyCourseAnswers() {
        return replyCourseAnswers;
    }

    public void setReplyCourseAnswers(List<CourseAnswer> replyCourseAnswers) {
        this.replyCourseAnswers = replyCourseAnswers;
    }

    public CourseAnswer getParentCourseAnswer() {
        return parentCourseAnswer;
    }

    public void setParentCourseAnswer(CourseAnswer parentCourseAnswer) {
        this.parentCourseAnswer = parentCourseAnswer;
    }

    public Long getCourse_answer_id() {
        return course_answer_id;
    }

    public void setCourse_answer_id(Long course_answer_id) {
        this.course_answer_id = course_answer_id;
    }

    public String getCourse_answer_user_name() {
        return course_answer_user_name;
    }

    public void setCourse_answer_user_name(String course_answer_user_name) {
        this.course_answer_user_name = course_answer_user_name;
    }

    public String getCourse_answer_content() {
        return course_answer_content;
    }

    public void setCourse_answer_content(String course_answer_content) {
        this.course_answer_content = course_answer_content;
    }

    public Date getCourse_answer_createTime() {
        return course_answer_createTime;
    }

    public void setCourse_answer_createTime(Date course_answer_createTime) {
        this.course_answer_createTime = course_answer_createTime;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Boolean getCourse_answer_teacher() {
        return course_answer_teacher;
    }

    public void setCourse_answer_teacher(Boolean course_answer_teacher) {
        this.course_answer_teacher = course_answer_teacher;
    }

    public Long getParent_course_answer_id() {
        return parent_course_answer_id;
    }

    public void setParent_course_answer_id(Long parent_course_answer_id) {
        this.parent_course_answer_id = parent_course_answer_id;
    }

    public int getAnswer_sort() {
        return answer_sort;
    }

    public void setAnswer_sort(int answer_sort) {
        this.answer_sort = answer_sort;
    }

    @Override
    public String toString() {
        return "CourseAnswer{" +
                "course_answer_id=" + course_answer_id +
                ", course_answer_user_name='" + course_answer_user_name + '\'' +
                ", course_answer_content='" + course_answer_content + '\'' +
                ", course_answer_createTime=" + course_answer_createTime +
                ", course_id=" + course_id +
                ", course_answer_teacher=" + course_answer_teacher +
                ", parent_course_answer_id=" + parent_course_answer_id +
                ", answer_sort=" + answer_sort +
                '}';
    }
}
