package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.query.AnswerBookQuery;
import com.chilly.examlibrary.entity.query.StudentScoreQuery;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/19
 */
public interface AdminStudentService {

    /**
     * 学生列表展示
     * @return
     */
    List<Student> listStudent();


    /**
     * 通过学号获取学生
     * @param student_number
     * @return
     */
    Student getStudentByNumber(String student_number);

    /**
     * 通过Id获取学生
     * @param student_id
     * @return
     */
    Student getStudentById(Long student_id);

    /**
     * 通过学生姓名或班级搜索
     * @param student
     * @return
     */
    List<Student> listStudentByNameOrClass(Student student);

    /**
     * 新增学生
     * @param student
     * @return
     */
    Long addStudent(Student student);

    /**
     * 更改学生信息
     * @param student
     * @return
     */
    int updateStudent(Student student);

    /**
     * 删除
     * @param student_id
     * @return
     */
    int deleteStudentById(Long student_id);

    /**
     * 学生对应的所有成绩
     * @param studentId
     * @return
     */
    StudentScoreQuery getStudentScore(Long studentId);
}
