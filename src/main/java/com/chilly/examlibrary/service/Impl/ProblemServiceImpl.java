package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Answer;
import com.chilly.examlibrary.entity.Problem;
import com.chilly.examlibrary.mapper.ProblemMapper;
import com.chilly.examlibrary.service.ProblemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/25
 */

@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemMapper problemMapper;

    @Override
    public int saveProblem(Problem problem) {
        problem.setProblem_createTime(new Date());
        return problemMapper.saveProblem(problem);
    }

    @Override
    public int updateProblem(Problem problem) {
        problem.setProblem_createTime(new Date());
        return problemMapper.updateProblem(problem);
    }

    @Override
    public Problem getProblemByBookId(Long bookId) {
        return problemMapper.getProblemByBookId(bookId);
    }

    @Override
    public Problem getProblemById(Long problemId) {
        return problemMapper.getProblemById(problemId);
    }

    @Override
    public List<Answer> listAnswerStudentIdByProblem(Long problem_id) {
        return problemMapper.listAnswerStudentIdByProblem(problem_id);
    }
}
