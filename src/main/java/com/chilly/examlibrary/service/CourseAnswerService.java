package com.chilly.examlibrary.service;



import com.chilly.examlibrary.entity.CourseAnswer;

import java.util.List;

public interface CourseAnswerService {

    List<CourseAnswer> listCourseAnswerByCourseId(Long course_id);

    int saveCourseAnswer(CourseAnswer courseAnswer);

    void deleteCourseAnswer(CourseAnswer courseAnswer, Long id);

    /**
     * 置顶功能
     * @param
     * @return
     */
    int updateCourseAnswerSort(CourseAnswer CourseAnswer, Long id);
}
