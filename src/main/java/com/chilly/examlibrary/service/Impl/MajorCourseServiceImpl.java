package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Course;
import com.chilly.examlibrary.mapper.CourseMapper;
import com.chilly.examlibrary.service.MajorCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MajorCourseServiceImpl implements MajorCourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<Course> listCourse() {
        return courseMapper.listCourse();
    }

    @Override
    public Course getCourseById(Long course_id) {

        Course course = courseMapper.getCourseById(course_id);

        return course;
    }
}
