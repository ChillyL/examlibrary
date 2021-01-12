package com.chilly.examlibrary.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Objects;

public class Student {
    @ExcelIgnore
    private Long student_id;
    @ExcelProperty(value = "学生学号", index = 0)
    private String student_number;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String student_name;
    @ExcelProperty(value = "登录密码", index = 2)
    private String student_password;
    @ExcelProperty(value = "班级", index = 3)
    private String student_class;

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getStudent_password() {
        return student_password;
    }

    public void setStudent_password(String student_password) {
        this.student_password = student_password;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", student_number='" + student_number + '\'' +
                ", student_password='" + student_password + '\'' +
                ", Student_name='" + student_name + '\'' +
                ", student_class='" + student_class + '\'' +
                '}';
    }

    //重写hashCode,只针对于学号来判断是否一致
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(student_number, student.student_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_number);
    }
}
