package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Book;
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

    /**
     * 课程名，查
     * @param course_title
     * @return
     */
    List<Course> getCourseByTitle(String course_title);

    /**
     * id查
     * @param course_id
     * @return
     */
    Course getCourse(Long course_id);

    /**
     * id删
     * @param course_id
     * @return
     */
    int deleteCourse(Long course_id);
}
