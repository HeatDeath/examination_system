package com.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.system.exception.CustomException;
import com.system.dao.CollegeMapper;
import com.system.dao.CourseMapper;
import com.system.dao.TeacherCustomMapper;
import com.system.dao.TeacherMapper;
import com.system.pojo.*;
import com.system.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherCustomMapper teacherCustomMapper;

    @Override
    public void updateById(Integer id, Teacher teacher) throws Exception {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        CourseExample courseExample = new CourseExample();

        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andTeacheridEqualTo(id);
        List<Course> list = courseMapper.selectByExample(courseExample);

        if (list.size() != 0) {
            throw new CustomException("请先删除该名老师所教授的课程");
        }

        teacherMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Boolean save(TeacherCustom teacherCustom) throws Exception {

        Teacher tea = teacherMapper.selectByPrimaryKey(teacherCustom.getUserid());
        if (tea == null) {
            teacherMapper.insert(teacherCustom);
            return true;
        }
        return false;
    }

    @Override
    public long getCountTeacher() throws Exception {
        //自定义查询对象
        TeacherExample teacherExample = new TeacherExample();
        //通过criteria构造查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andUseridIsNotNull();

        return teacherMapper.countByExample(teacherExample);
    }

    @Override
    public TeacherCustom findById(Integer id) throws Exception {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        TeacherCustom teacherCustom = null;
        if (teacher != null) {
            teacherCustom = new TeacherCustom();
            BeanUtils.copyProperties(teacher, teacherCustom);
        }

        return teacherCustom;
    }

    @Override
    public List<TeacherCustom> findByName(Teacher teacher, int page, int rows) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        if (StringUtil.isEmpty(teacher.getUsername())){
            teacherExample.or().andUsernameLike("%");
        }else {
            teacherExample.or().andUsernameLike("%" + teacher.getUsername() + "%");
        }
        PageHelper.startPage(page, rows);
        return teacherCustomMapper.selectByExample(teacherExample);
    }

    // 获取所有教师
    @Override
    public List<Teacher> findAll() throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.or().andUsernameIsNotNull();
        // 返回全部的教师
        return teacherMapper.selectByExample(teacherExample);
    }
}
