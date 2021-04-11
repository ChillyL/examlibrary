package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Course;
import com.chilly.examlibrary.mapper.AdminCourseMapper;
import com.chilly.examlibrary.service.AdminCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        return adminCourseMapper.saveCourse(course);
    }

    @Override
    public int updateCourse(Course course) {
        return adminCourseMapper.updateCourse(course);
    }
}
