package com.system.dao;

import com.system.pojo.CourseCustom;

import java.util.List;

public interface CourseCustomMapper {
    List<CourseCustom> selectByTeacherId(Integer id);
    List<CourseCustom> selectByStudentId(Integer id);
    List<CourseCustom> selectAll();
}
