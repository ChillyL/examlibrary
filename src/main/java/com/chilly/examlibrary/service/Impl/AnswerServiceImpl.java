package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Answer;
import com.chilly.examlibrary.mapper.AnswerMapper;
import com.chilly.examlibrary.service.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther ChillyLin
 * @create 2020/12/25
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerMapper answerMapper;


    @Override
    public Answer getAnswerByStudentAndBook(Long studentId, Long bookId) {
        return answerMapper.getAnswerByStudentAndBook(studentId,bookId);
    }

    @Override
    public int saveAnswer(Answer answer) {
        answer.setAnswer_score(0);
        return answerMapper.saveAnswer(answer);
    }

    @Override
    public int updateAnswer(Answer answer) {
        return answerMapper.updateAnswer(answer);
    }

    @Override
    public Answer getAnswerById(Long answerId) {
        return answerMapper.getAnswerById(answerId);
    }
}
