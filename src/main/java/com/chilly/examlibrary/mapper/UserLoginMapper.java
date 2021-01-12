package com.chilly.examlibrary.mapper;

import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther ChillyLin
 * @create 2020/12/24
 */
@Mapper
public interface UserLoginMapper {

    /**
     * 教师登录
     * @param number
     * @param password
     * @return
     */
    Teacher teacherLogin(@Param("number") String number, @Param("password") String password);


    /**
     * 学生登录
     * @param number
     * @param password
     * @return
     */
    Student studentLogin(@Param("number") String number, @Param("password") String password);

}
