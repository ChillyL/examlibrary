package com.chilly.examlibrary.controller.major;


import com.chilly.examlibrary.entity.Course;
import com.chilly.examlibrary.service.MajorCourseService;
import com.chilly.examlibrary.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/04/08
 */

@Controller
public class CourseIndexController {

    @Autowired
    private MajorCourseService majorCourseService;

    @GetMapping("/course")
    public String index(Model model, RedirectAttributes attributes) {

        List<Course> courseList = majorCourseService.listCourse();

        model.addAttribute("courseList",courseList);

        return "major/major";
    }

    @GetMapping("/course/{course_id}")
    public String course(@PathVariable Long course_id,Model model){

        Course course = majorCourseService.getCourseById(course_id);

        model.addAttribute("course",course);

        return "major/majorReading";
    }

    /**
     * 	文件下载
     * @param response
     * @param course_data
     * @return
     */
    @RequestMapping("/course/download")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
    public String down(HttpServletResponse response,String course_data) {
        try {

            //根据文件路径下载文件信息
            UploadUtil.down(response, course_data);

            return "下载成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "下载失败";
    }




}
