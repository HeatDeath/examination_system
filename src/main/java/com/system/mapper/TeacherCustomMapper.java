package com.system.mapper;

import com.system.po.TeacherCustom;
import com.system.po.TeacherExample;

import java.util.List;

public interface TeacherCustomMapper {

    //分页查询学生信息
    List<TeacherCustom> selectByExample(TeacherExample example);
}
