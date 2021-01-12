package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Admin;

public interface AdminLoginService {

    /**
     *  管理员登录
     * @param number
     * @param password
     * @return
     */
    Admin adminLogin(String number, String password);
}
