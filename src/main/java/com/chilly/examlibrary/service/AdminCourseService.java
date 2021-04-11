package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Course;

import java.util.List;

public interface AdminCourseService {

    /**
     * 列表展示
     * @return
     */
    List<Course> listCourse();

    /**
     * 新增
     * @param course
     * @return
     */
    int saveCourse(Course course);

    /**
     * 修改
     * @param course
     * @return
     */
    int updateCourse(Course course);
}
