package com.chilly.examlibrary.controller.admin;

import com.chilly.examlibrary.entity.Teacher;
import com.chilly.examlibrary.service.AdminTeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @auther ChillyLin
 * @date 2020/9/29
 */

@Controller
@RequestMapping("/admin")
public class AdminTeacherController {

    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminTeacherService adminTeacherService;

    //教师列表
    @GetMapping("/teachers")
    public String teachers(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        PageHelper.startPage(pageNum, 10);
        List<Teacher> list = adminTeacherService.listTeacher();
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);

        model.addAttribute("pageInfo", pageInfo);

        return "admin/teacher";
    }

    //跳转新增页面
    @GetMapping("/teachers/input")
    public String input(Model model) {

        model.addAttribute("teacher", new Teacher());

        return "admin/teacherAdd";
    }

    @PostMapping("/teachers")
    public String post(Teacher teacher, RedirectAttributes attributes) {

        //不能重复添加老师？？？
        Teacher teacher1 = adminTeacherService.getTeacherByNumber(teacher.getTeacher_number());
        if (teacher1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的教师");
            return "redirect:/admin/teachers/input";
        }

        //保存
        int status = adminTeacherService.addTeacher(teacher);

        if (status == 0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }

        return "redirect:/admin/teachers";
    }

    @PostMapping("/teachers/search")
    public String sreach(String teacher_name, Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        PageHelper.startPage(pageNum, 10);

        List<Teacher> teachers = adminTeacherService.listTeacherByName(teacher_name);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(teachers);
        model.addAttribute("pageInfo", pageInfo);

        //动态局部刷新
        //返回到admin/teacher.html中的teacherList片段,即  th:fragment="teacherList" 所在
        return "admin/teacher :: teacherList";
    }

    //删
    @GetMapping("/teachers/{teacher_id}/delete")
    public String delete(@PathVariable Long teacher_id, RedirectAttributes attributes) {

        int i = 1;

//        if (adminTeacherService.listExamAboutSubject(subject_id).size() > 0){
//            i = 0;
//        }else {
            i = adminTeacherService.deleteTeacherById(teacher_id);
//        }

        if (i == 1) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败，该科目授课老师已经出题");
        }
        return "redirect:/admin/teachers";
    }

    @GetMapping("/teachers/{teacher_id}/input")
    public String editPost(@PathVariable Long teacher_id,Model model){
        Teacher teacher = adminTeacherService.getTeacherById(teacher_id);

        model.addAttribute("teacher",teacher);

        return "admin/teacherAdd";
    }

    @PostMapping("/teachers/{teacher_id}")
    public String editPost(@Valid Teacher teacher,@PathVariable Long teacher_id ,RedirectAttributes attributes){

        int i = adminTeacherService.updateTeacher(teacher);

        if (i == 1) {
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            attributes.addFlashAttribute("message", "修改失败");
        }

        return "redirect:/admin/teachers";
    }

}
