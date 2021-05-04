package com.chilly.examlibrary.controller.major;

import com.chilly.examlibrary.entity.Comment;
import com.chilly.examlibrary.entity.CourseAnswer;
import com.chilly.examlibrary.entity.Teacher;
import com.chilly.examlibrary.service.CommentService;
import com.chilly.examlibrary.service.CourseAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CourseAnswerController {

    @Autowired
    private CourseAnswerService courseAnswerService;

    //    查询评论列表
    @GetMapping("/courseAnswers/{course_id}")
    public String courseAnswers(@PathVariable Long course_id, Model model) {
        List<CourseAnswer> courseAnswers = courseAnswerService.listCourseAnswerByCourseId(course_id);
        System.out.println(courseAnswers);
        model.addAttribute("courseAnswers", courseAnswers);
        return "major/majorReading :: courseAnswerList";
    }

    //    新增评论
    @PostMapping("/courseAnswers")
    public String post(CourseAnswer courseAnswer, HttpSession session, Model model) {
        Long course_id = courseAnswer.getCourse_id();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher != null) {
            courseAnswer.setCourse_answer_teacher(true);
        } else {
            //设置头像,吧
            //不想搞。。
        }

        if (courseAnswer.getParentCourseAnswer().getCourse_answer_id() != null) {
            courseAnswer.setParent_course_answer_id(courseAnswer.getParentCourseAnswer().getCourse_answer_id());
        }
        courseAnswerService.saveCourseAnswer(courseAnswer);
        List<CourseAnswer> courseAnswers = courseAnswerService.listCourseAnswerByCourseId(course_id);
        model.addAttribute("courseAnswers", courseAnswers);
        return "major/majorReading :: courseAnswerList";
    }

    //    删除评论
    @GetMapping("/courseAnswer/{course_id}/{id}/delete")
    public String delete(@PathVariable Long course_id, @PathVariable Long id, CourseAnswer courseAnswer, RedirectAttributes attributes, Model model) {
        courseAnswerService.deleteCourseAnswer(courseAnswer, id);

        List<CourseAnswer> courseAnswers = courseAnswerService.listCourseAnswerByCourseId(course_id);

        model.addAttribute("courseAnswers", courseAnswers);

        String url = "redirect:/course/" + course_id;
        return url;
    }

    //    置顶评论
    @GetMapping("/courseAnswer/{course_id}/{id}/top")
    public String top(@PathVariable Long course_id, @PathVariable Long id, CourseAnswer courseAnswer, RedirectAttributes attributes, Model model) {

        courseAnswerService.updateCourseAnswerSort(courseAnswer, id);

        List<CourseAnswer> courseAnswers = courseAnswerService.listCourseAnswerByCourseId(course_id);
        System.out.println(courseAnswers);
        model.addAttribute("courseAnswers", courseAnswers);

        String url = "redirect:/course/" + course_id;
        return url;




    }
}
