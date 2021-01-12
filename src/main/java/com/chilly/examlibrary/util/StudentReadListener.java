package com.chilly.examlibrary.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chilly.examlibrary.entity.Student;

import java.util.HashSet;

/**
 * @auther ChillyLin
 * @date 2020/10/2
 */
public class StudentReadListener extends AnalysisEventListener<Student> {

    //确保不重复添加学生
    public static HashSet<Student> studentHashSet = new HashSet<Student>();

    // 每读一样，会调用该invoke方法一次
    @Override
    public void invoke(Student data, AnalysisContext context) {
        System.out.println("data = " + data);
        studentHashSet.add(data);
    }

    // 全部读完之后，会调用该方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // TODO......
    }
}