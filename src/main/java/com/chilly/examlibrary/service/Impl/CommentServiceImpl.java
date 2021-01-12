package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Comment;
import com.chilly.examlibrary.mapper.AdminBookMapper;
import com.chilly.examlibrary.mapper.CommentMapper;
import com.chilly.examlibrary.service.CommentService;
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
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    public List<Comment> listCommentByBookId(Long bookId) {
        //查出是父节点的评论,,
        List<Comment> comments = commentMapper.findByBookIdParentIdNull(bookId);
        for (Comment comment : comments) {
            Long id = comment.getComment_id();
            String parentName = comment.getComment_user_name();
            //查出该父节点的所有子节点
            List<Comment> childComments = commentMapper.findByBookIdParentIdNotNull(bookId, id);

            combineChildren(bookId, childComments, parentName);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    private void combineChildren(Long bookId, List<Comment> childComments, String parentName) {
        //查询出一级子评论
        if (childComments.size() > 0) {
            for (Comment comment : childComments) {
                String parentName1 = comment.getComment_user_name();
                comment.setParentName(parentName);
                tempReplys.add(comment);
                Long childId = comment.getComment_id();
                //根据子一级评论，找出其他所有二级评论
                recursively(bookId, childId, parentName1);
            }
        }
    }

    private void recursively(Long bookId, Long childId, String parentName1) {
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByBookIdAndReplayId(bookId, childId);

        if (replayComments.size() > 0) {
            for (Comment replayComment : replayComments) {
                String parentNickname = replayComment.getComment_user_name();
                replayComment.setParentName(parentName1);
                Long replayId = replayComment.getComment_id();
                tempReplys.add(replayComment);
                recursively(bookId, replayId, parentNickname);
            }
        }
    }

    @Override
    public int saveComment(Comment comment) {
        comment.setComment_createTime(new Date());
        if (comment.getParent_comment_id() == -1) {
            comment.setParent_comment_id(null);
        }
        int comments = commentMapper.saveComment(comment);

        return comments;
    }

    @Override
    public void deleteComment(Comment comment, Long id) {
        commentMapper.deleteComment(id);
    }
}
