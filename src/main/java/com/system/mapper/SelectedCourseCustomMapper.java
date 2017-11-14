package com.system.mapper;

import com.system.po.SelectedCourseCustom;
import com.system.po.SelectedCourseExample;

import java.util.List;

public interface SelectedCourseCustomMapper {
    //分页查询教师信息
    List<SelectedCourseCustom> selectByCourseID(Integer id);

    // 查询学生选课信息
    List<SelectedCourseCustom> selectByStudentID(Integer id);
}
