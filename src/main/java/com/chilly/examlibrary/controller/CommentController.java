package com.chilly.examlibrary.controller;

import com.chilly.examlibrary.entity.Comment;
import com.chilly.examlibrary.entity.Teacher;
import com.chilly.examlibrary.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @auther ChillyLin
 * @date 2020/7/7
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    //    查询评论列表
    @GetMapping("/comments/{bookId}")
    public String comments(@PathVariable Long bookId, Model model) {
        List<Comment> comments = commentService.listCommentByBookId(bookId);
        System.out.println(comments);
        model.addAttribute("comments", comments);
        return "articles :: commentList";
    }

    //    新增评论
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session, Model model) {
        Long bookId = comment.getComment_book_id();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher != null) {
            comment.setComment_teacher(true);
        } else {
            //设置头像,吧
            //不想搞。。
        }

        if (comment.getParentComment().getComment_id() != null) {
            comment.setParent_comment_id(comment.getParentComment().getComment_id());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBookId(bookId);
        model.addAttribute("comments", comments);
        return "articles :: commentList";
    }

    //    删除评论
    @GetMapping("/comment/{bookId}/{id}/delete")
    public String delete(@PathVariable Long bookId, @PathVariable Long id, Comment comment, RedirectAttributes attributes, Model model) {
        commentService.deleteComment(comment, id);

        List<Comment> comments = commentService.listCommentByBookId(bookId);

        model.addAttribute("comments", comments);

        String url = "redirect:/book/" + bookId;
        return url;
    }
}
