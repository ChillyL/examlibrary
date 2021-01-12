package com.chilly.examlibrary.service.Impl;

import com.chilly.examlibrary.entity.Teacher;
import com.chilly.examlibrary.mapper.AdminTeacherMapper;
import com.chilly.examlibrary.service.AdminTeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminTeacherServiceImpl implements AdminTeacherService {

    @Resource
    private AdminTeacherMapper adminTeacherMapper;

    @Override
    public List<Teacher> listTeacher() {
        return adminTeacherMapper.listTeacher();
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return adminTeacherMapper.addTeacher(teacher);
    }

    @Override
    public Teacher getTeacherByNumber(String teacher_number) {
        return adminTeacherMapper.getTeacherByNumber(teacher_number);
    }

    @Override
    public Teacher getTeacherById(Long teacher_id) {
        return adminTeacherMapper.getTeacherById(teacher_id);
    }

    @Override
    public List<Teacher> listTeacherByName(String teacher_name) {
        return adminTeacherMapper.listTeacherByName(teacher_name);
    }

    @Override
    public int deleteTeacherById(Long teacher_id) {
        return adminTeacherMapper.deleteTeacherById(teacher_id);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return adminTeacherMapper.updateTeacher(teacher);
    }


}
