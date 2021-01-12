package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.query.AnswerBookQuery;
import com.chilly.examlibrary.entity.query.StudentScoreQuery;
import com.chilly.examlibrary.mapper.AdminStudentMapper;
import com.chilly.examlibrary.service.AdminStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/19
 */
@Service
public class AdminStudentServiceImpl implements AdminStudentService {

    @Resource
    private AdminStudentMapper adminStudentMapper;

    @Override
    public List<Student> listStudent() {
        return adminStudentMapper.listStudent();
    }

    @Override
    public Student getStudentByNumber(String student_number) {
        return adminStudentMapper.getStudentByNumber(student_number);
    }

    @Override
    public Student getStudentById(Long student_id) {
        return adminStudentMapper.getStudentById(student_id);
    }

    @Override
    public List<Student> listStudentByNameOrClass(Student student) {
        return adminStudentMapper.listStudentByNameOrClass(student);
    }

    @Override
    public Long addStudent(Student student) {
        //初始密码和学号相同
        student.setStudent_password(student.getStudent_number());
        return adminStudentMapper.addStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return adminStudentMapper.updateStudent(student                                                                                                                                                                                     );
    }

    @Override
    public int deleteStudentById(Long student_id) {
        return adminStudentMapper.deleteStudentById(student_id);
    }

    @Override
    public StudentScoreQuery getStudentScore(Long studentId) {
        Student student = adminStudentMapper.getStudentById(studentId);
        List<AnswerBookQuery> answerBookQueries = adminStudentMapper.listStudentScore(studentId);
        StudentScoreQuery studentScoreQuery = new StudentScoreQuery(student,answerBookQueries);

        return studentScoreQuery;
    }
}
