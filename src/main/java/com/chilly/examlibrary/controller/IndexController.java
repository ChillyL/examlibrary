package com.chilly.examlibrary.controller;

import com.chilly.examlibrary.entity.Book;
import com.chilly.examlibrary.entity.query.BookQuery;
import com.chilly.examlibrary.service.UserBookService;
import com.chilly.examlibrary.util.UploadUtil;
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

    @GetMapping("/")
    public String index(Model model, RedirectAttributes attributes) {

        return "index";
    }


}
