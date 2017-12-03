package com.system.dao;

import com.system.pojo.TeacherCustom;
import com.system.pojo.TeacherExample;

import java.util.List;

public interface TeacherCustomMapper {

    //分页查询教师信息
    List<TeacherCustom> selectByExample(TeacherExample example);
}
