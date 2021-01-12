package com.chilly.examlibrary.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.chilly.examlibrary.entity.Student;
import com.chilly.examlibrary.entity.query.StudentScoreQuery;
import com.chilly.examlibrary.service.AdminStudentService;
import com.chilly.examlibrary.util.StudentReadListener;
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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @auther ChillyLin
 * @date 2020/9/29
 */

@Controller
@RequestMapping("/admin")
public class AdminStudentController {

    //日志输出
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminStudentService adminStudentService;

    //批量导入学生的列表
    private List<Student> studentList = new ArrayList<Student>();

    //列表
    @GetMapping("/students")
    public String students(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        PageHelper.startPage(pageNum, 10);
        List<Student> list = adminStudentService.listStudent();
        PageInfo<Student> pageInfo = new PageInfo<Student>(list);

        model.addAttribute("pageInfo", pageInfo);

        return "admin/student";
    }

    //跳转新增页面
    @GetMapping("/students/input")
    public String input(Model model) {

        model.addAttribute("student", new Student());

        return "admin/studentAdd";
    }

    @PostMapping("/students")
    public String post(Student student, RedirectAttributes attributes) {

        Student student1 = adminStudentService.getStudentByNumber(student.getStudent_number());
        if (student1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的学生");
            return "redirect:/admin/students/input";
        }

        //保存
        Long studentId = adminStudentService.addStudent(student);

        if (studentId == 0 && studentId == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }

        return "redirect:/admin/students";
    }

    //跳转更改页面
    @GetMapping("/students/input/{student_id}")
    public String editInput(@PathVariable Long student_id, Model model) {

        Student student = adminStudentService.getStudentById(student_id);
        model.addAttribute("student", student);

        return "admin/studentAdd";
    }

    @PostMapping("/students/{student_id}")
    public String editPost(Student student,@PathVariable Long student_id, RedirectAttributes attributes) {

        Student student1 = adminStudentService.getStudentByNumber(student.getStudent_number());
        if (student1 != null) {
            attributes.addFlashAttribute("message", "不能有重复学号的学生");
            return "redirect:/admin/students/input";
        }

        //保存
        int studentId = adminStudentService.updateStudent(student);

        if (studentId == 0) {
            attributes.addFlashAttribute("message", "修改失败");
        } else {
            attributes.addFlashAttribute("message", "修改成功");
        }

        return "redirect:/admin/students";
    }

    @PostMapping("/students/search")
    public String sreach(Student student, Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        PageHelper.startPage(pageNum, 10);

        logger.info("获取到的student: {}", student);

        List<Student> students = adminStudentService.listStudentByNameOrClass(student);
        logger.info("listStudentByNicknameOrClass : {}", students);
        PageInfo<Student> pageInfo = new PageInfo<Student>(students);
        model.addAttribute("pageInfo", pageInfo);

        //动态局部刷新
        //返回到admin/blogs.html中的blogList片段,即  th:fragment="blogList" 所在
        return "admin/student :: studentList";
    }


    //上传excel
    @GetMapping("/students/bulkInput")
    public String index() {
        return "admin/upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "admin/studentAddMany";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get("exceldir/" + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取文件，读取完之后会自动关闭
        /*
            pathName        文件路径；"d:\\模拟在线202003班学员信息.xls"
            head            每行数据对应的实体；Student.class
            readListener    读监听器，每读一样就会调用一次该监听器的invoke方法

            sheet方法参数： 工作表的顺序号（从0开始）或者工作表的名字，不传默认为0
        */
        // 封装工作簿对象
        ExcelReaderBuilder workBook = EasyExcel.read("exceldir/" + file.getOriginalFilename(), Student.class, new StudentReadListener());

        // 封装工作表
        ExcelReaderSheetBuilder sheet1 = workBook.sheet();
        // 读取
        sheet1.doRead();


        Iterator it = StudentReadListener.studentHashSet.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            logger.info("批量录入：{}",next);
            studentList.add((Student) next);
        }

        //清空一下。。不知道这里会不会出个bug
        StudentReadListener.studentHashSet = new HashSet<Student>();

        model.addAttribute("students", studentList);


        return "admin/studentAddMany";
    }

    //excel批量导入学生的模板下载
    @PostMapping("/download")
    public String  testDownload(HttpServletResponse response , Model model) {
        //待下载文件名
        String fileName = "学生信息录入.xlsx";

        File excelFile = new File("exceldir/demo/"+fileName);

        //设置格式
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        //加上设置大小下载下来的.xlsx文件打开时才不会报“Excel 已完成文件级验证和修复。此工作簿的某些部分可能已被修复或丢弃”
        response.addHeader("Content-Length", String.valueOf(excelFile.length()));


        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName.trim(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(excelFile));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            model.addAttribute("result","下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //成功后返回成功信息
        model.addAttribute("result","下载成功");
        return "admin/uploadStatus";
    }


    @PostMapping("/students/addMany")
    public String postMany(RedirectAttributes attributes) {

        logger.info("student:{}",studentList);

        for (Student student : studentList){
            Student student1 = adminStudentService.getStudentByNumber(student.getStudent_number());
            if (student1 == null) {
                Long studentId = adminStudentService.addStudent(student);
            }

        }

        return "redirect:/admin/students";
    }

    //删
    @GetMapping("/students/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        int i = adminStudentService.deleteStudentById(id);
        if (i == 1) {
            attributes.addFlashAttribute("message", "删除成功");
        } else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/students";
    }

    @GetMapping("/students/{studentId}/score")
    public String studentScore(@PathVariable Long studentId,Model model){
        StudentScoreQuery studentScore = adminStudentService.getStudentScore(studentId);

        model.addAttribute("studentScore",studentScore);

        logger.info("studentScore",studentScore);

        return "admin/studentScoreList";
    }

    //导出对应期末考试的excel,会报错，但是问题不大
    @GetMapping("/download")
    public String download(HttpServletResponse response){
        List<Student> students = adminStudentService.listStudent();

        List<StudentScoreQuery> studentScoreQueries = new ArrayList<>();

        for (Student student : students){
            StudentScoreQuery studentScore = adminStudentService.getStudentScore(student.getStudent_id());
            studentScoreQueries.add(studentScore);
        }

        /*
            String pathName 写入文件的路径
            Class head      写入文件的对象类型
            默认写入到07的xlsx中，如果想要写入xls，可以指定类型（待验证）
         */
        ExcelWriterBuilder workBook = EasyExcel.write("exceldir/学生成绩导出.xlsx", StudentScoreQuery.class);

        // sheet方法参数： 工作表的顺序号（从0开始）或者工作表的名字
        workBook.sheet().doWrite(studentScoreQueries);

        //另存为excel
        //待下载文件名
        String fileName = "学生成绩导出.xlsx";

        File excelFile = new File("exceldir/"+fileName);

        //设置格式
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        //加上设置大小下载下来的.xlsx文件打开时才不会报“Excel 已完成文件级验证和修复。此工作簿的某些部分可能已被修复或丢弃”
        response.addHeader("Content-Length", String.valueOf(excelFile.length()));


        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName.trim(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(excelFile));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "导出成功";
    }
}
