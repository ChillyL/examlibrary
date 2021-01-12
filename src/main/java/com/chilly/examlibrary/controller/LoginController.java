package com.chilly.examlibrary.controller;

import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.Teacher;
import com.chilly.examlibrary.service.AdminTeacherService;
import com.chilly.examlibrary.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @auther ChillyLin
 * @create 2020/12/24
 */

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private AdminTeacherService adminTeacherService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String number, @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        Teacher t = adminTeacherService.getTeacherByNumber(number);

        if (t != null) {  //判断一下是学生还是老师

            Teacher teacher = userLoginService.teacherLogin(number, password);
            if (teacher != null) {
                //将用户密码隐藏，不要传到前端
                teacher.setTeacher_password(null);
                session.setAttribute("teacher", teacher);
                return "redirect:/";
            } else {
                attributes.addFlashAttribute("message", "用户名或密码错误");
                //加入redirect ,表示重新定位页面。
                return "redirect:/user";
            }
        } else {
            Student student = userLoginService.studentLogin(number, password);
            if (student != null) {
                //将用户密码隐藏，不要传到前端
                student.setStudent_password(null);
                session.setAttribute("student", student);
                return "redirect:/";
            } else {
                attributes.addFlashAttribute("message", "用户名或密码错误");
                //加入redirect ,表示重新定位页面。
                return "redirect:/user";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("student");
        session.removeAttribute("teacher");
        return "redirect:/";
    }
}
