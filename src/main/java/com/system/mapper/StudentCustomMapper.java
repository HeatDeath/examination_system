package com.system.mapper;

import com.system.po.Student;
import com.system.po.StudentCustom;
import com.system.po.StudentExample;

import java.util.List;

public interface StudentCustomMapper {
    //分页查询学生信息
    List<StudentCustom> selectByName(String name);

}
