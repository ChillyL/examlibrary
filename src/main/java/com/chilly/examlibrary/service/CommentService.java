package com.chilly.examlibrary.service;



import com.chilly.examlibrary.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBookId(Long bookId);

    int saveComment(Comment comment);

    void deleteComment(Comment comment, Long id);
}
