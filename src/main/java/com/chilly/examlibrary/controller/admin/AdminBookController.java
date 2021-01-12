package com.chilly.examlibrary.controller.admin;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.entity.Classify;
import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.Teacher;
import com.chilly.examlibrary.service.AdminBookService;
import com.chilly.examlibrary.service.AdminClassifyService;
import com.chilly.examlibrary.service.AdminTeacherService;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/22
 */
@Controller
@RequestMapping("/admin")
public class AdminBookController {

    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminBookService adminBookService;

    @Autowired
    private AdminClassifyService adminClassifyService;

    @Autowired
    private AdminTeacherService adminTeacherService;

    //分页查询分类列表，引入MyBatis分页插件
    @GetMapping("/book")
    public String listClassify(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {


        PageHelper.startPage(pageNum, 10);
        List<Book> list = adminBookService.listBook();
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);

        logger.info("pageInfo:{}", pageInfo);
        List<Classify> classifies = adminClassifyService.listClassify();
        model.addAttribute("classifyList",classifies);
        //提交到页面
        model.addAttribute("pageInfo", pageInfo);

        return "admin/book";
    }

    //跳转博客新增页面
    @GetMapping("/book/input")
    public String input(Model model) {

        List<Classify> classifies = adminClassifyService.listClassify();
        List<Teacher> teachers = adminTeacherService.listTeacher();

        model.addAttribute("teacherList",teachers);
        model.addAttribute("classifyList",classifies);
        model.addAttribute("book", new Book());

        return "admin/bookAdd";
    }

    @PostMapping("/book")
    public String post(@RequestParam("file") MultipartFile file, Book book,  RedirectAttributes redirectAttributes) throws IOException{

        Book book1 = adminBookService.getBookByTitle(book.getBook_title());
        if (book1 != null) {
            redirectAttributes.addFlashAttribute("message", "不能添加重复的书籍");
            return "redirect:/admin/book/input";
        }

        try {
            // Get the file and save it somewhere
            file.transferTo(new File("F:/bookfile",file.getOriginalFilename()));

            //资料地址
            book.setBook_data("F:/bookfile/" + file.getOriginalFilename());

        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("book:",book);

        //保存
        int bookId = adminBookService.saveBook(book);

        if (bookId == 0) {
            redirectAttributes.addFlashAttribute("message", "新增失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "新增成功");
        }

        return "redirect:/admin/book";
    }

    //删除
    @GetMapping("/book/{book_id}/delete")
    public String delete(@PathVariable Long book_id, RedirectAttributes attributes) {
        int i = adminBookService.deleteBook(book_id);
        if (i == 1) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }

        return "redirect:/admin/book";
    }

    @PostMapping("/book/search")
    public String sreach(Book book, Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        PageHelper.startPage(pageNum, 10);

        logger.info("获取到的student: {}", book);

        List<Book> books = adminBookService.listBookByTitleOrClassify(book);

        PageInfo<Book> pageInfo = new PageInfo<Book>(books);
        model.addAttribute("pageInfo", pageInfo);

        //动态局部刷新
        //返回到admin/blogs.html中的blogList片段,即  th:fragment="blogList" 所在
        return "admin/book :: bookList";
    }


    //编辑修改
    @GetMapping("/book/{book_id}/input")
    public String editPost(@PathVariable Long book_id, Model model) {
        Book book = adminBookService.getBook(book_id);
        List<Classify> classifies = adminClassifyService.listClassify();
        List<Teacher> teachers = adminTeacherService.listTeacher();

        model.addAttribute("teacherList",teachers);
        model.addAttribute("classifyList",classifies);
        model.addAttribute("book",book);

        return "admin/bookAdd";
    }

    //编辑修改文章
    @PostMapping("/book/{book_id}")
    public String editPost(@Valid Book book,@RequestParam("file") MultipartFile file,@PathVariable Long book_id ,RedirectAttributes attributes) {

        //获取之前的书籍相关文件地址
        String previousData = adminBookService.getBook(book_id).getBook_data();

        //判断文件是否更改
        if (("F:/bookfile/" + file.getOriginalFilename()).equals(previousData)){
            book.setBook_data(previousData);   //一样则不用再次上传
        }else {

            File file2 = new File(previousData);//根据指定的文件名创建File对象
            file2.delete();  //删掉之前的

            try {
                // Get the file and save it somewhere
                file.transferTo(new File("F:/bookfile",file.getOriginalFilename()));

                //资料地址
                book.setBook_data("F:/bookfile/" + file.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
                book.setBook_data("");  //出问题就给个空
            }
        }

        int i = adminBookService.updateBook(book);

        if (i == 1) {
            attributes.addFlashAttribute("message", "修改成功");
        } else {
            attributes.addFlashAttribute("message", "修改失败");
        }

        return "redirect:/admin/book";
    }

}
