package com.chilly.examlibrary.mapper;

import com.chilly.examlibrary.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 根据创建时间倒序来排
     *
     * @param book_id
     * @return
     */
    List<Comment> findByBookIdParentIdNull(@Param("book_id") Long book_id);

    /**
     * 查询一级回复
     *
     * @param book_id
     * @param comment_id
     * @return
     */
    List<Comment> findByBookIdParentIdNotNull(@Param("book_id") Long book_id, @Param("comment_id") Long comment_id);

    /**
     * 查询二级回复
     *
     * @param book_id
     * @param childId
     * @return
     */
    List<Comment> findByBookIdAndReplayId(@Param("book_id") Long book_id, @Param("childId") Long childId);

    /**
     * 添加一个评论
     *
     * @param comment
     * @return
     */
    int saveComment(Comment comment);

    /**
     * 删除评论
     *
     * @param comment_id
     */
    void deleteComment(Long comment_id);
}
