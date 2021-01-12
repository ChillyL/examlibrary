package com.chilly.examlibrary.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther ChillyLin
 * @create 2020/12/24
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

    //拦截，在请求之前
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (request.getSession().getAttribute("student") == null && request.getSession().getAttribute("teacher") == null) {
            response.sendRedirect("/user");  //重定路径
            return false;  //不往下执行
        } else {
            return true;  //继续
        }

    }
}
