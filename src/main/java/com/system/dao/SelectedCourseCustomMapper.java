package com.system.dao;

import com.system.pojo.SelectedCourseCustom;

import java.util.List;

public interface SelectedCourseCustomMapper {
    //分页查询教师信息
    List<SelectedCourseCustom> selectByCourseID(Integer id);

    // 查询学生选课信息
    List<SelectedCourseCustom> selectByStudentID(Integer id);
}
