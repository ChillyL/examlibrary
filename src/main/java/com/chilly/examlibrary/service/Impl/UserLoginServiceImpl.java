package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.Teacher;
import com.chilly.examlibrary.mapper.UserLoginMapper;
import com.chilly.examlibrary.service.UserLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther ChillyLin
 * @create 2020/12/24
 */

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserLoginMapper userLoginMapper;

    @Override
    public Teacher teacherLogin(String number, String password) {
        return userLoginMapper.teacherLogin(number,password);
    }

    @Override
    public Student studentLogin(String number, String password) {
        return userLoginMapper.studentLogin(number,password);
    }
}
