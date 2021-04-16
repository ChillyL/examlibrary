package com.chilly.examlibrary.controller.admin;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.entity.Course;
import com.chilly.examlibrary.service.AdminCourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2021/4/11
 */

@Controller
@RequestMapping("/admin")
public class AdminCourseController {
    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminCourseService adminCourseService;

    //分页查询分类列表，引入MyBatis分页插件
    @GetMapping("/course")
    public String listCourse(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {


        PageHelper.startPage(pageNum, 10);
        List<Course> list = adminCourseService.listCourse();
        PageInfo<Course> pageInfo = new PageInfo<Course>(list);

        logger.info("pageInfo:{}", pageInfo);

        //提交到页面
        model.addAttribute("pageInfo", pageInfo);

        return "admin/course";
    }

    //跳转新增页面
    @GetMapping("/course/input")
    public String input(Model model) {


        model.addAttribute("course", new Course());

        return "admin/courseAdd";
    }

    @PostMapping("/course")
    public String post(@RequestParam("file") MultipartFile file, Course course, RedirectAttributes redirectAttributes) throws IOException {

//        Course course1 = adminCourseService.getCourseByTitle(course.getCourse_title());
//        if (course1 != null) {
//            redirectAttributes.addFlashAttribute("message", "不能添加重复的书籍");
//            return "redirect:/admin/course/input";
//        }

        try {
            // Get the file and save it somewhere
            file.transferTo(new File("F:/coursefile",file.getOriginalFilename()));

            //资料地址
            course.setCourse_data("F:/coursefile/" + file.getOriginalFilename());

        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("course:",course);

        //保存
        int courseId = adminCourseService.saveCourse(course);

        if (courseId == 0) {
            redirectAttributes.addFlashAttribute("message", "新增失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "新增成功");
        }

        return "redirect:/admin/course";
    }

    //编辑修改
    @GetMapping("/course/{course_id}/input")
    public String editPost(@PathVariable Long course_id, Model model) {
        Course course = adminCourseService.getCourse(course_id);

        model.addAttribute("course",course);

        return "admin/courseAdd";
    }

    //编辑修改文章
    @PostMapping("/course/{course_id}")
    public String editPost(@Valid Course course, @RequestParam("file") MultipartFile file, @PathVariable Long course_id , RedirectAttributes attributes) {

        //获取之前的书籍相关文件地址
        String previousData = adminCourseService.getCourse(course_id).getCourse_data();

        //判断文件是否更改
        if (("F:/coursefile/" + file.getOriginalFilename()).equals(previousData) || previousData == null){
            course.setCourse_data(previousData);   //一样则不用再次上传
        }else {

            File file2 = new File(previousData);//根据指定的文件名创建File对象
            file2.delete();  //删掉之前的

            try {
                // Get the file and save it somewhere
                file.transferTo(new File("F:/coursefile",file.getOriginalFilename()));

                //资料地址
                course.setCourse_data("F:/coursefile/" + file.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
                course.setCourse_data("");  //出问题就给个空
            }
        }

        int i = adminCourseService.updateCourse(course);

        if (i == 1) {
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            attributes.addFlashAttribute("message", "修改失败");
        }

        return "redirect:/admin/course";
    }

    //删除
    @GetMapping("/course/{course_id}/delete")
    public String delete(@PathVariable Long course_id, RedirectAttributes attributes) {
        int i = adminCourseService.deleteCourse(course_id);
        if (i == 1) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }

        return "redirect:/admin/course";
    }

    @PostMapping("/course/search")
    public String sreach(String course_title, Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        PageHelper.startPage(pageNum, 10);

        logger.info("获取到的courseTitle: {}", course_title);

        List<Course> courses = adminCourseService.getCourseByTitle(course_title);

        PageInfo<Course> pageInfo = new PageInfo<Course>(courses);
        model.addAttribute("pageInfo", pageInfo);

        //动态局部刷新
        //返回到admin/course.html中的blogList片段,即  th:fragment="blogList" 所在
        return "admin/course :: courseList";
    }
}
