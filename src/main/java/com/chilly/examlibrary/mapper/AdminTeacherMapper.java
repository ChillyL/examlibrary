package com.chilly.examlibrary.mapper;


import com.chilly.examlibrary.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminTeacherMapper {

    /**
     * 列表展示所有教师
     * @return
     */
    List<Teacher> listTeacher();

    /**
     * 新增教师
     * @param teacher
     * @return
     */
    int addTeacher(Teacher teacher);

    /**
     * 教师编号查找教师
     * @param teacher_number
     * @return
     */
    Teacher getTeacherByNumber(String teacher_number);

    /**
     * id,查
     * @param teacher_id
     * @return
     */
    Teacher getTeacherById(Long teacher_id);

    /**
     * 模糊查询
     * @param teacher_name
     * @return
     */
    List<Teacher> listTeacherByName(@Param("teacher_name") String teacher_name);

    /**
     * 删除老师通过ID
     * @param teacher_id
     * @return
     */
    int deleteTeacherById(@Param("teacher_id") Long teacher_id);

    /**
     * 修改老师
     * @param teacher
     * @return
     */
    int updateTeacher(Teacher teacher);
}
