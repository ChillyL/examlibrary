package com.chilly.examlibrary.controller.admin;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.entity.Classify;
import com.chilly.examlibrary.entity.Course;
import com.chilly.examlibrary.service.AdminCourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2021/4/11
 */

@Controller
@RequestMapping("/admin")
public class AdminCourseController {
    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminCourseService adminCourseService;

    //分页查询分类列表，引入MyBatis分页插件
    @GetMapping("/course")
    public String listCourse(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {


        PageHelper.startPage(pageNum, 10);
        List<Course> list = adminCourseService.listCourse();
        PageInfo<Course> pageInfo = new PageInfo<Course>(list);

        logger.info("pageInfo:{}", pageInfo);

        //提交到页面
        model.addAttribute("pageInfo", pageInfo);

        return "admin/course";
    }
}
