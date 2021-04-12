package com.chilly.examlibrary.mapper;

import com.chilly.examlibrary.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/21
 */

@Mapper
public interface AdminCourseMapper {

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
     * 模糊查询
     * @param course_title
     * @return
     */
    List<Course> listCourseByTitle(String course_title);

    /**
     * id查
     * @param course_id
     * @return
     */
    Course getCourse(Long course_id);
}
