package com.system.service;

import com.system.pojo.Teacher;
import com.system.pojo.TeacherCustom;

import java.util.List;

/**
 * Teacher老师Service层
 */
public interface TeacherService {

    //根据id更新老师信息
    void updateById(Integer id, Teacher teacher) throws Exception;

    //根据id删除老师信息
    void removeById(Integer id) throws Exception;

    //保存老师信息
    Boolean save(TeacherCustom teacherCustom) throws Exception;

    //获取老师总数
    long getCountTeacher() throws Exception;

    //根据id查询
    TeacherCustom findById(Integer id) throws Exception;

    //根据名字查询
    List<TeacherCustom> findByName(Teacher teacher, int page, int rows) throws Exception;

    //获取全部教师
    List<Teacher> findAll() throws Exception;
}
