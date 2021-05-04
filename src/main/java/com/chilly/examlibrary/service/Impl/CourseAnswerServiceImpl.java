package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.CourseAnswer;
import com.chilly.examlibrary.entity.CourseAnswer;
import com.chilly.examlibrary.mapper.CourseAnswerMapper;
import com.chilly.examlibrary.mapper.CourseAnswerMapper;
import com.chilly.examlibrary.service.CourseAnswerService;
import com.chilly.examlibrary.service.CourseAnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/24
 */

@Service
public class CourseAnswerServiceImpl implements CourseAnswerService {

    @Resource
    private CourseAnswerMapper courseAnswerMapper;

    //存放迭代找出的所有子代的集合
    private List<CourseAnswer> tempReplys = new ArrayList<>();

    @Override
    public List<CourseAnswer> listCourseAnswerByCourseId(Long course_id) {
        //查出是父节点的评论,,
        List<CourseAnswer> courseAnswers = courseAnswerMapper.findByCourseIdParentIdNull(course_id);

        for (CourseAnswer courseAnswer : courseAnswers) {
            Long id = courseAnswer.getCourse_answer_id();
            String parentName = courseAnswer.getCourse_answer_user_name();
            //查出该父节点的所有子节点
            List<CourseAnswer> childCourseAnswers = courseAnswerMapper.findByCourseIdParentIdNotNull(course_id, id);

            combineChildren(course_id, childCourseAnswers, parentName);
            courseAnswer.setReplyCourseAnswers(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return courseAnswers;
    }


    private void combineChildren(Long course_id, List<CourseAnswer> childCourseAnswers, String parentName) {
        //查询出一级子评论
        if (childCourseAnswers.size() > 0) {
            for (CourseAnswer courseAnswer : childCourseAnswers) {
                String parentName1 = courseAnswer.getCourse_answer_user_name();
                courseAnswer.setParentName(parentName);
                tempReplys.add(courseAnswer);
                Long childId = courseAnswer.getCourse_answer_id();
                //根据子一级评论，找出其他所有二级评论
                recursively(course_id, childId, parentName1);
            }
        }
    }

    private void recursively(Long course_id, Long childId, String parentName1) {
//        根据子一级评论的id找到子二级评论
        List<CourseAnswer> replayCourseAnswers = courseAnswerMapper.findByCourseIdAndReplayId(course_id, childId);

        if (replayCourseAnswers.size() > 0) {
            for (CourseAnswer replayCourseAnswer : replayCourseAnswers) {
                String parentNickname = replayCourseAnswer.getCourse_answer_user_name();
                replayCourseAnswer.setParentName(parentName1);
                Long replayId = replayCourseAnswer.getCourse_answer_id();
                tempReplys.add(replayCourseAnswer);
                recursively(course_id, replayId, parentNickname);
            }
        }
    }

    @Override
    public int saveCourseAnswer(CourseAnswer courseAnswer) {
        courseAnswer.setCourse_answer_createTime(new Date());
        if (courseAnswer.getParent_course_answer_id() == -1) {
            courseAnswer.setParent_course_answer_id(null);
        }

        courseAnswer.setAnswer_sort(0);  //默认的优先级是0

        int answers = courseAnswerMapper.saveCourseAnswer(courseAnswer);

        return answers;
    }

    @Override
    public void deleteCourseAnswer(CourseAnswer CourseAnswer, Long id) {
        courseAnswerMapper.deleteCourseAnswer(id);
    }

    @Override
    public int updateCourseAnswerSort(CourseAnswer CourseAnswer, Long id) {

        Long startTs = System.currentTimeMillis(); // 当前时间戳
        int answer_sort = (int) (startTs % 10000000);
        return courseAnswerMapper.updateCourseAnswerSort(id,answer_sort);
    }
}
