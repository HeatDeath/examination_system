package com.system.service;

import com.system.pojo.Student;
import com.system.pojo.StudentCustom;

import java.util.List;

/**
 * Student学生Service层
 */
public interface StudentService {

    //根据id个更新学生信息
    void updateById(Integer id, Student student) throws Exception;

    //根据id删除学生信息
    void removeById(Integer id) throws Exception;

    //保存学生信息
    Boolean save(Student student) throws Exception;

    //获取学生总数
    long getCountStudent() throws Exception;

    //根据id获取学生信息
    StudentCustom findById(Integer id) throws Exception;

    //根据名字模糊查询
    List<StudentCustom> findByName(Student student, int page, int rows) throws Exception;
}
