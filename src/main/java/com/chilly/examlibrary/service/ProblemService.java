package com.chilly.examlibrary.service;

import com.chilly.examlibrary.entity.Answer;
import com.chilly.examlibrary.entity.Problem;

import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/25
 */

public interface ProblemService {

    /**
     * 新增
     * @param problem
     * @return
     */
    int saveProblem(Problem problem);

    /**
     * 改
     * @param problem
     * @return
     */
    int updateProblem(Problem problem);

    /**
     * bookId，查题目
     * @param bookId
     * @return
     */
    Problem getProblemByBookId(Long bookId);

    /**
     * problemId，查题目
     * @param problemId
     * @return
     */
    Problem getProblemById(Long problemId);

    /**
     * 获取作答该思考题的所有学生和学生
     * @param problem_id
     * @return
     */
    List<Answer> listAnswerStudentIdByProblem(Long problem_id);

    /**
     * 删
     * @param bookId
     * @return
     */
    int deleteProblemByBookId(Long bookId);
}
