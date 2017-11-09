package com.system.mapper;

import com.system.po.CourseCustom;

import java.util.List;

public interface CourseCustomMapper {
    List<CourseCustom> selectByTeacherId(Integer id);
}
