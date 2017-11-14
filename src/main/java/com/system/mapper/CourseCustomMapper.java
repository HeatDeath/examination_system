package com.system.mapper;

import com.system.po.CourseCustom;

import java.util.List;

public interface CourseCustomMapper {
    List<CourseCustom> selectByTeacherId(Integer id);
    List<CourseCustom> selectByStudentId(Integer id);
    List<CourseCustom> selectAll();
}
