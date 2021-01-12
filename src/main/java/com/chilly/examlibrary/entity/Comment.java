package com.chilly.examlibrary.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther ChillyLin
 * @date 2020/12/19
 */
public class Comment {

    private Long comment_id;
    private String comment_user_name;  //名字
    private String comment_user_number;   //学号或者教师编号
    private String comment_content;  //评论内容

    private Date comment_createTime;  //创建时间

    private Long comment_book_id; //关联的书籍id

    private boolean comment_teacher;  //是否是教师回复

    private Long parent_comment_id;  //自关联,父级id
    private String parentName; //
    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;


    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_user_name() {
        return comment_user_name;
    }

    public void setComment_user_name(String comment_user_name) {
        this.comment_user_name = comment_user_name;
    }

    public String getComment_user_number() {
        return comment_user_number;
    }

    public void setComment_user_number(String comment_user_number) {
        this.comment_user_number = comment_user_number;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getComment_createTime() {
        return comment_createTime;
    }

    public void setComment_createTime(Date comment_createTime) {
        this.comment_createTime = comment_createTime;
    }

    public Long getComment_book_id() {
        return comment_book_id;
    }

    public void setComment_book_id(Long comment_book_id) {
        this.comment_book_id = comment_book_id;
    }

    public boolean isComment_teacher() {
        return comment_teacher;
    }

    public void setComment_teacher(boolean comment_teacher) {
        this.comment_teacher = comment_teacher;
    }

    public Long getParent_comment_id() {
        return parent_comment_id;
    }

    public void setParent_comment_id(Long parent_comment_id) {
        this.parent_comment_id = parent_comment_id;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", comment_user_name='" + comment_user_name + '\'' +
                ", comment_user_number='" + comment_user_number + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_createTime=" + comment_createTime +
                ", comment_book_id=" + comment_book_id +
                ", comment_teacher=" + comment_teacher +
                ", parent_comment_id=" + parent_comment_id +
                ", parentName='" + parentName + '\'' +
                ", replyComments=" + replyComments +
                ", parentComment=" + parentComment +
                '}';
    }
}
