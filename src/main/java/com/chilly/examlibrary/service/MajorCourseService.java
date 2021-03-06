package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Course;

import java.util.List;

public interface MajorCourseService {
    /**
     * 列表展示所有课程
     * @return
     */
    List<Course> listCourse();

    /**
     * id查
     * @param course_id
     * @return
     */
    Course getCourseById(Long course_id);
}
