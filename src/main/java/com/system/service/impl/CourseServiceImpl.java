package com.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.system.mapper.CollegeMapper;
import com.system.mapper.CourseCustomMapper;
import com.system.mapper.CourseMapper;
import com.system.mapper.SelectedCourseMapper;
import com.system.po.*;
import com.system.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private SelectedCourseMapper selectedCourseMapper;

    @Autowired
    private CourseCustomMapper courseCustomMapper;

    @Override
    public void updateByPrimaryKey(Course course) throws Exception {
        courseMapper.updateByPrimaryKey(course);
    }

    @Override
    public Boolean removeById(Integer id) throws Exception {
        //自定义查询条件
        SelectedCourseExample example = new SelectedCourseExample();
        example.or().andCourseidEqualTo(id);
        List<SelectedCourse> list = selectedCourseMapper.selectByExample(example);

        if (list.size() == 0) {
            courseMapper.deleteByPrimaryKey(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Course> selectCourseByName(Course course, int page, int rows) {
        CourseExample courseExample = new CourseExample();
        if (StringUtil.isEmpty(course.getCoursename())){
            courseExample.or().andCoursenameLike("%");
        }else {
            courseExample.or().andCoursenameLike("%" + course.getCoursename() + "%");
        }
        PageHelper.startPage(page, rows);
        return courseMapper.selectByExample(courseExample);
    }

    @Override
    public Boolean save(CourseCustom courseCustom) throws Exception {
        Course course = courseMapper.selectByPrimaryKey(courseCustom.getCourseid());
        if (course == null) {
            courseMapper.insert(courseCustom);
            return true;
        }
        return false;
    }

    @Override
    public long getCountCourse() throws Exception {
        //自定义查询对象
        CourseExample courseExample = new CourseExample();

        courseExample.or().andCoursenameIsNotNull();

        return courseMapper.countByExample(courseExample);
    }

    @Override
    public CourseCustom findById(Integer id) throws Exception {
        Course course = courseMapper.selectByPrimaryKey(id);
        CourseCustom courseCustom = null;
        if (course != null) {
            courseCustom = new CourseCustom();
            BeanUtils.copyProperties(courseCustom, course);
        }

        return courseCustom;
    }

    @Override
    public List<CourseCustom> findByName(String name) throws Exception {
        CourseExample courseExample = new CourseExample();
        //自定义查询条件
        courseExample.or().andCoursenameLike("%" + name + "%");

        List<Course> list = courseMapper.selectByExample(courseExample);

        List<CourseCustom> courseCustomList = null;

        if (list != null) {
            courseCustomList = new ArrayList<CourseCustom>();
            for (Course c : list) {
                CourseCustom courseCustom = new CourseCustom();
                //类拷贝
                org.springframework.beans.BeanUtils.copyProperties(c, courseCustom);
                //获取课程名
                College college = collegeMapper.selectByPrimaryKey(c.getCollegeid());
                courseCustom.setCollegeName(college.getCollegename());

                courseCustomList.add(courseCustom);
            }
        }

        return courseCustomList;
    }

    @Override
    public List<CourseCustom> findByTeacherID(Integer id, int page, int rows) throws Exception {
        PageHelper.startPage(page, rows);
        return courseCustomMapper.selectByTeacherId(id);
    }

    @Override
    public List<CourseCustom> findByStudentID(Integer id, int page, int rows) throws Exception {

        PageHelper.startPage(page, rows);
        return courseCustomMapper.selectByStudentId(id);
    }

    @Override
    public List<CourseCustom> selectAll(int page, int rows) throws Exception {
        PageHelper.startPage(page, rows);
        return courseCustomMapper.selectAll();
    }
}
