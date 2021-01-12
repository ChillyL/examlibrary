package com.chilly.examlibrary.controller;

import com.chilly.examlibrary.entity.Answer;
import com.chilly.examlibrary.entity.Book;
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

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/25
 */
@Controller
@RequestMapping("/book")
public class ProblemController {

    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProblemService problemService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserBookService userBookService;

    private String problem_content;

    //跳转编辑页面
    @GetMapping("/problem/input/{bookId}")
    public String input(@PathVariable Long bookId, Model model) {

        Problem problem = problemService.getProblemByBookId(bookId);

        if (problem == null){
            problem = new Problem();
            problem.setProblem_book_id(bookId);
        }

        BookQuery bookQuery = userBookService.getBookQuery(bookId);
        model.addAttribute("book",bookQuery);
        model.addAttribute("problem",problem);

        return "examTeacher";
    }


    //编辑修改
    @PostMapping("/problem/update")
    public String editPost(Problem problem, RedirectAttributes attributes) {

        int i = problemService.updateProblem(problem);
        if (i == 0) {
            attributes.addFlashAttribute("message", "修改失败，关闭当前页面退出");
        } else {
            attributes.addFlashAttribute("message", "修改成功，关闭当前页面退出");
        }

        String url = "redirect:/book/problem/input/" + problem.getProblem_book_id();
        return url;
    }

    @PostMapping("/problem")
    public String post(Problem problem, RedirectAttributes attributes) {

        //保存
        int i = problemService.saveProblem(problem);

        if (i == 0) {
            attributes.addFlashAttribute("message", "新增失败，关闭当前页面退出");
        } else {
            attributes.addFlashAttribute("message", "新增成功，关闭当前页面退出");
        }

        String url = "redirect:/book/problem/input/" + problem.getProblem_book_id();
        return url;
    }

    //所有考生详情，用于改卷
    @GetMapping("/examStudent/{problem_id}")
    public String listStudent(@PathVariable Long problem_id, Model model) {

        //为了改卷的时候有题目内容
        Problem problem = problemService.getProblemById(problem_id);
        problem_content = problem.getProblem_content();

        List<Answer> answerStudents = problemService.listAnswerStudentIdByProblem(problem_id);
        model.addAttribute("answerStudents", answerStudents);

        model.addAttribute("problem_id",problem_id);

        return "studentList";
    }

    //单个考生详情，用于改卷
    @GetMapping("/examStudentDetails/{answer_book_id}/{answer_student_id}")
    public String Student(@PathVariable Long answer_book_id,@PathVariable Long answer_student_id, Model model) {

        Answer answer_student = answerService.getAnswerByStudentAndBook(answer_student_id,answer_book_id);
        model.addAttribute("answer_student", answer_student);
        model.addAttribute("problem_content",problem_content);

        return "studentDetails";
    }

    //成绩输入
    @PostMapping("/setScore")
    public String scorePost(Answer answer, RedirectAttributes attributes) {

        //保存
        int i = answerService.updateAnswer(answer);

        if (i == 0) {
            attributes.addFlashAttribute("message", "评分失败，关闭当前页面退出");
        } else {
            attributes.addFlashAttribute("message", "评分成功，关闭当前页面退出");
        }

        //传参
        String str = "redirect:/book/examStudent/" + answer.getProblem_id();

        return str;
    }
}
