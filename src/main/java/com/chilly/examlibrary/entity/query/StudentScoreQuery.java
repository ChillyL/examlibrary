package com.chilly.examlibrary.entity.query;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.chilly.examlibrary.entity.Answer;
import com.chilly.examlibrary.entity.Student;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/27
 */
public class StudentScoreQuery {
    @ExcelIgnore
    private Long student_id;
    @ExcelProperty(value = "学生学号",index = 0)
    private String student_number;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String student_name;
    @ExcelProperty(value = "班级",index = 2)
    private String student_class;
    @ExcelProperty(value = "思考题作答详情",index = 3)
    private String scoreStr;
    @ExcelIgnore
    private List<AnswerBookQuery> answerList;

    public StudentScoreQuery(Student student, List<AnswerBookQuery> answerBookQueryes){
        this.student_id = student.getStudent_id();
        this.student_number = student.getStudent_number();
        this.student_name = student.getStudent_name();
        this.student_class = student.getStudent_class();
        this.answerList = answerBookQueryes;
        this.scoreStr = "";
        for (AnswerBookQuery answerBookQuery : answerBookQueryes){
            this.scoreStr += answerBookQuery.toString();
        }

    }


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

    public String getScoreStr() {
        return scoreStr;
    }

    public void setScoreStr(String scoreStr) {
        this.scoreStr = scoreStr;
    }

    public List<AnswerBookQuery> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerBookQuery> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "StudentScoreQuery{" +
                "student_id=" + student_id +
                ", student_number='" + student_number + '\'' +
                ", student_name='" + student_name + '\'' +
                ", student_class='" + student_class + '\'' +
                ", scoreStr='" + scoreStr + '\'' +
                ", answerList=" + answerList +
                '}';
    }
}
