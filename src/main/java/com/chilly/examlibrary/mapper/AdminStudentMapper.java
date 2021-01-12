package com.chilly.examlibrary.mapper;


import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.Teacher;
import com.chilly.examlibrary.entity.query.AnswerBookQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminStudentMapper {

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
     * 学生对应的成绩
     * @param studentId
     * @return
     */
    List<AnswerBookQuery> listStudentScore(Long studentId);

}
