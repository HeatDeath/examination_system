package com.system.service;

import com.system.pojo.SelectedCourseCustom;

import java.util.List;

/**
 * 选课表servic层
 */
public interface SelectedCourseService {

    //根据课程ID查询课程
    List<SelectedCourseCustom> findByCourseID(Integer id, int page, int rows) throws Exception;

    //查询指定学生成绩
    SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) throws Exception;

    //打分
    void updateOne(SelectedCourseCustom selectedCourseCustom) throws Exception;

    //选课
    boolean save(SelectedCourseCustom selectedCourseCustom) throws Exception;

    //根据学生id查找课程
    List<SelectedCourseCustom> findByStudentID(Integer id) throws Exception;

    //退课
    void remove(SelectedCourseCustom selectedCourseCustom) throws Exception;

}
