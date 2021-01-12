package com.chilly.examlibrary.controller;

import com.chilly.examlibrary.entity.Answer;
import com.chilly.examlibrary.entity.Problem;
import com.chilly.examlibrary.entity.query.BookQuery;
import com.chilly.examlibrary.service.AnswerService;
import com.chilly.examlibrary.service.ProblemService;
import com.chilly.examlibrary.service.UserBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @auther ChillyLin
 * @create 2020/12/25
 */
@Controller
@RequestMapping("/book")
public class AnswerController {

    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserBookService userBookService;


    //跳转编辑页面
    @GetMapping("/answer/input/{studentId}/{bookId}")
    public String input(@PathVariable Long studentId,@PathVariable Long bookId, Model model) {

        Answer answer = answerService.getAnswerByStudentAndBook(studentId,bookId);

        if (answer == null){
            answer = new Answer();
            answer.setAnswer_book_id(bookId);
        }

        BookQuery bookQuery = userBookService.getBookQuery(bookId);
        Problem problem = problemService.getProblemByBookId(bookId);

        model.addAttribute("book",bookQuery);
        model.addAttribute("problem",problem);
        model.addAttribute("answer",answer);

        return "examStudent";
    }


    //编辑修改
    @PostMapping("/answer/update")
    public String editPost(Answer answer, RedirectAttributes attributes) {

        int score = answerService.getAnswerById(answer.getAnswer_id()).getAnswer_score();

        if (score != 0){
            attributes.addFlashAttribute("message", "答案修改失败，教师已对其进行打分，关闭当前页面退出");
        }else {
            int i = answerService.updateAnswer(answer);
            if (i == 0) {
                attributes.addFlashAttribute("message", "答案修改失败，关闭当前页面退出");
            } else {
                attributes.addFlashAttribute("message", "答案修改成功，关闭当前页面退出");
            }
        }

        String url = "redirect:/book/answer/input/" + answer.getAnswer_student_id() + "/" +answer.getAnswer_book_id();
        return url;
    }

    @PostMapping("/answer")
    public String post(Answer answer, RedirectAttributes attributes) {

        answer.setAnswer_score(0); //给个成绩。。。。
        //保存
        int i = answerService.saveAnswer(answer);

        if (i == 0) {
            attributes.addFlashAttribute("message", "答案提交失败，关闭当前页面退出");
        } else {
            attributes.addFlashAttribute("message", "答案提交成功，关闭当前页面退出");
        }

        String url = "redirect:/book/answer/input/" + answer.getAnswer_student_id() + "/" +answer.getAnswer_book_id();
        return url;
    }
}
