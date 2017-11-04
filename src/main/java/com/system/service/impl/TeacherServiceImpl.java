package com.system.service.impl;

import com.system.exception.CustomException;
import com.system.mapper.CollegeMapper;
import com.system.mapper.CourseMapper;
import com.system.mapper.TeacherMapper;
import com.system.po.*;
import com.system.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public void updateById(Integer id, TeacherCustom teacherCustom) throws Exception {
        teacherMapper.updateByPrimaryKey(teacherCustom);
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
    public List<TeacherCustom> findByName(String name) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        //自定义查询条件
        TeacherExample.Criteria criteria = teacherExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Teacher> list = teacherMapper.selectByExample(teacherExample);

        List<TeacherCustom> teacherCustomList = null;

        if (list != null) {
            teacherCustomList = new ArrayList<TeacherCustom>();
            for (Teacher t : list) {
                TeacherCustom teacherCustom = new TeacherCustom();
                //类拷贝
                BeanUtils.copyProperties(t, teacherCustom);
                //获取课程名
                College college = collegeMapper.selectByPrimaryKey(t.getCollegeid());
                teacherCustom.setcollegeName(college.getCollegename());

                teacherCustomList.add(teacherCustom);
            }
        }

        return teacherCustomList;
    }

    // 获取所有教师
    @Override
    public List<Teacher> findAll() throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.or().andUsernameIsNotNull();
        return teacherMapper.selectByExample(teacherExample);
    }
}
