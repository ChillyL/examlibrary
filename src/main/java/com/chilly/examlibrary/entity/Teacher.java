package com.chilly.examlibrary.entity;

public class Teacher {
    private Long teacher_id;
    private String teacher_name;
    private String teacher_number;
    private String teacher_password;

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_number() {
        return teacher_number;
    }

    public void setTeacher_number(String teacher_number) {
        this.teacher_number = teacher_number;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public void setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_number='" + teacher_number + '\'' +
                ", teacher_password='" + teacher_password + '\'' +
                '}';
    }
}
