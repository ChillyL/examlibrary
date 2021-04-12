package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Course;
import com.chilly.examlibrary.mapper.AdminCourseMapper;
import com.chilly.examlibrary.service.AdminCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AdminCourseServiceImpl implements AdminCourseService {

    @Resource
    private AdminCourseMapper adminCourseMapper;

    @Override
    public List<Course> listCourse() {
        return adminCourseMapper.listCourse();
    }

    @Override
    public int saveCourse(Course course) {
        course.setCourse_createTime(new Date());
        return adminCourseMapper.saveCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        course.setCourse_createTime(new Date());
        return adminCourseMapper.updateCourse(course);
    }

    @Override
    public List<Course> getCourseByTitle(String course_title) {
        return adminCourseMapper.listCourseByTitle(course_title);
    }

    @Override
    public Course getCourse(Long course_id) {
        return adminCourseMapper.getCourse(course_id);
    }
}
