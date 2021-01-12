package com.chilly.examlibrary.controller;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.entity.query.BookQuery;
import com.chilly.examlibrary.service.UserBookService;
import com.chilly.examlibrary.util.UploadUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * @auther ChillyLin
 * @create 2020/12/23
 */

@Controller
public class IndexController {

    @Autowired
    private UserBookService userBookService;

    @GetMapping("/")
    public String index(Model model, RedirectAttributes attributes) {

        HashMap<String, List<BookQuery>> listHashMap = userBookService.listBook();

        model.addAttribute("listHashMap", listHashMap);

        return "index";
    }

    @GetMapping("/book/{book_id}")
    public String book(@PathVariable Long book_id,Model model){

        Book book = userBookService.getBook(book_id);

        model.addAttribute("book",book);

        return "articles";
    }

    /**
     * 	文件下载
     * @param response
     * @param book_data
     * @return
     */
    @RequestMapping("/book/download")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
    public String down(HttpServletResponse response,String book_data) {
        try {

            //根据文件路径下载文件信息
            UploadUtil.down(response, book_data);

            return "下载成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "下载失败";
    }


}
