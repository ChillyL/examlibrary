package com.chilly.examlibrary.mapper;

import com.chilly.examlibrary.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2021/4/4
 **/

@Mapper
public interface CourseMapper {

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
