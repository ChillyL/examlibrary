package com.chilly.examlibrary.controller.admin;

import com.chilly.examlibrary.entity.Admin;
import com.chilly.examlibrary.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @auther ChillyLin
 * @date 2020/9/3
 */

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String number, @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        Admin admin = adminLoginService.adminLogin(number, password);

        if (admin != null) {
            //将用户密码隐藏，不要传到前端
            admin.setAdmin_password(null);
            session.setAttribute("admin", admin);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            //加入redirect ,表示重新定位页面。
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/admin";
    }

}
