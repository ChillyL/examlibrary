package com.chilly.examlibrary.mapper;

import com.chilly.examlibrary.entity.Answer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther ChillyLin
 * @create 2020/12/25
 */
@Mapper
public interface AnswerMapper {

    /**
     * 通过学生id和问题id获取对应的作答
     * @param studentId
     * @param bookId
     * @return
     */
    Answer getAnswerByStudentAndBook(Long studentId,Long bookId);

    /**
     * 保存作答
     * @param answer
     * @return
     */
    int saveAnswer(Answer answer);

    /**
     * 更改作答
     * @param answer
     * @return
     */
    int updateAnswer(Answer answer);

    /**
     * id 查
     * @param answerId
     * @return
     */
    Answer getAnswerById(Long answerId);
}
