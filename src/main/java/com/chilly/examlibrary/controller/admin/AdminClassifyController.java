package com.chilly.examlibrary.controller.admin;

import com.chilly.examlibrary.entity.Classify;
import com.chilly.examlibrary.service.AdminClassifyService;
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
 * @create 2020/12/21
 */
@Controller
@RequestMapping("/admin")
public class AdminClassifyController {

    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminClassifyService adminClassifyService;

    //分页查询分类列表，引入MyBatis分页插件
    @GetMapping("/classify")
    public String listClassify(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        //按照排序字段，倒序，排序
        String orderBy = "classify_id desc";
        PageHelper.startPage(pageNum, 10, orderBy);
        List<Classify> list = adminClassifyService.listClassify();
        PageInfo<Classify> pageInfo = new PageInfo<Classify>(list);

        logger.info("pageInfo:{}", pageInfo);
        //提交到页面
        model.addAttribute("pageInfo", pageInfo);

        return "admin/classify";
    }

    @GetMapping("/classify/input")
    public String inputClassify(Model model) {
        //为了可以将新建页面和编辑页面公用
        model.addAttribute("classify", new Classify());
        return "admin/classifyAdd";
    }

    @PostMapping("/classify")
    public String post(@Valid Classify classify, RedirectAttributes attributes) {

        Classify classify1 = adminClassifyService.getClassifyByName(classify.getClassify_name());
        if (classify1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/classify/input";
        }

        int t = adminClassifyService.saveClassify(classify);

        if (t == 1) {
            //成功
            attributes.addFlashAttribute("message", "新增成功");
        } else {
            attributes.addFlashAttribute("message", "新增失败");
        }

        return "redirect:/admin/classify";
    }

    @GetMapping("/classify/{classify_id}/input")
    public String editInput(@PathVariable Long classify_id, Model model) {
        model.addAttribute("classify", adminClassifyService.getClassify(classify_id));
        return "admin/classifyAdd";
    }

    //编辑修改分类
    @PostMapping("/classify/{classify_id}")
    public String editPost(@Valid Classify classify, RedirectAttributes attributes) {
        Classify classify1 = adminClassifyService.getClassifyByName(classify.getClassify_name());
        if (classify1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/classify/input";
        }
        int t = adminClassifyService.updateClassify(classify);
        if (t == 1) {
            attributes.addFlashAttribute("message", "编辑成功");
        } else {
            attributes.addFlashAttribute("message", "编辑失败");
        }
        return "redirect:/admin/classify";
    }

    //删除分类
    @GetMapping("/classify/{classify_id}/delete")
    public String delete(@PathVariable Long classify_id, RedirectAttributes attributes) {
        int i = adminClassifyService.deleteClassify(classify_id);
        if (i == 1) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }

        return "redirect:/admin/classify";
    }


}
