package com.system.dao;

import com.system.pojo.StudentCustom;

import java.util.List;

public interface StudentCustomMapper {
    //分页查询学生信息
    List<StudentCustom> selectByName(String name);

}
