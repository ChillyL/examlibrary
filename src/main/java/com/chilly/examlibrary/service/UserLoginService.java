package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.Teacher;
import org.apache.ibatis.annotations.Param;

/**
 * @auther ChillyLin
 * @create 2020/12/24
 */
public interface UserLoginService {

    /**
     * 教师登录
     * @param number
     * @param password
     * @return
     */
    Teacher teacherLogin(String number,String password);


    /**
     * 学生登录
     * @param number
     * @param password
     * @return
     */
    Student studentLogin(String number,String password);
}
