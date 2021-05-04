package com.chilly.examlibrary.mapper;

import com.chilly.examlibrary.entity.CourseAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseAnswerMapper {

    /**
     * 根据创建时间倒序来排,加上优先级
     *
     * @param course_id
     * @return
     */
    List<CourseAnswer> findByCourseIdParentIdNull(@Param("course_id") Long course_id);

    /**
     * 查询一级回复
     *
     * @param course_id
     * @param course_answer_id
     * @return
     */
    List<CourseAnswer> findByCourseIdParentIdNotNull(@Param("course_id") Long course_id, @Param("course_answer_id") Long course_answer_id);

    /**
     * 查询二级回复
     *
     * @param course_id
     * @param childId
     * @return
     */
    List<CourseAnswer> findByCourseIdAndReplayId(@Param("course_id") Long course_id, @Param("childId") Long childId);

    /**
     * 添加一个评论
     *
     * @param courseAnswer
     * @return
     */
    int saveCourseAnswer(CourseAnswer courseAnswer);

    /**
     * 置顶功能
     * @param course_answer_id
     * @return
     */
    int updateCourseAnswerSort(Long course_answer_id, int answer_sort);

    /**
     * 删除评论
     *
     * @param course_answer_id
     */
    void deleteCourseAnswer(Long course_answer_id);
}
