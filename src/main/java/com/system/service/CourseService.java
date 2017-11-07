package com.system.service;

import com.system.po.Course;
import com.system.po.CourseCustom;

import java.util.List;

public interface CourseService {
    //根据id更新课程信息
    void updateByPrimaryKey(Course course) throws Exception;

    //根据id删除课程信息
    Boolean removeById(Integer id) throws Exception;

    //插入课程信息
    Boolean save(CourseCustom couseCustom) throws Exception;

    //获取课程总数
    long getCountCourse() throws Exception;

    //根据id查询
    CourseCustom findById(Integer id) throws Exception;

    //根据名字查询
    List<CourseCustom> findByName(String name) throws Exception;

    //根据教师id查找课程
    List<CourseCustom> findByTeacherID(Integer id) throws Exception;

    // 根据条件分页查询
    List<Course> selectCourseByName(Course course, int page, int rows);
}
