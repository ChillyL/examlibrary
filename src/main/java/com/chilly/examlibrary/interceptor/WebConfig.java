package com.chilly.examlibrary.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @auther ChillyLin
 * @date 2020/6/23
 * <p>
 * 配置拦截器
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //定义管理员模块拦截的位置
        registry.addInterceptor(new AdminLoginInterceptor())
                .addPathPatterns("/admin/**")   //定义需要拦截的路径
                .excludePathPatterns("/admin")  //排除部分必须执行的
                .excludePathPatterns("/admin/login");

        //定义浏览模块拦截的位置
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/book/**")   //定义需要拦截的路径
                .addPathPatterns("/common/**")
                .excludePathPatterns("/user")  //排除部分必须执行的
                .excludePathPatterns("/");
    }
}
