package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Admin;
import com.chilly.examlibrary.mapper.AdminLoginMapper;
import com.chilly.examlibrary.service.AdminLoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.chilly.examlibrary.util.MD5Utils.codeMD5;


/**
 * @auther ChillyLin
 * @date 2020/9/28
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Resource
    private AdminLoginMapper adminLoginMapper;

    /**
     * 管理员登录
     * @param number
     * @param password
     * @return
     */
    @Override
    public Admin adminLogin(String number, String password) {
        //使用MD5加密
        return adminLoginMapper.adminLogin(number,codeMD5(password));
    }
}
