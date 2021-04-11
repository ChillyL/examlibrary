package com.chilly.examlibrary.mapper;

import com.chilly.examlibrary.entity.Book;
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
}
